package com.ccstudy.qna.controller;

import com.ccstudy.qna.dto.Account.*;
import com.ccstudy.qna.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @GetMapping("/{id}/form")
    public String getEditFormOfAccount(@PathVariable("id") Long id,
                                       @Valid AccountAuthDto accountAuthDto,Model model) {
        AccountResDto editAccount = accountService.findAccountById(id);
        model.addAttribute("account", editAccount);
        return "pages/userUpdateForm";
    }

    @PostMapping
    public String saveAccount(@Valid AccountSaveReqDto saveReqDto) {
        Long id = accountService.saveAccount(saveReqDto);
        log.info("save new user - id : " + id);
        return "redirect:/users";
    }

    @PutMapping("/{id}")
    public String updateAccount(@PathVariable("id") Long id,
                                @Valid AccountUpdateReqDto updateReqDto, @Valid AccountAuthDto accountAuthDto) {
        accountService.updateAccount(updateReqDto, id);
        log.info("update user - id : " + id);
        return "redirect:/users";
    }

    @PostMapping("/login")
    public void loginAccount(@Valid AccountLoginReqDto reqDto, Model model) {
        AccountAuthDto accountAuthDto = accountService.login(reqDto);
        model.addAttribute(AccountAuthDto.ATTRIBUTE_NAME, accountAuthDto);
    }

    @DeleteMapping("/logout")
    public String logoutAccount() {
        return "redirect:/";
    }

}
