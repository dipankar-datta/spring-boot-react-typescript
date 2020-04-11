package com.ingenral.app.services;

import com.ingenral.app.data.entities.Contact;
import com.ingenral.app.rest.dtos.ContactDTO;

import java.util.List;

public interface ContactService extends BasicCrudService<Contact> {

    List<ContactDTO> getContactDtosByUserId(Long userId);

    ContactDTO createUserContactFromDto(Long userId, ContactDTO contactDTO);

    ContactDTO updateUserContactFromDto(Long userId, ContactDTO contactDTO);

    void deleteUserContactById(Long userId, Long contactId);

    ContactDTO getUserContactDtoById(Long userId, Long contactId);
}
