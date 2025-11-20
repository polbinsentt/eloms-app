package com.project.eloms.controllers.admin;

import com.project.eloms.dtos.admin.AccountDto;
import com.project.eloms.dtos.ResponseDto;
import com.project.eloms.services.admin.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/account")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/set")
    public ResponseDto setAccount(@RequestBody AccountDto dto){
        return accountService.setAccount(dto);
    }

    @PostMapping("/list")
    public ResponseDto listAllAccounts(){
        return  accountService.listAllAccounts();
    }

    @PostMapping("/get/{id}")
    public ResponseDto getAccountById(@PathVariable Long id){
        return accountService.getAccountById(id);
    }

}
