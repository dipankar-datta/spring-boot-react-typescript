package com.ingenral.app.rest.controllers;

import com.ingenral.app.data.entities.Contact;
import com.ingenral.app.data.entities.User;
import com.ingenral.app.exceptions.BadClientDataException;
import com.ingenral.app.rest.dtos.ContactDTO;
import com.ingenral.app.rest.dtos.UserDTO;
import com.ingenral.app.services.ContactService;
import com.ingenral.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ContactService contactService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> usersList() {
        return userService.list();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public UserDTO createUser(@Valid @RequestBody UserDTO userDTO) {
        return userService.createFromDto(userDTO);
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO updateUser(@PathVariable Long userId, @Valid @RequestBody UserDTO userDTO) {
        return userService.updateFromDto(userId, userDTO);
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO findUser(@PathVariable Long userId) {
        return userService.getUserDtoById(userId);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteById(userId);
    }

    @GetMapping("/{userId}/contacts")
    @ResponseStatus(HttpStatus.OK)
    public List<ContactDTO> getUserContacts(@PathVariable Long userId) {
        return contactService.getContactDtosByUserId(userId);
    }

    @PostMapping("/{userId}/contacts")
    @ResponseStatus(HttpStatus.OK)
    public ContactDTO createUserContact(@PathVariable Long userId, @Valid @RequestBody ContactDTO contactDTO) {
        return contactService.createUserContactFromDto(userId, contactDTO);
    }

//    @PutMapping("/{userId}/contacts")
//    @ResponseStatus(HttpStatus.OK)
//    public ContactDTO updateUserContact(@PathVariable Long userId, @Valid @RequestBody ContactDTO contactDTO) {
//        return contactService.updateUserContactFromDto(userId, contactDTO);
//    }

    @PutMapping("/{userId}/contacts/{contactId}")
    @ResponseStatus(HttpStatus.OK)
    public ContactDTO updateUserContact(@PathVariable Long userId, @PathVariable Long contactId, @RequestBody ContactDTO contactDTO) {
        return contactService.updateUserContactFromDto(userId, contactDTO);
    }

    @GetMapping("/{userId}/contacts/{contactId}")
    @ResponseStatus(HttpStatus.OK)
    public ContactDTO getUserContactById(@PathVariable Long userId, @PathVariable Long contactId) {
        return contactService.getUserContactDtoById(userId, contactId);
    }

    @DeleteMapping("/{userId}/contacts/{contactId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserContactById(@PathVariable Long userId, @PathVariable Long contactId) {
        contactService.deleteUserContactById(userId, contactId);
    }


}
