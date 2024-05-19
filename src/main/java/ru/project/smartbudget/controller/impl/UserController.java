package ru.project.smartbudget.controller.impl;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.project.smartbudget.controller.IUserController;
import ru.project.smartbudget.model.UserDto;
import ru.project.smartbudget.service.IUserService;

@RestController
@RequiredArgsConstructor
public class UserController implements IUserController {

    private final IUserService userService;

    @Override
    @Operation(summary = "Создание пользователя")
    public void createUser(UserDto user) {
        userService.createUser(user);
    }

    @Override
    public UserDto getUser(String id) {
        return userService.getUser(id);
    }
}
