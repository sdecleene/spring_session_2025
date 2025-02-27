package com.sdcconsulting.sessions;

import com.sdcconsulting.sessions.model.Address;
import com.sdcconsulting.sessions.model.Student;
import com.sdcconsulting.sessions.service.AddressService;
import com.sdcconsulting.sessions.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.sdcconsulting.sessions.util.StudentGenerator.generateStudent;
import static com.sdcconsulting.sessions.util.StudentGenerator.generateStudentRoomAddress;

@Slf4j
@SpringBootApplication
public class Spring2025Application {

    public static void main(String[] args) {
        final ApplicationContext context = SpringApplication.run(Spring2025Application.class, args);

        /*
            The only way to interface with the application it here through the application context.
             Once we implement the web layer, we can comfortably do it through there.
         */

        log.info("\n\n\n\n\n\n");

        final StudentService studentService = context.getBean(StudentService.class);
        final AddressService addressService = context.getBean(AddressService.class);

        /*
            We'll start with adding 10 students to the database.
         */

        List<Student> students = studentService.getAllStudents();
        log.info("number of initial students: {}", students.size());

        final List<Student> studentsToAdd = IntStream.range(0, 10)
                .mapToObj(index -> generateStudent())
                .collect(Collectors.toList());

        studentService.addStudents(studentsToAdd);

        students = studentService.getAllStudents();
        log.info("number of students after inserting: {}", students.size());
        log.info("students after inserting:");
        students.forEach(student -> log.info(student.toString()));
        log.info("");

        /*
            In the following scenario we will see what happens if we try to reinsert a student after changing a single field.
             We will not change the id of the student.
         */

        final Student studentToReinsert = students.getFirst();
        studentToReinsert.getName().setFirstName("Henk");
        studentService.addStudent(studentToReinsert);

        students = studentService.getAllStudents();
        log.info("number of students after reinsert: {}", students.size());
        log.info("students after reinsert:");
        students.forEach(student -> log.info(student.toString()));
        log.info("");

        /*
            Surprisingly, Spring did not add a new student, but it updates the existing student.
             This is because both of the insert and update are done via the repository.save method.
             If you want to block this behaviour, you will need to write custom code.
             Next up, we'll properly update a student.
         */

        final Student studentToUpdate = students.get(1);
        studentToUpdate.getAddresses().add(generateStudentRoomAddress(studentToUpdate));
        studentService.updateStudent(studentToUpdate.getId(), studentToUpdate);

        students = studentService.getAllStudents();
        log.info("number of students after update: {}", students.size());
        log.info("students after update:");
        students.forEach(student -> log.info(student.toString()));
        log.info("");

        /*
            Now, we'll delete a student.
         */
        List<Address> addresses = addressService.getAllAddresses();
        log.info("number of addresses before delete: {}", addresses.size());

        final Long studentIdToDelete = students.getLast().getId();
        studentService.deleteStudent(studentIdToDelete);

        students = studentService.getAllStudents();
        log.info("number of students after delete: {}", students.size());
        log.info("students after delete:");
        students.forEach(student -> log.info(student.toString()));

        addresses = addressService.getAllAddresses();
        log.info("number of addresses after delete: {}", addresses.size());
        log.info("");

        /*
            As you can see, the linked addresses were automatically removed as well.
            This is because of the cascade = CascadeType.ALL on the addresses field in the Student class.
            Next up, we'll test our conditional student retrieval methods.
            First up, we'll get all students born in a certain year. (the custom query)
         */
        final int birthYear = students.getFirst().getDateOfBirth().getYear();
        final String cityZip = students.getFirst().getAddresses().getFirst().getZip();

        students = studentService.getStudentsBornInYear(birthYear);
        log.info("number of students born in {}: {}", birthYear, students.size());
        log.info("students born in {}:", birthYear);
        students.forEach(student -> log.info(student.toString()));
        log.info("");

        /*
            Next up, students with an address in a specific city.
         */
        students = studentService.getStudentsWithAddressInCity(cityZip);
        log.info("number of students from zip {}: {}", cityZip, students.size());
        log.info("students from zip {}:", cityZip);
        students.forEach(student -> log.info(student.toString()));
        log.info("");

        log.info("\n\n\n\n\n\n");
    }

}
