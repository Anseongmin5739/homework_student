package study.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import study.homework.service.StudentService;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final StudentService studentService;

    @GetMapping("/")
    public String mainPage(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "main";
    }
}
