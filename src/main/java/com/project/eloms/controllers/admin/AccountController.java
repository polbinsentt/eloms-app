package com.project.eloms.controllers.admin;

import com.project.eloms.dtos.admin.AccountDto;
import com.project.eloms.dtos.ResponseDto;
import com.project.eloms.services.admin.AccountService;
import com.project.eloms.types.MessageType;
import com.project.eloms.utils.ResponseUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/account")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/set")
    public ResponseDto setAccount(@RequestBody AccountDto dto){
        return accountService.setAccount(dto);
    }

    @PostMapping("/set/batch")
    public ResponseDto setBatchAccount(@RequestBody List<AccountDto> batchDto) {

        for (AccountDto dto : batchDto){
            accountService.setAccount(dto);
        }
        return ResponseUtility.getSuccessResponse(MessageType.SUCCESSFULLY_SAVED);
    }

    @PostMapping("/get/{userId}")
    public ResponseDto getAccountByUserId(@PathVariable Long userId){
        return accountService.getAccountByUserId(userId);
    }

    @PostMapping("/list")
    public ResponseDto listAccountWithFilter(@RequestBody AccountDto dto){
        return  accountService.listAccountWithFilter(dto);
    }

    @PostMapping("/delete/{accountId}")
    public ResponseDto deleteAccountById(@PathVariable Long accountId){

        accountService.deleteAccountById(accountId);
        return ResponseUtility.getSuccessResponse(MessageType.ACCOUNT_SUCCESSFULLY_DELETED);
    }
}
