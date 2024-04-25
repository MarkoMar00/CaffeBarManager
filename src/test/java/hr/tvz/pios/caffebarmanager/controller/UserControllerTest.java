package hr.tvz.pios.caffebarmanager.controller;

import hr.tvz.pios.caffebarmanager.dto.UserDto;
import hr.tvz.pios.caffebarmanager.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserControllerTest {
    @Mock
    private UserService userService;
    @InjectMocks
    private UserController userController;
    private List<UserDto> userList;
    private UserDto userDto;
    private ResponseEntity<UserDto> userResponseEntityCreated;
    private ResponseEntity<UserDto> userResponseEntityOk;
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        userList = new ArrayList<>();
        userDto = new UserDto(1L, "Roko", "Ročić", "roko@example.com", "password", 1);
        userList.add(userDto);
        userResponseEntityCreated = new ResponseEntity<>(userDto, HttpStatus.CREATED);
        userResponseEntityOk = new ResponseEntity<>(userDto, HttpStatus.OK);
    }
    @AfterEach
    void tearDown() {
    }
    @Test
    public void getAllUsersTest() {
        when(userService.findAllUsers()).thenReturn(userList);
        List<UserDto> response = userController.getAllUsers();
        assertEquals(userList, response);
    }
    @Test
    public void getUserByEmailTest() {
        when(userService.findByEmail(userDto.getEmail())).thenReturn(Optional.of(userDto));
        ResponseEntity<UserDto> response = userController.getUserByEmail("roko@example.com");
        assertEquals(userResponseEntityOk, response);
    }
    @Test
    public void saveUserTest() {
        when(userService.save(userDto)).thenReturn(Optional.of(userDto));
        ResponseEntity<UserDto> response = userController.saveUser(userDto);
        assertEquals(userResponseEntityCreated, response);
    }
}