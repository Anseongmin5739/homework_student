package study.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.homework.dto.StudentDTO;
import study.homework.entity.Student;
import study.homework.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student addStudent(StudentDTO studentDTO) {
        Student student = new Student(studentDTO.getName(), studentDTO.getAddress(), studentDTO.getSchool(), studentDTO.getMajor());
        return studentRepository.save(student);
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> searchStudentsByName(String name) {
        return studentRepository.findByNameContainingOrderByNameAsc(name);
    }

    public void editStudent(Student student) {
        studentRepository.save(student);
    }
}
