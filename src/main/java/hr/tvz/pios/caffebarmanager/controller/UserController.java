package hr.tvz.pios.caffebarmanager.controller;

import hr.tvz.pios.caffebarmanager.dto.UserDto;
import hr.tvz.pios.caffebarmanager.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class UserController {

    UserService userService;

   @GetMapping("all")
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/")
    public ResponseEntity<UserDto> getUserByEmail(@RequestParam String email) {
       return userService.findByEmail(email)
               .map(
                       user -> ResponseEntity.status(HttpStatus.OK).body(user)
               ).orElseGet(
                       () -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()
               );
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) {
       return userService.save(userDto)
               .map(
                       user -> ResponseEntity.status(HttpStatus.CREATED).body(user)
               ).orElseGet(
                       () -> ResponseEntity.status(HttpStatus.CONFLICT).build()
               );
    }
}
