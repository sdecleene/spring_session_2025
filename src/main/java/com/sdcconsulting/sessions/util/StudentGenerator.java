package com.sdcconsulting.sessions.util;

import com.github.javafaker.Faker;
import com.sdcconsulting.sessions.model.Address;
import com.sdcconsulting.sessions.model.AddressType;
import com.sdcconsulting.sessions.model.Name;
import com.sdcconsulting.sessions.model.Student;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;

public class StudentGenerator {

    private static final Faker FAKE_DATA_GENERATOR = new Faker();

    public static Student generateStudent() {
        return Student.builder()
                .dateOfBirth(utilDateToLocalDate(FAKE_DATA_GENERATOR.date().birthday()))
                .name(Name.builder()
                        .firstName(FAKE_DATA_GENERATOR.name().firstName())
                        .lastName(FAKE_DATA_GENERATOR.name().lastName())
                        .build())
                .addresses(Collections.singletonList(
                        Address.builder()
                                .addressType(AddressType.DOMICILE)
                                .street(FAKE_DATA_GENERATOR.address().streetName())
                                .number(FAKE_DATA_GENERATOR.address().streetAddressNumber())
                                .box(FAKE_DATA_GENERATOR.address().buildingNumber())
                                .city(FAKE_DATA_GENERATOR.address().cityName())
                                .zip(FAKE_DATA_GENERATOR.address().zipCode())
                                .build()
                ))
                .build();
    }

    private static LocalDate utilDateToLocalDate(final Date utilDate) {
        return utilDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

}
