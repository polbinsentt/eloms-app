package com.project.eloms.dtos.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.eloms.types.Roles;
import com.project.eloms.types.SortOrder;
import lombok.Data;

import java.util.Date;

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
    private Long departmentId;

    private Roles role;
    private Boolean isActive;

    //Search
    private String departmentName;

    private SortOrder sortOrder;
    private String sortBy;
}
