package com.ccstudy.qna.web.controller.account;

import com.ccstudy.qna.dto.account.AccountLoginRequestDto;
import com.ccstudy.qna.dto.account.AccountSaveRequestDto;
import com.ccstudy.qna.dto.account.AccountUpdateRequestDto;
import com.ccstudy.qna.dto.account.LoginAccount;
import com.ccstudy.qna.service.account.AccountService;
import com.ccstudy.qna.web.auth.LoginAccountSessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final AccountService accountService;

    private final LoginAccountSessionManager loginAccountSessionManager;

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
    public String login(@ModelAttribute("loginAccount") AccountLoginRequestDto dto, HttpServletRequest httpServletRequest) {
        LoginAccount loginAccount = accountService.loginAccount(dto);
        loginAccountSessionManager.saveSession(httpServletRequest, loginAccount);
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest) {
        loginAccountSessionManager.deleteSession(httpServletRequest);
        return "redirect:/";
    }

}

