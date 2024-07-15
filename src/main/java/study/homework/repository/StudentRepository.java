package study.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.homework.entity.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByNameContainingOrderByNameAsc(String name);
}
