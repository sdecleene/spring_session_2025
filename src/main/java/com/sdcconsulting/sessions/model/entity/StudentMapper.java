package com.sdcconsulting.sessions.model.entity;

import com.sdcconsulting.sessions.model.Address;
import com.sdcconsulting.sessions.model.AddressType;
import com.sdcconsulting.sessions.model.Name;
import com.sdcconsulting.sessions.model.Student;

import java.util.List;
import java.util.stream.Collectors;

public class StudentMapper {

    public static Student toDomain(final StudentEntity studentEntity) {
        return Student.builder()
                .id(studentEntity.getId())
                .name(toDomain(studentEntity.getName()))
                .dateOfBirth(studentEntity.getDateOfBirth())
                .addresses(toDomain(studentEntity.getAddresses()))
                .build();
    }

    private static Name toDomain(final NameEntity nameEntity) {
        return Name.builder()
                .firstName(nameEntity.getFirstName())
                .lastName(nameEntity.getLastName())
                .build();
    }

    private static List<Address> toDomain(final List<AddressEntity> addressEntities) {
        return addressEntities.stream()
                .map(StudentMapper::toDomain)
                .collect(Collectors.toList());
    }

    private static Address toDomain(final AddressEntity addressEntity) {
        return Address.builder()
                .id(addressEntity.getId())
                .addressType(AddressType.valueOf(addressEntity.getAddressType()))
                .street(addressEntity.getStreet())
                .number(addressEntity.getNumber())
                .box(addressEntity.getBox())
                .city(addressEntity.getCity())
                .zip(addressEntity.getZip())
                .build();
    }

    public static StudentEntity toEntity(final Student student) {
        final StudentEntity studentEntity = StudentEntity.builder()
                .id(student.getId())
                .name(toEntity(student.getName()))
                .dateOfBirth(student.getDateOfBirth())
                .addresses(toEntity(student.getAddresses()))
                .active(true)
                .build();
        studentEntity.getAddresses().forEach(addressEntity -> addressEntity.setStudent(studentEntity));
        return studentEntity;
    }

    private static NameEntity toEntity(final Name name) {
        return NameEntity.builder()
                .firstName(name.getFirstName())
                .lastName(name.getLastName())
                .build();
    }

    private static List<AddressEntity> toEntity(final List<Address> addresses) {
        return addresses.stream()
                .map(StudentMapper::toEntity)
                .collect(Collectors.toList());
    }

    private static AddressEntity toEntity(final Address address) {
        return AddressEntity.builder()
                .id(address.getId())
                .addressType(address.getAddressType().name())
                .street(address.getStreet())
                .number(address.getNumber())
                .box(address.getBox())
                .city(address.getCity())
                .zip(address.getZip())
                .build();
    }

}
