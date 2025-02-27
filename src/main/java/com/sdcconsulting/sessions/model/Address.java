package com.sdcconsulting.sessions.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class Address {

    private AddressType addressType;
    private String street;
    private String number;
    private String box;
    private String city;
    private String zip;

}
