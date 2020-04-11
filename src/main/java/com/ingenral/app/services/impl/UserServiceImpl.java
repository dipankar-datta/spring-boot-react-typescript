package com.ingenral.app.services.impl;

import com.ingenral.app.data.entities.Contact;
import com.ingenral.app.data.entities.User;
import com.ingenral.app.data.repositories.ContactRepository;
import com.ingenral.app.data.repositories.UserRepository;
import com.ingenral.app.exceptions.BadClientDataException;
import com.ingenral.app.exceptions.ExceptionsUtility;
import com.ingenral.app.rest.dtos.ContactDTO;
import com.ingenral.app.rest.dtos.UserDTO;
import com.ingenral.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ContactRepository contactRepository;

    @Override
    public List<User> list() {
        return userRepository.findAll();
    }

    @Override
    public User create(User item) {
        return userRepository.save(item);
    }

    @Override
    public User update(User item) {
        return userRepository.save(item);
    }

    @Override
    public <S extends User> Iterable<S> saveAll(Iterable<S> items) {
        return userRepository.saveAll(items);
    }

    @Override
    public Optional<User> getById(Long id) {
        return Optional.of(userRepository.getOne(id));
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void delete(User item) {
        userRepository.delete(item);
    }

    @Override
    public UserDTO createFromDto(UserDTO userDTO) {
        ExceptionsUtility.exceptionIfIdExistsForCreate(userDTO.getId());
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user = userRepository.save(user);

        Long userId = user.getId();
        List<ContactDTO> contactDTOS = null;

        if (userDTO.getContacts() != null && !userDTO.getContacts().isEmpty()) {
            List<Contact> contacts = userDTO.getContacts().stream()
                    .map(contactDTO -> {
                        ExceptionsUtility.exceptionIfIdExistsForCreate(contactDTO.getId());
                        Contact contact = new Contact();
                        contact.setAddressLine1(contactDTO.getAddressLine1());
                        contact.setAddressLine2(contactDTO.getAddressLine2());
                        contact.setPhoneNumber(contactDTO.getPhoneNumber());
                        contact.setPostalCode(contactDTO.getPostalCode());
                        contact.setUserId(userId);
                        return contact;
                    })
                    .collect(Collectors.toList());
            contactDTOS = contactRepository.saveAll(contacts).stream()
                    .map(contact -> {
                        ContactDTO contactDTO = new ContactDTO();
                        contactDTO.setAddressLine1(contact.getAddressLine1());
                        contactDTO.setAddressLine2(contact.getAddressLine2());
                        contactDTO.setPhoneNumber(contact.getPhoneNumber());
                        contactDTO.setPostalCode(contact.getPostalCode());
                        contactDTO.setUserId(userId);
                        return contactDTO;

                    })
                    .collect(Collectors.toList());

        }
        if (userId != null) {
            userDTO.setId(userId);
            if (contactDTOS != null) {
                userDTO.setContacts(contactDTOS);
            }
        }

        return userDTO;
    }

    @Override
    public UserDTO updateFromDto(Long userId, UserDTO userDTO) {
        ExceptionsUtility.exceptionIfIdDoesntExistForUpdate(userDTO.getId());
        if (userDTO.getId() == null || !userDTO.getId().equals(userId)) {
            throw new BadClientDataException("Invalid user data");
        }
        User savedUser = userRepository.getOne(userDTO.getId());
        savedUser.setFirstName(userDTO.getFirstName());
        savedUser.setLastName(userDTO.getLastName());
        userRepository.save(savedUser);

        if (userDTO.getContacts() != null && !userDTO.getContacts().isEmpty()) {
            List<Contact> contacts = userDTO.getContacts().stream()
                    .map(contactDTO -> {
                        ExceptionsUtility.exceptionIfIdDoesntExistForUpdate(contactDTO.getId());
                        Contact contact = new Contact();
                        contact.setAddressLine1(contactDTO.getAddressLine1());
                        contact.setAddressLine2(contactDTO.getAddressLine2());
                        contact.setPhoneNumber(contactDTO.getPhoneNumber());
                        contact.setPostalCode(contactDTO.getPostalCode());
                        return contact;
                    })
                    .collect(Collectors.toList());
            contactRepository.saveAll(contacts);
        }
        return userDTO;
    }

    @Override
    public UserDTO getUserDtoById(Long userId) {
        User user = userRepository.getOne(userId);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());

        if (user.getContacts() != null && !user.getContacts().isEmpty()) {
            List<ContactDTO> contactDTOS = user.getContacts().stream()
                    .map(contact -> {
                        ContactDTO contactDTO = new ContactDTO();
                        contactDTO.setAddressLine1(contact.getAddressLine1());
                        contactDTO.setAddressLine2(contact.getAddressLine2());
                        contactDTO.setPhoneNumber(contact.getPhoneNumber());
                        contactDTO.setPostalCode(contact.getPostalCode());
                        contactDTO.setUserId(userId);
                        return contactDTO;
                    })
                    .collect(Collectors.toList());
            userDTO.setContacts(contactDTOS);
        }
        return userDTO;
    }
}
