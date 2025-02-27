package com.sdcconsulting.sessions.repository;

import com.sdcconsulting.sessions.model.Address;
import jakarta.annotation.Nonnull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {

    @Nonnull
    @Override
    List<Address> findAll();

}
