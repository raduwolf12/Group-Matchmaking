package com.example.demo.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.model.dto.UserRequestDto;
import com.example.demo.model.dto.UserResponseDto;
import com.example.demo.model.entity.User;
import com.example.demo.model.entity.enums.Role;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.validation.exception.UserNotFoundException;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void createUserTest() {
        UserRequestDto user = new UserRequestDto();
        user.setEmail("test@exemple.com");
        user.setGroupId(null);
        user.setCanvasUserId(1L);
        user.setPreferenceId(null);
        user.setRole(Role.STUDENT);
        user.setUserId(1L);

        UserResponseDto userResponseDto = userService.createUser(user);

        assert(userResponseDto.getEmail().equals("test@exemple.com"));
        assert(userResponseDto.getGroupId() == null);
        assert(userResponseDto.getCanvasUserId() == 1L);
        assert(userResponseDto.getPreferenceId() == null);
        assert(userResponseDto.getRole() == Role.STUDENT);
        assert(userResponseDto.getUserId() == 1L);
        verify(userRepository, atLeastOnce()).save(any(User.class));
    }

    @Test
    public void getAllUsersTest() {
        User user = new User();
        user.setEmail("test@exemple.com");
        user.setPassword("test");
        
        when(userRepository.findAll()).thenReturn(List.of(user));

        List<UserResponseDto> users = userService.getAllUsers();
        assert( users.size() == 1);
        assert( users.get(0).getEmail().equals("test@exemple.com"));
        assert(!users.get(0).getPassword().equals("test"));
    }

    @Test
    public void getUserByIDTest() throws UserNotFoundException {
        User user = new User();
        user.setEmail("test@exemple.com");
        user.setUserId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserResponseDto userResponseDto = userService.getUserById(1L);
        assert(userResponseDto.getEmail().equals("test@exemple.com"));
    }

    @Test
    public void getUserByIDTestFail() {
        try {
            when(userRepository.findById(1L)).thenReturn(Optional.empty());
            userService.getUserById(1L);
            assert(false);
        } catch (UserNotFoundException e) {
            assert(true);
        }
    }

    @Test
    public void updateUserTest() throws UserNotFoundException {
        User user = new User();
        user.setEmail("test@exemple.com");
        user.setUserId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setEmail("new@exemple.com");
        userRequestDto.setUserId(1L);

        UserResponseDto userResponseDto = userService.updateUser(userRequestDto);
        assert(userResponseDto.getEmail().equals("new@exemple.com"));
        verify(userRepository).save(user);
    }

    @Test
    public void updateUserTestFail() {
        try {
            UserRequestDto userRequestDto = new UserRequestDto();
            userRequestDto.setEmail("test@exemple.com");
            userRequestDto.setUserId(1L);

            when(userRepository.findById(1L)).thenReturn(Optional.empty());

            userService.updateUser(userRequestDto);
            assert(false);
        } catch (UserNotFoundException e) {
            assert(true);
        }
    }

    @Test
    public void deleteUserByIdTest() throws UserNotFoundException {
        User user = new User();
        user.setEmail("test@exemple.com");
        user.setUserId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        userService.deleteUserById(1L);

        verify(userRepository).delete(any(User.class));
    }

    @Test
    public void deleteUserByIdTestFail() {
        try {
            when(userRepository.findById(1L)).thenReturn(Optional.empty());
            userService.deleteUserById(1L);
            assert(false);
        } catch (UserNotFoundException e) {
            assert(true);
        }
    }

    @Test
    public void deleteAllUsersTest() {
        userService.deleteAllUsers();
        verify(userRepository).deleteAll();
    }
}
