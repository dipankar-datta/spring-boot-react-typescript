package com.ingenral.app.services;

import com.ingenral.app.data.entities.User;
import com.ingenral.app.rest.dtos.UserDTO;

public interface UserService extends BasicCrudService<User> {

    UserDTO createFromDto(UserDTO userDTO);

    UserDTO updateFromDto(Long userId, UserDTO userDTO);

    UserDTO getUserDtoById(Long userId);
}
