package com.sdcconsulting.sessions.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private Long id;
    private AddressType addressType;
    private String street;
    private String number;
    private String box;
    private String city;
    private String zip;

}
