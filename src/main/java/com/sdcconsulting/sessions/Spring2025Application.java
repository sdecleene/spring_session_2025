package com.sdcconsulting.sessions;

import com.sdcconsulting.sessions.model.Student;
import com.sdcconsulting.sessions.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@Slf4j
@SpringBootApplication
public class Spring2025Application {

    public static void main(String[] args) {
        final ApplicationContext context = SpringApplication.run(Spring2025Application.class, args);

        /*
            The only way to interface with the application it here through the application context.
             Once we implement the web layer, we can comfortably do it through there.
         */

        final StudentService service = context.getBean(StudentService.class);
        final List<Student> students = service.getAllStudents();
        log.info("Students:");
        students.forEach(student -> log.info(student.toString()));
    }

}
