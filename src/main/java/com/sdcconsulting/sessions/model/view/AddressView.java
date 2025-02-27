package com.sdcconsulting.sessions.model.view;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressView {

    private Long id;

    @NotNull
    private String addressType;

    @NotBlank
    private String street;

    @NotBlank
    private String number;

    private String box;

    @NotBlank
    private String city;

    @NotBlank
    private String zip;

}
