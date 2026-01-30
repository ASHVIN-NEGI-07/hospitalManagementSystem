package com.ashvin.projects.hospitalManagment.security;

import com.ashvin.projects.hospitalManagment.dto.LoginRequestDto;
import com.ashvin.projects.hospitalManagment.dto.LoginResponseDto;
import com.ashvin.projects.hospitalManagment.dto.SignupResponseDto;
import com.ashvin.projects.hospitalManagment.entity.User;
import com.ashvin.projects.hospitalManagment.entity.type.AuthProviderType;
import com.ashvin.projects.hospitalManagment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
        );

        User user = (User) authentication.getPrincipal();

        String token = authUtil.generateAccessToken(user);

        return new LoginResponseDto(token,user.getId());
    }

    public User signUpInternal(LoginRequestDto signupRequestDto,AuthProviderType authProviderType,String providerId) throws IllegalAccessException {
        User user = userRepository.findByUsername(signupRequestDto.getUsername()).orElse(null);

        if(user!=null) throw new IllegalAccessException("User alredy exists");

        user  =  User.builder()
                .username((signupRequestDto.getUsername()))
                .providerId(providerId)
                .providerType(authProviderType)
                .build();

        // in case of OAuth2 providers we don't get password so we were getting bcrypt error as we cant encrypt null password
        if(authProviderType == AuthProviderType.EMAIL){
            user.setPassword(passwordEncoder.encode(signupRequestDto.getPassword()));
        }
        // else password remains null

        return userRepository.save(user);
    }


    // login controller
    public SignupResponseDto signup(LoginRequestDto signupRequestDto) throws IllegalAccessException {

        User user = signUpInternal(signupRequestDto,AuthProviderType.EMAIL,null);
        return new SignupResponseDto(user.getId(),user.getUsername());
    }

    @Transactional
    public ResponseEntity<LoginResponseDto> handleOAuth2LoginRequest(OAuth2User oAuth2User, String registerationId) throws IllegalAccessException {
        // fetch providerId and providerType
        // save the providerId and providerType info with user
        // if the user has an account : directly login
        // otherwise , first sign up and then login

        AuthProviderType providerType = authUtil.getProviderTypeFromRegisterationId(registerationId);
        String providerId = authUtil.determineProviderIdFromOAuth2User(oAuth2User,registerationId);

        User user = userRepository.findByProviderIdAndProviderType(providerId,providerType).orElse(null);

        String email = oAuth2User.getAttribute("email");

        User emailUser = userRepository.findByUsername(email).orElse(null);

        if(user == null && emailUser == null) {
            // signup flow :
            String username = authUtil.determineUsernameFormOAuth2User(oAuth2User,registerationId,providerId);
            user = signUpInternal(new LoginRequestDto(username,null),providerType,providerId);
        }
        else if (user != null) {

            // we get a new email so store it as when we don't get email we are storing any random things in username if we don't get email
            if(email != null && !email.isBlank() && !email.equals(user.getUsername())) {
                user.setUsername(email);
                userRepository.save(user);
            }
        }

        // emailUser is not null and user is trying to log in through OAuth2 so don't allow it
        else{
           throw new BadCredentialsException("This email is already registered with provider " + emailUser.getProviderType());
        }

        LoginResponseDto loginResponseDto = new LoginResponseDto(authUtil.generateAccessToken(user),user.getId());
        return ResponseEntity.ok(loginResponseDto);
    }

}
