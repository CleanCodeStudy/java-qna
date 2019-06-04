package com.ccstudy.qna.controller.account;

import com.ccstudy.qna.dto.account.AccountSaveRequestDto;
import com.ccstudy.qna.service.account.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class AccountController {

    private AccountService accountService;

    @PostMapping("/account")
    public String register(AccountSaveRequestDto accountSaveRequestDto){
        accountService.saveAccount(accountSaveRequestDto);
        return "redirect:/users";
    }


}
