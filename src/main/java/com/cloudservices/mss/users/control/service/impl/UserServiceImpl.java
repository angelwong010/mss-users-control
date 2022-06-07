package com.cloudservices.mss.users.control.service.impl;

import com.cloudservices.mss.users.control.api.request.LoginRequest;
import com.cloudservices.mss.users.control.api.request.NewUSerRequest;
import com.cloudservices.mss.users.control.dto.UserDto;
import com.cloudservices.mss.users.control.entity.User;
import com.cloudservices.mss.users.control.repository.UserRepository;
import com.cloudservices.mss.users.control.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private static final String INVALIDCREDENTIALS = "Invalid username/password supplied";

    private final transient UserRepository userRepository;
    private final transient ModelMapper modelMapper;
    private  transient PasswordEncoder passwordEncoder;

    public UserServiceImpl(final UserRepository userRepository,
                           final ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDto createNewUser(NewUSerRequest request) {
        log.info("Creating new user: {}", request);

        final Optional<User> foundUser = userRepository.findUserByEmail(request.getEmail());
        if (foundUser.isPresent()) {
            //Check if the mail is already in use
            //if so, then throw a response status exception whit http code 409
            log.trace("Duplicated email");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Duplicate email");
        }
        //if the email is new, then create and save the new user
        final User user = modelMapper.map(request,User.class);
        //encrypt the user password
        final String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        final User newUser = userRepository.save(user);
        return mapUserToResource(newUser);
    }

    @Override
    public UserDto login(LoginRequest loginRequest) {
        final Optional<User> optUser = userRepository.findUserByEmail(loginRequest.getEmail());
        return optUser.map(user -> verifyPassword(user,loginRequest)).orElseThrow(()->{
            return new ResponseStatusException(HttpStatus.UNAUTHORIZED,INVALIDCREDENTIALS);
        });
    }

    @Override
    public UserDto getUser(Integer id) {
        final Optional<User> optUser = userRepository.findById(id);
        return optUser.map(this::mapUserToResource).orElseThrow(()->{
            return new ResponseStatusException(HttpStatus.NOT_FOUND,"User not Found");
        });
    }

    private UserDto mapUserToResource(final User user){
        return modelMapper.map(user, UserDto.class);
    }

    private UserDto verifyPassword(final User user, final LoginRequest loginRequest){
        final String md5Password = passwordEncoder.encode(loginRequest.getPassword());
        if (md5Password.equals(user.getPassword())){
            return mapUserToResource(user);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,INVALIDCREDENTIALS);
    }
}
