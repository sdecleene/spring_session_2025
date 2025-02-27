package com.sdcconsulting.sessions.model.view;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NameView {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

}
