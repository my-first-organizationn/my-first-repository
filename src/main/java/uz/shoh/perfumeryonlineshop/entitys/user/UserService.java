package uz.shoh.perfumeryonlineshop.entitys.user;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.shoh.perfumeryonlineshop.entitys.user.dto.UserCreateDto;
import uz.shoh.perfumeryonlineshop.entitys.user.dto.UserResponseDto;
import uz.shoh.perfumeryonlineshop.entitys.user.dto.UserUpdateDto;
import uz.shoh.perfumeryonlineshop.entitys.user.entity.User;
import uz.shoh.perfumeryonlineshop.entitys.user.enums.UserRole;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserResponseDto create(UserCreateDto userCreateDto) {
        User user = new User(null, userCreateDto.getName(), userCreateDto.getSurname(), userCreateDto.getPhoneNumber(), userCreateDto.getDateOfBirth(), userCreateDto.getPassword(), UserRole.SELLER, null);
        userRepository.save(user);
        return modelMapper.map(user, UserResponseDto.class);
    }

    public List<UserResponseDto> getAll() {
        return userRepository
                .findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserResponseDto.class))
                .toList();
    }

    public UserResponseDto getUser(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return modelMapper.map(user, UserResponseDto.class);
        } else {
            throw new IllegalArgumentException("User with ID " + id + " not found");
        }
    }

    public UserResponseDto updateUser(Integer id, UserUpdateDto updateDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));

        user.setName(updateDto.getName());
        user.setSurname(updateDto.getSurname());
        user.setPassword(user.getPassword());
        userRepository.save(user);

        return modelMapper.map(user, UserResponseDto.class);
    }

    public void deleteUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not fount with id: " + id));

        userRepository.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByName(username)
                .orElseThrow(() -> new BadCredentialsException("Username or password is not correct"));
    }
}
