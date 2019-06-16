package com.ccstudy.qna.controller.account;

import com.ccstudy.qna.domain.account.Account;
import com.ccstudy.qna.dto.account.AccountLoginRequestDto;
import com.ccstudy.qna.dto.account.AccountSaveRequestDto;
import com.ccstudy.qna.dto.account.AccountSessionDto;
import com.ccstudy.qna.dto.account.AccountUpdateRequestDto;
import com.ccstudy.qna.service.account.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final AccountService accountService;



    @PostMapping("/user")
    public String register(AccountSaveRequestDto accountSaveRequestDto) {
        accountService.saveAccount(accountSaveRequestDto);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String accountList(Model model) {
        model.addAttribute("accounts", accountService.findAll());
        return "account/list_view";
    }

    @GetMapping("/users/{id}/form")
    public String accountInfo(@PathVariable("id") Long id, Model model) {
        model.addAttribute("account", accountService.findById(id));
        return "account/info_update";
    }

    @PutMapping("/users/{id}")
    public String accountModify(@PathVariable("id") Long id, AccountUpdateRequestDto dto) {
        accountService.updateAccount(dto, id);
        return "redirect:/users";
    }

    @PostMapping("/login")
    public String login(AccountLoginRequestDto dto, HttpSession httpSession) {
        AccountSessionDto loginAccount = accountService.loginAccount(dto);
        httpSession.setAttribute("LOGIN_ACCOUNT", loginAccount);
        return "redirect:/";
    }


}
