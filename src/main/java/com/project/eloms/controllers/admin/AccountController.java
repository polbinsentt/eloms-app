package com.project.eloms.controllers.admin;

import com.project.eloms.dtos.admin.AccountDto;
import com.project.eloms.dtos.ResponseDto;
import com.project.eloms.services.admin.AccountService;
import com.project.eloms.types.MessageType;
import com.project.eloms.utils.ResponseUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/account")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/set")
    public ResponseDto setAccount(@RequestBody AccountDto dto){
        return accountService.setAccount(dto);
    }

    @PostMapping("/get")
    public ResponseDto getAccounts(){


        return  accountService.getAccounts();
    }

    @PostMapping("/get/{id}")
    public ResponseDto getAccountById(@PathVariable Long id){
        return accountService.getAccountById(id);
    }

}
