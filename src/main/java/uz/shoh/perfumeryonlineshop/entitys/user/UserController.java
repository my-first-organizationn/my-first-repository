package uz.shoh.perfumeryonlineshop.entitys.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.shoh.perfumeryonlineshop.entitys.user.dto.UserCreateDto;
import uz.shoh.perfumeryonlineshop.entitys.user.dto.UserResponseDto;
import uz.shoh.perfumeryonlineshop.entitys.user.dto.UserUpdateDto;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> create(@RequestBody UserCreateDto userCreateDto) {
        UserResponseDto responseDto = userService.create(userCreateDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll() {
        List<UserResponseDto> responseDtoList = userService.getAll();
        return ResponseEntity
                .ok(responseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Integer id) {
        UserResponseDto responseDto = userService.getUser(id);
        return ResponseEntity
                .ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> update(@PathVariable Integer id, @RequestBody UserUpdateDto updateDto) {
        UserResponseDto responseDto = userService.updateUser(id, updateDto);
        return ResponseEntity
                .ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
