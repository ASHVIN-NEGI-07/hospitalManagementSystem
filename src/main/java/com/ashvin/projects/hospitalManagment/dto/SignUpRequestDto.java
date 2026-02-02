package com.ashvin.projects.hospitalManagment.dto;

import com.ashvin.projects.hospitalManagment.entity.type.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto {

    private String username;
    private String password;
    private String name;


    // only for learning purposes  we don't want user to define his role at time of signup (only admins can do that )
    private Set<RoleType> roles = new HashSet<>();
}
