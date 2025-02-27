package com.sdcconsulting.sessions.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sdcconsulting.sessions.model.validation.ValidDateOfBirth;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @NotNull
    @Embedded
    private Name name;

    @ValidDateOfBirth
    private LocalDate dateOfBirth;

    /*
        Note that we need to ignore this field during JSON (de)serialization, otherwise the client could un-delete students.
         A proper fix for this issue comes in the next branch.
     */
    @JsonIgnore
    private boolean active = true;

    @NotEmpty
    @OneToMany(
            mappedBy = "student",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private List<Address> addresses;

}
