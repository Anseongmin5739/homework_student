package study.homework.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import study.homework.dto.StudentDTO;
import study.homework.entity.Student;
import study.homework.service.StudentService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    // 학생 더미 데이터
    @PostConstruct
    public void init() {
        studentService.addStudent(new StudentDTO("대성진", "시흥", "카이스트", "G.O.A.T"));
        studentService.addStudent(new StudentDTO("노승빈", "노원", "육사", "프론트"));
        studentService.addStudent(new StudentDTO("김수정", "부천", "부천대", "Edit"));
        studentService.addStudent(new StudentDTO("김승민", "수원", "수원대", "sui"));
        studentService.addStudent(new StudentDTO("서준명", "서울", "서울대", "롤체학"));
        studentService.addStudent(new StudentDTO("안성민", "서울", "홍대", "미시시피"));
        studentService.addStudent(new StudentDTO("동재완", "???", "열공대", "행님"));
    }

    @GetMapping
    public String getAllStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "main";
    }

    // 학생 추가
    @PostMapping("/add")
    public String addStudent(@ModelAttribute StudentDTO studentDTO) {
        studentService.addStudent(studentDTO);
        return "redirect:/students";
    }

    // 학생 수정
    @PostMapping("/edit")
    public String editStudent(@RequestParam Long id, @ModelAttribute StudentDTO studentDTO) {
        studentService.getStudentById(id).ifPresent(existingStudent -> {
            existingStudent.setName(studentDTO.getName());
            existingStudent.setAddress(studentDTO.getAddress());
            existingStudent.setSchool(studentDTO.getSchool());
            existingStudent.setMajor(studentDTO.getMajor());
            studentService.editStudent(existingStudent);
        });
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        model.addAttribute("student", student);
        model.addAttribute("students", studentService.getAllStudents());
        return "main";
    }

    // 학생 삭제
    @PostMapping("/delete")
    public String deleteStudent(@RequestParam Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/students";
    }

    // 학생 검색
    @GetMapping("/search")
    public String searchStudents(@RequestParam String name, Model model) {
        model.addAttribute("students", studentService.searchStudentsByName(name));
        return "main";
    }
}
