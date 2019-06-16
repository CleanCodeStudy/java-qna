package com.ccstudy.qna.controller.account;

import com.ccstudy.qna.dto.account.AccountSaveRequestDto;
import com.ccstudy.qna.dto.account.AccountUpdateRequestDto;
import com.ccstudy.qna.service.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/user")
    public String register(AccountSaveRequestDto accountSaveRequestDto){
        accountService.saveAccount(accountSaveRequestDto);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String accountList(Model model){
        model.addAttribute("accounts",accountService.findAll());
        return "account/list_view";
    }

    @GetMapping("/users/{id}/form")
    public String accountInfo(@PathVariable("id") Long id, Model model){
        model.addAttribute("account",accountService.findById(id));
        return "account/info_update";
    }

    @PutMapping("/users/{id}")
    public String accountModify(@PathVariable("id") Long id, AccountUpdateRequestDto dto) {
        accountService.updateAccount(dto, id);
        return "redirect:/users";
    }


}
