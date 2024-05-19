package ru.project.smartbudget.model;

import lombok.*;
import ru.project.smartbudget.model.dictionary.Role;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String name;
    private String surname;
    private String middleName;
    private Integer age;
    private String inn;
    private Role role;
    private List<String> walletIds;
    private List<GoalDto> goals;
}
