package com.sdcconsulting.sessions.controller;

import com.sdcconsulting.sessions.model.Student;
import com.sdcconsulting.sessions.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.sdcconsulting.sessions.util.StudentGenerator.generateStudent;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{student_id}")
    public ResponseEntity<Student> getStudent(
            @PathVariable(name = "student_id") final long studentId
    ) {
        final Student student = studentService.getStudent(studentId);
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public ResponseEntity<Page<Student>> getStudents(
            @PageableDefault(value = 20, page = 0) @SortDefault(sort = "name.firstName", direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(name = "city_zip", required = false) final String cityZip,
            @RequestParam(name = "year_of_birth", required = false) final Integer yearOfBirth
    ) {
        final Page<Student> students;
        if (cityZip != null) {
            students = studentService.getStudentsWithAddressInCity(cityZip, pageable);
        } else if (yearOfBirth != null) {
            students = studentService.getStudentsBornInYear(yearOfBirth, pageable);
        } else {
            students = studentService.getAllStudents(pageable);
        }
        return ResponseEntity.ok(students);
    }

    @PostMapping
    public ResponseEntity<Void> addStudent(
            /*
                The @Valid annotation triggers all the validators (both existing and custom)
             */
            @Validated @RequestBody final Student student
    ) {
        /*
            Note that for insurance purposes, we also set the student field in the addresses.
             This way we don't have to do it in the HTTP request.
             This could also easily be aligned with the frontend.
         */
        student.getAddresses().forEach(address -> address.setStudent(student));
        studentService.addStudent(student);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/generate")
    public ResponseEntity<Void> addGeneratedStudent() {
        final Student generatedStudent = generateStudent();
        studentService.addStudent(generatedStudent);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{student_id}")
    public ResponseEntity<Void> updateStudent(
            @PathVariable(name = "student_id") final long studentId,
            @Validated @RequestBody final Student student
    ) {
        student.getAddresses().forEach(address -> address.setStudent(student));
        studentService.updateStudent(studentId, student);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{student_id}")
    public ResponseEntity<Void> deleteStudent(
            @PathVariable(name = "student_id") final long studentId
    ) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllStudents() {
        studentService.deleteAllStudents();
        return ResponseEntity.noContent().build();
    }

}
