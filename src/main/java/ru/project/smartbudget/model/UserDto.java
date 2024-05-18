package ru.project.smartbudget.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.project.smartbudget.model.dictionary.Role;

import java.util.List;

@Data
@RequiredArgsConstructor
public class UserDto {
    private String id;
    private String name;
    private String surname;
    private String middleName;
    private Integer age;
    private String inn;
    private Role role;
    private List<String> walletIds;
    private List<Goal> goals;
}
