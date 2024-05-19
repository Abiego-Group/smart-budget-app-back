package ru.project.smartbudget.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.project.smartbudget.entity.User;
import ru.project.smartbudget.exception.SmartBudgetException;
import ru.project.smartbudget.mapper.UserMapper;
import ru.project.smartbudget.model.UserDto;
import ru.project.smartbudget.model.dictionary.Role;
import ru.project.smartbudget.repository.UserRepository;

@ContextConfiguration(classes = {UserService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class UserServiceTest {
    @MockBean
    private UserMapper userMapper;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    void testCreateUser() {
        User user = new User();
        user.setAge(1);
        user.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setId(1L);
        user.setInn("Inn");
        user.setMiddleName("Middle Name");
        user.setName("Name");
        user.setRole("Role");
        user.setSurname("Doe");
        user.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setWallets(new ArrayList<>());
        when(userRepository.save(Mockito.<User>any())).thenReturn(user);

        User user2 = new User();
        user2.setAge(1);
        user2.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user2.setId(1L);
        user2.setInn("Inn");
        user2.setMiddleName("Middle Name");
        user2.setName("Name");
        user2.setRole("Role");
        user2.setSurname("Doe");
        user2.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user2.setWallets(new ArrayList<>());
        when(userMapper.toEntity(Mockito.<UserDto>any())).thenReturn(user2);

        userService.createUser(new UserDto());

        verify(userRepository).save(isA(User.class));
        verify(userMapper).toEntity(isA(UserDto.class));
    }

    @Test
    void testCreateUserWithError() {
        when(userMapper.toEntity(Mockito.<UserDto>any())).thenThrow(new SmartBudgetException("An error occurred"));

        assertThrows(SmartBudgetException.class, () -> userService.createUser(new UserDto()));
        verify(userMapper).toEntity(isA(UserDto.class));
    }

    @Test
    void testGetUser() {
        User user = new User();
        user.setAge(1);
        user.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setId(1L);
        user.setInn("Inn");
        user.setMiddleName("Middle Name");
        user.setName("Name");
        user.setRole("Role");
        user.setSurname("Doe");
        user.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setWallets(new ArrayList<>());
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        UserDto.UserDtoBuilder ageResult = UserDto.builder().age(1);
        UserDto.UserDtoBuilder surnameResult = ageResult.goals(new ArrayList<>())
                .inn("Inn")
                .middleName("Middle Name")
                .name("Name")
                .role(Role.BASE)
                .surname("Doe");
        UserDto buildResult = surnameResult.walletIds(new ArrayList<>()).build();
        when(userMapper.toDto(Mockito.<User>any())).thenReturn(buildResult);

        userService.getUser("42");

        verify(userRepository).findById(isNull());
        verify(userMapper).toDto(isA(User.class));
    }

    @Test
    void testGetUserWithError() {
        User user = new User();
        user.setAge(1);
        user.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setId(1L);
        user.setInn("Inn");
        user.setMiddleName("Middle Name");
        user.setName("Name");
        user.setRole("Role");
        user.setSurname("Doe");
        user.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        user.setWallets(new ArrayList<>());
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(userMapper.toDto(Mockito.<User>any())).thenThrow(new SmartBudgetException("An error occurred"));

        assertThrows(SmartBudgetException.class, () -> userService.getUser("42"));
        verify(userRepository).findById(isNull());
        verify(userMapper).toDto(isA(User.class));
    }

    @Test
    void testGetUserWithEmpty() {
        Optional<User> emptyResult = Optional.empty();
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        assertThrows(SmartBudgetException.class, () -> userService.getUser("42"));
        verify(userRepository).findById(isNull());
    }
}
