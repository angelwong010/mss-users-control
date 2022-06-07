package com.cloudservices.mss.users.control.service;

import com.cloudservices.mss.users.control.api.request.LoginRequest;
import com.cloudservices.mss.users.control.api.request.NewUSerRequest;
import com.cloudservices.mss.users.control.dto.UserDto;

public interface UserService {

    UserDto createNewUser(NewUSerRequest request);

    UserDto login(LoginRequest request);

    UserDto getUser(Integer id);
}
