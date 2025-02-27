package com.sdcconsulting.sessions.repository;

import com.sdcconsulting.sessions.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends PagingAndSortingRepository<Student, Long>, CrudRepository<Student, Long> {

    /*
        Note how, now that we extend the PagingAndSortingRepository, we have a base method that returns a Page.
        So the previous override that had for the findAll can be removed.
     */

    /*
        To get a page from our repository methods, we will need to pass a pageable to the method.
     */

    Page<Student> findAllByAddressesZip(String zip, Pageable pageable);

    @Query("SELECT s from Student s WHERE year(s.dateOfBirth) = :year")
    Page<Student> findAllBornInYear(final int year, Pageable pageable);

}
