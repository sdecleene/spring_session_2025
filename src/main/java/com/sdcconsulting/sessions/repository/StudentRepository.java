package com.sdcconsulting.sessions.repository;

import com.sdcconsulting.sessions.model.entity.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends PagingAndSortingRepository<StudentEntity, Long>, CrudRepository<StudentEntity, Long> {

    /*
        Note how, now that we extend the PagingAndSortingRepository, we have a base method that returns a Page.
        So the previous override that had for the findAll can be removed.
     */

    /*
        To get a page from our repository methods, we will need to pass a pageable to the method.
     */
    Page<StudentEntity> findAllByActiveTrue(Pageable pageable);

    Page<StudentEntity> findAllByAddressesZipAndActiveTrue(String zip, Pageable pageable);

    @Query("SELECT s from StudentEntity s WHERE year(s.dateOfBirth) = :year and active = true")
    Page<StudentEntity> findAllBornInYearAndActiveTrue(final int year, Pageable pageable);

    /*
        Notice how this time we do need to add the @Modifying here, as well as the @Transactional on the Service methods, since we are now using custom queries.
     */
    @Modifying
    @Query("UPDATE StudentEntity s SET s.active = FALSE WHERE s.id = :id")
    void softDeleteById(long id);

    @Modifying
    @Query("UPDATE StudentEntity s SET s.active = FALSE")
    void softDeleteAll();

}
