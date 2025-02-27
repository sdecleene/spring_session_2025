package com.sdcconsulting.sessions.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class Student {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private List<Address> addresses;

}
