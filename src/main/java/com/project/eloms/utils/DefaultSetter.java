package com.project.eloms.utils;

import com.project.eloms.dtos.admin.AccountDto;
import com.project.eloms.entities.Account;
import com.project.eloms.types.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

public class DefaultSetter {

    public static void setAccountFromDto(AccountDto dto, Account account, PasswordEncoder passwordEncoder) {
        if (dto.getPassword() != null && !dto.getPassword().isBlank()){
            account.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        if (dto.getFirstName() != null)
            account.setFirstName(dto.getFirstName());

        if (dto.getMiddleName() != null)
            account.setMiddleName(dto.getMiddleName());

        if (dto.getLastName() != null)
            account.setLastName((dto.getLastName()));

        if (dto.getBirthdate() != null)
            account.setBirthdate(dto.getBirthdate());

        if (dto.getEmail() != null)
            account.setEmail(dto.getEmail());

        if (dto.getPhoneNumber() != null){
            account.setPhoneNumber(dto.getPhoneNumber());
        }

        if (dto.getDepartmentId() != null)
            account.setDepartmentId(dto.getDepartmentId());

        account.setRole(dto.getRole() != null ? dto.getRole() : Roles.USER);

        account.setIsActive(dto.getIsActive() != null ? dto.getIsActive(): Boolean.TRUE);
    }
}
