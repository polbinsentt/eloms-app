package com.project.eloms.utils;

import com.project.eloms.dtos.ResponseDto;
import com.project.eloms.dtos.admin.AccountDto;
import com.project.eloms.types.MessageType;

public class InputValidator {

    public static ResponseDto accountInputValidator(AccountDto dto) {

        if (dto.getUsername() == null || dto.getUsername().isBlank())
            return ResponseUtility.getErrorResponse("E0001", MessageType.MISSING_REQUIRED_INPUT);

        if (dto.getPassword() == null || dto.getPassword().isBlank())
            return ResponseUtility.getErrorResponse("E0002", MessageType.MISSING_REQUIRED_INPUT);

        if (dto.getFirstName() == null || dto.getFirstName().isBlank())
            return ResponseUtility.getErrorResponse("E0003", MessageType.MISSING_REQUIRED_INPUT);

        if (dto.getMiddleName() == null || dto.getMiddleName().isBlank())
            return ResponseUtility.getErrorResponse("E0004", MessageType.MISSING_REQUIRED_INPUT);

        if (dto.getLastName() == null || dto.getLastName().isBlank())
            return ResponseUtility.getErrorResponse("E0005", MessageType.MISSING_REQUIRED_INPUT);

        if (dto.getBirthdate() == null) {
            return ResponseUtility.getErrorResponse("E0006", MessageType.MISSING_REQUIRED_INPUT);
        }
        if (dto.getEmail() == null || dto.getEmail().isBlank())
            return ResponseUtility.getErrorResponse("E0007", MessageType.MISSING_REQUIRED_INPUT);

        if (dto.getDepartmentId() == null)
            return ResponseUtility.getErrorResponse("E0008", MessageType.MISSING_REQUIRED_INPUT);

        if (dto.getPhoneNumber() == null || dto.getPhoneNumber().isBlank())
            return ResponseUtility.getErrorResponse("E0009", MessageType.MISSING_REQUIRED_INPUT);

        return null;
    }
}
