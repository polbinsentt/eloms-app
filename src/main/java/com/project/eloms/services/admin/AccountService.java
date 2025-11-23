package com.project.eloms.services.admin;

import com.project.eloms.dtos.admin.AccountDto;
import com.project.eloms.dtos.ResponseDto;
import com.project.eloms.entities.*;
import com.project.eloms.repositories.AccountRepository;
import com.project.eloms.repositories.DepartmentRepository;
import com.project.eloms.types.MessageType;
import com.project.eloms.types.SortOrder;
import com.project.eloms.utils.InputValidator;
import com.project.eloms.utils.ResponseUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static com.project.eloms.utils.DefaultSetter.setAccountFromDto;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final DepartmentRepository departmentRepository;
    private final  PasswordEncoder passwordEncoder;

    public ResponseDto setAccount(AccountDto dto) {
        if (dto == null)
            return ResponseUtility.getErrorResponse("E0001", MessageType.DTO_IS_NULL);

        if (dto.getUsername() == null || dto.getUsername().isBlank())
            return ResponseUtility.getErrorResponse("E0002", MessageType.USERNAME_INPUT_REQUIRED);
        /*
        * check if username already exist
        * if new account validate input if all fields are present
        * if not then create new account
        * before creating new account check if valid department id
        * */
        Account account = accountRepository.findByUsername(dto.getUsername()).orElse(null);
        boolean isNew = account == null;

        if (isNew){
            ResponseDto validation = InputValidator.accountInputValidator(dto);
            if(validation != null){
                return validation;
            }
        }

        if (dto.getPhoneNumber() != null) {
            Account numberAlreadyExist = accountRepository.findByPhoneNumber(dto.getPhoneNumber()).orElse(null);
            if(numberAlreadyExist != null && (isNew || numberAlreadyExist.getId() != account.getId())){
                return ResponseUtility.getErrorResponse("E0001", MessageType.PHONE_NUMBER_MUST_BE_UNIQUE);
            }
        }
        if (    dto.getPhoneNumber() != null &&
                !Pattern.matches("^(\\+63|0)9\\d{9}$", dto.getPhoneNumber())) {
            return ResponseUtility.getErrorResponse("E0002", MessageType.INVALID_PHONE_NUMBER);
        }

        if (dto.getDepartmentId() != null){
            Department department = departmentRepository.findById(dto.getDepartmentId()).orElse(null);
            if (department == null) {
                return ResponseUtility.getErrorResponse("E0003", MessageType.DEPARTMENT_NOT_EXIST);
            }
        }

        if (isNew){
            account = new Account();
            account.setUsername(dto.getUsername());
            account.setCreatedAt(new Date());
            account.setCreatedBy("ADMIN");
            }
        setAccountFromDto(dto, account, passwordEncoder);
        account.setUpdatedAt(new Date());
        account.setUpdatedBy("ADMIN");

        accountRepository.save(account);
        return ResponseUtility.getSuccessResponse(MessageType.SUCCESSFULLY_SAVED);
    }


    public ResponseDto getAccountByUserId(Long userId) {
        Map<String, Object> account = accountRepository.findByAccountId(userId).orElse(null);

        if(account == null)
            return ResponseUtility.getErrorResponse("E0001", MessageType.ACCOUNT_NOT_FOUND);

        return ResponseUtility.getSuccessResponse(MessageType.ACCOUNT_SUCCESSFULLY_FETCHED, account);
    }

    public ResponseDto listAccountWithFilter(AccountDto dto) {

        String username = dto.getUsername();
        String departmentName = dto.getDepartmentName();
        String sortOrder = (dto.getSortOrder() != null ? dto.getSortOrder().name() : "ASC");
        String sortBy = dto.getSortBy();
        List<Map<String,Object>> accounts = accountRepository.listAccountWithFilter(username,departmentName,sortOrder, sortBy);

        return ResponseUtility.getSuccessResponse(MessageType.SUCCESSFULLY_FETCHED_ACCOUNTS, accounts);
    }

    public void deleteAccountById(Long accountId) {
        accountRepository.deleteById(accountId);
    }
}
