package com.ingenral.app.services.impl;

import com.ingenral.app.data.entities.Contact;
import com.ingenral.app.data.repositories.ContactRepository;
import com.ingenral.app.exceptions.BadClientDataException;
import com.ingenral.app.exceptions.ExceptionsUtility;
import com.ingenral.app.rest.dtos.ContactDTO;
import com.ingenral.app.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public ContactDTO getUserContactDtoById(Long userId, Long contactId) {
        Optional<Contact> contactOptional = contactRepository.findById(contactId);
        ContactDTO contactDTO = null;
        if (contactOptional.isPresent()) {
            Contact contact = contactOptional.get();
            if (contact.getUserId().equals(userId)) {
                contactDTO = ContactDTO.entityToDto(contact);
            } else {
                throw new BadClientDataException("Invalid data request");
            }
        }
        return contactDTO;
    }

    @Override
    public void deleteUserContactById(Long userId, Long contactId) {
        Optional<Contact> contactOptional = contactRepository.findById(contactId);
        if (contactOptional.isPresent() && contactOptional.get().getUserId().equals(userId)) {
            contactRepository.deleteById(contactId);
        } else {
            throw new BadClientDataException("Invalid contact data provided");
        }
    }

    @Override
    public ContactDTO updateUserContactFromDto(Long userId, ContactDTO contactDTO) {
        ExceptionsUtility.exceptionIfIdDoesntExistForUpdate(contactDTO);
        Contact contact = contactRepository.getOne(contactDTO.getId());
        if (contact == null || !contact.getUserId().equals(userId)) {
            throw new BadClientDataException("Invalid data provided");
        }
        contact.setAddressLine1(contactDTO.getAddressLine1());
        contact.setAddressLine2(contactDTO.getAddressLine2());
        contact.setPhoneNumber(contactDTO.getPhoneNumber());
        contact.setPostalCode(contactDTO.getPostalCode());
        contactRepository.save(contact);
        return contactDTO;
    }

    @Override
    public ContactDTO createUserContactFromDto(Long userId, ContactDTO contactDTO) {
        ExceptionsUtility.exceptionIfIdExistsForCreate(contactDTO);
        Contact contact = contactDTO.toEntity();
        contact = contactRepository.save(contact);
        contactDTO.setId(contact.getId());
        return contactDTO;
    }

    @Override
    public List<ContactDTO> getContactDtosByUserId(Long userId) {
        return contactRepository.getContactsByUserId(userId).stream()
                .map(ContactDTO::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Contact> list() {
        return contactRepository.findAll();
    }

    @Override
    public Contact create(Contact item) {
        return contactRepository.save(item);
    }

    @Override
    public Contact update(Contact item) {
        return contactRepository.save(item);
    }

    @Override
    public <S extends Contact> Iterable<S> saveAll(Iterable<S> items) {
        return contactRepository.saveAll(items);
    }

    @Override
    public Optional<Contact> getById(Long id) {
        return contactRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        contactRepository.deleteById(id);
    }

    @Override
    public void delete(Contact item) {
        contactRepository.delete(item);
    }
}
