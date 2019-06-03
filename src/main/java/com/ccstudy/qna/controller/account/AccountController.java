package com.ccstudy.qna.controller.account;

import com.ccstudy.qna.dto.account.AccountSaveRequestDto;
import com.ccstudy.qna.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {
    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService){
        this.accountService=accountService;
    }

    @PostMapping("/account")
    public String register(AccountSaveRequestDto accountSaveRequestDto){
        accountService.saveAccount(accountSaveRequestDto);
        return "redirect:/users";
    }


}
