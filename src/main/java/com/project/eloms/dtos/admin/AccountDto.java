package com.project.eloms.dtos.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.eloms.entities.Role;
import com.project.eloms.types.AccountStatus;
import com.project.eloms.types.Roles;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AccountDto {

    private String username;
    private String password;

    private String lastName;
    private String firstName;
    private String middleName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthdate;

    private String email;
    private String phoneNumber;
    private List<Roles> roles;

    private Long departmentId;
    private AccountStatus accountStatus;

}
