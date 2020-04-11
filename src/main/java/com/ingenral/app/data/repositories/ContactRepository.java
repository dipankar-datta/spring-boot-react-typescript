package com.ingenral.app.data.repositories;

import com.ingenral.app.data.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query("select c from Contact c where c.userId = :userId")
    Set<Contact> getContactsByUserId(@Param(value = "userId") Long userId);
}
