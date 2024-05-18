package ru.project.smartbudget.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.project.smartbudget.model.UserDto;

@RequestMapping("api/v1/user")
public interface IUserController {
    @PutMapping("/create")
    void createUser(UserDto user);

    @PostMapping("")
    UserDto getUser(String id);
}
