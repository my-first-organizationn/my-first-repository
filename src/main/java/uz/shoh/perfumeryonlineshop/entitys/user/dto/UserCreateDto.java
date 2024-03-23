package uz.shoh.perfumeryonlineshop.entitys.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.shoh.perfumeryonlineshop.entitys.user.enums.UserRole;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto {
    private String name;
    private String surname;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String password;
    private UserRole role;
}
