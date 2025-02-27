package com.sdcconsulting.sessions.service;

import com.github.javafaker.Faker;
import com.sdcconsulting.sessions.model.Address;
import com.sdcconsulting.sessions.model.AddressType;
import com.sdcconsulting.sessions.model.Student;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class StudentService {

    private static final Faker FAKE_DATA_GENERATOR = new Faker();

    private static final Random RANDOM = new Random();

    public List<Student> getAllStudents() {
        return IntStream.range(0, 10)
                .mapToObj(index -> generateStudent())
                .collect(Collectors.toList());
    }

    /*
        Since we don't have any persistence layer yet, we'll generate some fake data using the Java Faker dependency.
     */
    private Student generateStudent() {
        return Student.builder()
                .firstName(FAKE_DATA_GENERATOR.name().firstName())
                .lastName(FAKE_DATA_GENERATOR.name().lastName())
                .dateOfBirth(utilDateToLocalDate(FAKE_DATA_GENERATOR.date().birthday()))
                .addresses(Collections.singletonList(
                        Address.builder()
                                .addressType(generateAddressType())
                                .street(FAKE_DATA_GENERATOR.address().streetName())
                                .number(FAKE_DATA_GENERATOR.address().streetAddressNumber())
                                .box(FAKE_DATA_GENERATOR.address().buildingNumber())
                                .city(FAKE_DATA_GENERATOR.address().cityName())
                                .zip(FAKE_DATA_GENERATOR.address().zipCode())
                                .build()
                ))
                .build();
    }

    private AddressType generateAddressType() {
        AddressType[] addressTypes = AddressType.values();
        return addressTypes[RANDOM.nextInt(addressTypes.length)];
    }

    private LocalDate utilDateToLocalDate(final Date utilDate) {
        return utilDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

}
