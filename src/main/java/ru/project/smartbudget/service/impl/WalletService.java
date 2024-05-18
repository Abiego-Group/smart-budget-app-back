package ru.project.smartbudget.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.project.smartbudget.entity.User;
import ru.project.smartbudget.entity.Wallet;
import ru.project.smartbudget.exception.SmartBudgetException;
import ru.project.smartbudget.mapper.WalletMapper;
import ru.project.smartbudget.model.WalletDto;
import ru.project.smartbudget.repository.UserRepository;
import ru.project.smartbudget.repository.WalletRepository;
import ru.project.smartbudget.service.IWalletService;

@Slf4j
@Service
@RequiredArgsConstructor
public class WalletService implements IWalletService {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;
    private final WalletMapper walletMapper;

    @Override
    public WalletDto openWallet(WalletDto wallet) {
        log.info("Открытие счета: {}, пользователем: todo", wallet);
        Wallet createdWallet = walletRepository.save(walletMapper.toEntity(wallet));
        log.info("Счет открыт: {}", createdWallet);
        return walletMapper.toDto(createdWallet);
    }

    @Override
    @Transactional
    public void addUser(String id) {
        //todo получение id пользователя из текущей сессии
        log.info("Добавление пользователя: {} в общий счет", id);
        Wallet wallet = walletRepository.findByUserId(1L); //todo пока заменим абстрактным значением
        User user = userRepository.findById(Long.getLong(id))
                .orElseThrow(() -> new SmartBudgetException("Ошибка при получении пользователя"));

        wallet.getUsers().add(user);
        walletRepository.save(wallet);
    }
}
