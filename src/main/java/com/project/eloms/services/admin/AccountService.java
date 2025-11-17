package com.project.eloms.services.admin;

import com.project.eloms.dtos.admin.AccountDto;
import com.project.eloms.dtos.ResponseDto;
import com.project.eloms.entities.*;
import com.project.eloms.repositories.*;
import com.project.eloms.types.AccountStatus;
import com.project.eloms.types.MessageType;
import com.project.eloms.types.Roles;
import com.project.eloms.utils.ResponseUtility;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final DepartmentRepository departmentRepository;
    private final  PasswordEncoder passwordEncoder;
    private final PhoneNumberRepository phoneNumberRepository;
    private final UserRoleRepository userRoleRepository;

    @Transactional
    public ResponseDto setAccount(AccountDto dto) {
        Account account = accountRepository.findByUsername(dto.getUsername()).orElse(null);
        Department department = departmentRepository.findById(account != null ? account.getDepartmentId() : dto.getDepartmentId()).orElse(null);
        if(department == null)
            return ResponseUtility.getErrorResponse("E0001", MessageType.DEPARTMENT_NOT_EXIST);

        if (dto.getPhoneNumber() != null && !Pattern.matches("^(\\+63|0)9\\d{9}$", dto.getPhoneNumber()))
            return ResponseUtility.getErrorResponse("E0002", MessageType.INVALID_PHONE_NUMBER);

        if(account == null) {
            account = new Account();
            account.setUsername(dto.getUsername());
            account.setCreatedAt(new Date());
            account.setCreatedBy("ADMIN");
        }
        setAccountFromDto(dto, account);
        account.setAccountStatus(dto.getAccountStatus() == null ? AccountStatus.ACTIVE : dto.getAccountStatus());
        if(dto.getPassword() != null)
            account.setPassword(passwordEncoder.encode(dto.getPassword()));
        account.setUpdatedAt(new Date());
        account.setUpdatedBy("ADMIN");

        accountRepository.save(account);


        PhoneNumber phone = phoneNumberRepository.findByUserId(account.getId()).orElse(null);
        if (phone == null){
            phone = new PhoneNumber();
            phone.setUserId(account.getId());
            phone.setCreatedBy("ADMIN");
            phone.setCreatedAt(new Date());
        }
        if (dto.getPhoneNumber() != null)
            phone.setPhoneNumber(dto.getPhoneNumber());
        phone.setUpdatedBy("ADMIN");
        phone.setUpdatedAt(new Date());

        phoneNumberRepository.save(phone);

        if (dto.getRoles() != null) {
            for (Roles role : dto.getRoles()) {

                UserRole userRole = userRoleRepository.findByUserIdAndRoleType(account.getId(),role).orElse(null);

                if (userRole == null){
                    userRole = new UserRole();
                    userRole.setCreatedAt(new Date());
                    userRole.setRoleType(role);
                    userRole.setUserId(account.getId());
                    userRole.setCreatedBy("ADMIN");
                }
                userRole.setUpdatedAt(new Date());
                userRole.setUpdatedBy("ADMIN");

                userRoleRepository.save(userRole);
            }

        }
        return ResponseUtility.getSuccessResponse(MessageType.SUCCESSFULLY_SAVED);
    }

    public static void setAccountFromDto(AccountDto dto, Account account) {
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

        if (dto.getDepartmentId() != null)
            account.setDepartmentId(dto.getDepartmentId());
    }

    public ResponseDto getAccountById(Long id) {
        Map<String,Object> account = accountRepository.findByAccountId(id).orElse(null);

        if(account == null)
            return ResponseUtility.getErrorResponse("E0001", MessageType.ACCOUNT_NOT_FOUND);

        return ResponseUtility.getSuccessResponse(MessageType.ACCOUNT_SUCCESSFULLY_FETCHED, account);
    }

    public ResponseDto getAccounts() {
        List<Map<String,Object>> res = accountRepository.getAccounts();

    return ResponseUtility.getSuccessResponse(MessageType.SUCCESSFULLY_FETCHED_ACCOUNTS, res);
    }
}
