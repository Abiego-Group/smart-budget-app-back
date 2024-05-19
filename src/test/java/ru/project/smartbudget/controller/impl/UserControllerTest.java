package ru.project.smartbudget.controller.impl;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.project.smartbudget.model.UserDto;
import ru.project.smartbudget.model.dictionary.Role;
import ru.project.smartbudget.service.IUserService;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class UserControllerTest {
    @MockBean
    private IUserService iUserService;

    @Autowired
    private UserController userController;

    @Test
    void testCreateUser() {
        doNothing().when(iUserService).createUser(Mockito.<UserDto>any());

        userController.createUser(new UserDto());

        verify(iUserService).createUser(isA(UserDto.class));
    }

    @Test
    void testGetUser() {
        UserDto.UserDtoBuilder ageResult = UserDto.builder().age(1);
        UserDto.UserDtoBuilder surnameResult = ageResult.goals(new ArrayList<>())
                .inn("Inn")
                .middleName("Middle Name")
                .name("Name")
                .role(Role.BASE)
                .surname("Doe");
        UserDto buildResult = surnameResult.walletIds(new ArrayList<>()).build();
        when(iUserService.getUser(Mockito.<String>any())).thenReturn(buildResult);

        userController.getUser("42");

        verify(iUserService).getUser(eq("42"));
    }
}
