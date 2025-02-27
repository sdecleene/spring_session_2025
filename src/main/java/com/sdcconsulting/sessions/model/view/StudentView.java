package com.sdcconsulting.sessions.model.view;

import com.sdcconsulting.sessions.model.validation.ValidDateOfBirth;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentView {

    private Long id;

    @Valid
    @NotNull
    private NameView name;

    @ValidDateOfBirth
    private LocalDate dateOfBirth;

    @NotEmpty
    private List<AddressView> addresses;

}
