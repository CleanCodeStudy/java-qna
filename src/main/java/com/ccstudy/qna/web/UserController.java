package com.ccstudy.qna.web;

import com.ccstudy.qna.service.UserService;
import com.ccstudy.qna.service.dto.user.UserDetailInfo;
import com.ccstudy.qna.service.dto.user.UserRequestDto;
import com.ccstudy.qna.service.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public String saveUser(@Valid UserRequestDto requestDto) {
        userService.save(requestDto);
        return "redirect:/";
    }

    @GetMapping("/{id}/form")
    public String updateForm(@PathVariable("id") Long userId, Model model) {
        UserDetailInfo userDetailInfo = userService.findById(userId);
        model.addAttribute("user", userDetailInfo);
        return "/update";
    }

    @PutMapping
    public String updateUser(@Valid UserUpdateDto updateDto) {
        userService.update(updateDto);
        return "redirect:/users";
    }

    @GetMapping
    public String showUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "/user-list";
    }
}
