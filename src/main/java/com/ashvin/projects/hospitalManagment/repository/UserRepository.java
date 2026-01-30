package com.ashvin.projects.hospitalManagment.repository;

import com.ashvin.projects.hospitalManagment.entity.User;
import com.ashvin.projects.hospitalManagment.entity.type.AuthProviderType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    // this method will write JPQL automatically, and we will get the user if it is saved in DB
    Optional<User> findByProviderIdAndProviderType(String providerId, AuthProviderType providerType);
}