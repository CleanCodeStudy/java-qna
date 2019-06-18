package com.ccstudy.qna.controller;

import com.ccstudy.qna.dto.Account.AccountResDto;
import com.ccstudy.qna.dto.Account.AccountSaveReqDto;
import com.ccstudy.qna.dto.Account.AccountUpdateReqDto;
import com.ccstudy.qna.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class AccountController {

    private final AccountService accountService;

    @GetMapping("")
    public String getAllAccounts(Model model) {
        model.addAttribute("accounts", accountService.getAllAccounts());
        return "pages/users";
    }

    @GetMapping("/{id}")
    public String getEditFormOfAccount(@PathVariable("id") Long id, Model model) {
        AccountResDto editAccount = accountService.findAccountById(id);
        model.addAttribute("account", editAccount);
        return "pages/userUpdateForm";
    }

    @PostMapping
    public String saveAccount(AccountSaveReqDto saveReqDto) {
        Long id = accountService.saveAccount(saveReqDto);
        log.info("save new user - id : " + id);
        return "redirect:/users";
    }

    @PutMapping("/{id}")
    public String updateAccount(@PathVariable("id") Long id, AccountUpdateReqDto updateReqDto) {
        accountService.updateAccount(updateReqDto, id);
        log.info("update user - id : " + id);
        return "redirect:/users";
    }


}
