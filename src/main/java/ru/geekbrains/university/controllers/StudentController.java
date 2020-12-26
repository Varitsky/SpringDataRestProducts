package ru.geekbrains.university.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.university.model.Student;
import ru.geekbrains.university.repositories.StudentRepository;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentRepository studentRepository;
    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentRepository.findById(id).get();
    }

    @GetMapping("/search_by_name")
    public Student searchByName(@RequestParam String name) {
        return studentRepository.findByName(name).get();
    }


    //http://localhost:8189/geek/students/search_by_min_score?min_score=85
    @GetMapping("/search_by_min_score")
    public List<Student> searchByMinScore(@RequestParam(name = "min_score") Integer minScore){
        return studentRepository.findAllByScoreIsGreaterThanEqual(minScore);
    }


    //Вызов метода с Query. Ошибка 500 если 1 Bob не наёден. ('Bob', 80),('John', 70),('Jack', 90);
    @GetMapping("/test")
    public Student testMethod() {
        return studentRepository.customFinderById(1L, "Bob").get();
    }


    //в теле запроса json, джексон преобразует его в объектом. Запускать через Postman.
    @PostMapping
    public Student save(@RequestBody Student student) {
//         student.setId(null);
        return studentRepository.save(student);
    }
}
