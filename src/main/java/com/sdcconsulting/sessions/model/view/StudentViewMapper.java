package com.sdcconsulting.sessions.model.view;

import com.sdcconsulting.sessions.model.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentViewMapper {

    StudentView toView(Student student);

    Student toDomain(StudentView studentView);

}
