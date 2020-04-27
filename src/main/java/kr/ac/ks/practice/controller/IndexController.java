package kr.ac.ks.practice.controller;

import kr.ac.ks.practice.domain.Student;
import kr.ac.ks.practice.domain.dto.StudentDto;
import kr.ac.ks.practice.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Controller
public class IndexController {

    private final StudentRepository studentRepository;

    public IndexController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        Iterable<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "index";
    }

    @GetMapping("/add")
    public String showAddForm(StudentDto studentDto) {
        return "add-student";
    }

    @PostMapping("/add")
    public String addStudent(@Valid StudentDto studentDto, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("error");
            return "add-student";
        }
        Student student = new Student(studentDto.getName(), studentDto.getEmail(), studentDto.getPhoneNo());
        studentRepository.save(student);
        System.out.println(student.getName());
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        model.addAttribute("student", student);
        return "update-student";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(Student student) {
        studentRepository.save(student);
        return "redirect:/";
    }

    @GetMapping("delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id, Model model) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        studentRepository.delete(student);
        model.addAttribute("students", studentRepository.findAll());
        return "redirect:/";
    }
}