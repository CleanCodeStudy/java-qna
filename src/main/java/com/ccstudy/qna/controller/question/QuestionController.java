package com.ccstudy.qna.controller.question;


import com.ccstudy.qna.dto.question.QuestionUpdateRequestDto;
import com.ccstudy.qna.dto.question.QuestionSaveRequestDto;
import com.ccstudy.qna.service.question.QuestionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@Slf4j
public class QuestionController {

    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("questions", questionService.showQuestions());
        return "index";
    }

    @PostMapping("/questions")
    public String saveQuestion(QuestionSaveRequestDto dto) {
        questionService.save(dto);
        return "redirect:/";
    }

    @GetMapping("/questions/{id}")
    public String showQuestionDetail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("question", questionService.showQuestionDetail(id));
        return "question/detail_view";
    }

    @GetMapping("/question/updateForm/{id}")
    public String questionUpdateForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("question", questionService.showQuestionDetail(id));
        return "question/form_update";
    }

    @PutMapping("/questions/{id}")
    public String update(QuestionUpdateRequestDto dto){
        Long updateId = questionService.updateQuestion(dto);
        return "redirect:/questions/"+updateId;
    }
    @DeleteMapping("/questions/{id}")
    public String delete(@PathVariable("id") Long id){
        questionService.delete(id);
        return "redirect:/";
    }

}
