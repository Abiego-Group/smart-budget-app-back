package ru.project.smartbudget.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.project.smartbudget.entity.User;
import ru.project.smartbudget.exception.SmartBudgetException;
import ru.project.smartbudget.mapper.UserMapper;
import ru.project.smartbudget.model.UserDto;
import ru.project.smartbudget.repository.UserRepository;
import ru.project.smartbudget.service.IUserService;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public void createUser(UserDto user) {
        log.info("Создание нового пользователя: {}", user);
        userRepository.save(userMapper.toEntity(user));
    }

    @Override
    public UserDto getUser(String id) {
        log.info("Получение пользователя {}", id);
        User user = userRepository.findById(Long.getLong(id))
                .orElseThrow(() -> new SmartBudgetException("Ошибка при получении пользователя"));

        return userMapper.toDto(user);
    }
}
