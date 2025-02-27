package com.sdcconsulting.sessions.model.view;

import com.sdcconsulting.sessions.model.Address;
import com.sdcconsulting.sessions.model.AddressType;
import com.sdcconsulting.sessions.model.Name;
import com.sdcconsulting.sessions.model.Student;

import java.util.List;
import java.util.stream.Collectors;

public class StudentMapper {

    public static Student toDomain(final StudentView studentView) {
        return Student.builder()
                .id(studentView.getId())
                .name(toDomain(studentView.getName()))
                .dateOfBirth(studentView.getDateOfBirth())
                .addresses(toDomain(studentView.getAddresses()))
                .build();
    }

    private static Name toDomain(final NameView nameView) {
        return Name.builder()
                .firstName(nameView.getFirstName())
                .lastName(nameView.getLastName())
                .build();
    }

    private static List<Address> toDomain(final List<AddressView> addressViews) {
        return addressViews.stream()
                .map(StudentMapper::toDomain)
                .collect(Collectors.toList());
    }

    private static Address toDomain(final AddressView addressView) {
        return Address.builder()
                .id(addressView.getId())
                .addressType(AddressType.valueOf(addressView.getAddressType()))
                .street(addressView.getStreet())
                .number(addressView.getNumber())
                .box(addressView.getBox())
                .city(addressView.getCity())
                .zip(addressView.getZip())
                .build();
    }

    public static StudentView toView(final Student student) {
        return StudentView.builder()
                .id(student.getId())
                .name(toView(student.getName()))
                .dateOfBirth(student.getDateOfBirth())
                .addresses(toView(student.getAddresses()))
                .build();
    }

    private static NameView toView(final Name name) {
        return NameView.builder()
                .firstName(name.getFirstName())
                .lastName(name.getLastName())
                .build();
    }

    private static List<AddressView> toView(final List<Address> addresses) {
        return addresses.stream()
                .map(StudentMapper::toView)
                .collect(Collectors.toList());
    }

    private static AddressView toView(final Address address) {
        return AddressView.builder()
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
