package hr.tvz.pios.caffebarmanager.service.impl;

import hr.tvz.pios.caffebarmanager.domain.User;
import hr.tvz.pios.caffebarmanager.dto.UserDto;
import hr.tvz.pios.caffebarmanager.repository.UserRepository;
import hr.tvz.pios.caffebarmanager.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    private UserService userService;
    AutoCloseable autoCloseable;
    User user;
    User user2;
    UserDto userDto;
    UserDto userDto2;
    @BeforeEach
    void setUp() {
        autoCloseable= MockitoAnnotations.openMocks(this);
        userService= new UserServiceImpl(userRepository);
        user=new User();
        user.setId(3L);
        user.setName("Prvi");
        user.setLastName("Prvic");
        user.setEmail("prvi@email.com");
        user.setPassword("password");
        user.setRole(2);
        userDto= mapToDto(user);
        user2=user;
        user2.setEmail("drugi@drugic.com");
        userDto2= mapToDto(user2);
    }
    @Test
    void saveTest() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        Optional<UserDto> result= userService.save(mapToDto(user));

        assertThat(result.get().getName()).isEqualTo(user.getName());
        assertThat(result.get().getEmail()).isEqualTo(user.getEmail());
        assertThat(result.get().getLastName()).isEqualTo(user.getLastName());
        assertThat(result.get().getRole()).isEqualTo(user.getRole());

    }
    @Test
    void findAllUsersTest() {
        ArrayList<User> users= new ArrayList<>();
        users.add(user);
        users.add(user2);

        when(userRepository.findAll()).thenReturn(users);

        List<UserDto> result= userService.findAllUsers();

        assertThat(result.get(0).getEmail()).isEqualTo(user.getEmail());
        assertThat(result.get(1).getEmail()).isEqualTo(user2.getEmail());
    }
    @Test
    void findByEmailTest() {
        when(userRepository.findByEmail("prvi@gmail.com")).
                thenReturn(Optional.ofNullable(user));

        Optional<UserDto> result= userService.findByEmail("prvi@gmail.com");

        assertThat(result.get().getName()).isEqualTo(user.getName());
        assertThat(result.get().getEmail()).isEqualTo(user.getEmail());
        assertThat(result.get().getLastName()).isEqualTo(user.getLastName());
        assertThat(result.get().getRole()).isEqualTo(user.getRole());
    }
    private UserDto mapToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        return userDto;
    }
}