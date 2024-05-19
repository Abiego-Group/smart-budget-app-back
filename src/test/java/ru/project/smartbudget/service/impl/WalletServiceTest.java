package ru.project.smartbudget.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
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
import ru.project.smartbudget.config.context.UserContext;
import ru.project.smartbudget.entity.User;
import ru.project.smartbudget.entity.Wallet;
import ru.project.smartbudget.exception.SmartBudgetException;
import ru.project.smartbudget.mapper.WalletMapper;
import ru.project.smartbudget.model.WalletDto;
import ru.project.smartbudget.repository.UserRepository;
import ru.project.smartbudget.repository.WalletRepository;

@ContextConfiguration(classes = {WalletService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class WalletServiceTest {
    @MockBean
    private UserContext userContext;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private WalletMapper walletMapper;

    @MockBean
    private WalletRepository walletRepository;

    @Autowired
    private WalletService walletService;

    @Test
    void testOpenWallet() {
        User owner = new User();
        owner.setAge(1);
        owner.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        owner.setId(1L);
        owner.setInn("Inn");
        owner.setMiddleName("Middle Name");
        owner.setName("Name");
        owner.setRole("Role");
        owner.setSurname("Doe");
        owner.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        owner.setWallets(new ArrayList<>());

        Wallet wallet = new Wallet();
        wallet.setBalance(new BigDecimal("2.3"));
        wallet.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        wallet.setId(1L);
        wallet.setOwner(owner);
        wallet.setTransactionHistory(new ArrayList<>());
        wallet.setTransactionHistoryId(1L);
        wallet.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        wallet.setUsers(new ArrayList<>());
        when(walletRepository.save(Mockito.<Wallet>any())).thenReturn(wallet);

        User owner2 = new User();
        owner2.setAge(1);
        owner2.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        owner2.setId(1L);
        owner2.setInn("Inn");
        owner2.setMiddleName("Middle Name");
        owner2.setName("Name");
        owner2.setRole("Role");
        owner2.setSurname("Doe");
        owner2.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        owner2.setWallets(new ArrayList<>());

        Wallet wallet2 = new Wallet();
        wallet2.setBalance(new BigDecimal("2.3"));
        wallet2.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        wallet2.setId(1L);
        wallet2.setOwner(owner2);
        wallet2.setTransactionHistory(new ArrayList<>());
        wallet2.setTransactionHistoryId(1L);
        wallet2.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        wallet2.setUsers(new ArrayList<>());

        WalletDto walletDto = new WalletDto();
        walletDto.setBalance(42L);
        walletDto.setTransactionHistory(new ArrayList<>());
        walletDto.setUsers(new ArrayList<>());
        when(walletMapper.toEntity(Mockito.<WalletDto>any())).thenReturn(wallet2);
        when(walletMapper.toDto(Mockito.<Wallet>any())).thenReturn(walletDto);
        when(userContext.getUserId()).thenReturn(1L);

        WalletDto wallet3 = new WalletDto();
        wallet3.setBalance(42L);
        wallet3.setTransactionHistory(new ArrayList<>());
        wallet3.setUsers(new ArrayList<>());

        WalletDto actualOpenWalletResult = walletService.openWallet(wallet3);

        verify(walletRepository).save(isA(Wallet.class));
        verify(userContext).getUserId();
        verify(walletMapper).toDto(isA(Wallet.class));
        verify(walletMapper).toEntity(isA(WalletDto.class));
        assertSame(walletDto, actualOpenWalletResult);
    }

    @Test
    void testOpenWalletWithError() {
        when(userContext.getUserId()).thenThrow(new SmartBudgetException("An error occurred"));

        WalletDto wallet = new WalletDto();
        wallet.setBalance(42L);
        wallet.setTransactionHistory(new ArrayList<>());
        wallet.setUsers(new ArrayList<>());

        assertThrows(SmartBudgetException.class, () -> walletService.openWallet(wallet));
        verify(userContext).getUserId();
    }

    @Test
    void testAddUser() {
        User owner = new User();
        owner.setAge(1);
        owner.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        owner.setId(1L);
        owner.setInn("Inn");
        owner.setMiddleName("Middle Name");
        owner.setName("Name");
        owner.setRole("Role");
        owner.setSurname("Doe");
        owner.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        owner.setWallets(new ArrayList<>());

        Wallet wallet = new Wallet();
        wallet.setBalance(new BigDecimal("2.3"));
        wallet.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        wallet.setId(1L);
        wallet.setOwner(owner);
        wallet.setTransactionHistory(new ArrayList<>());
        wallet.setTransactionHistoryId(1L);
        wallet.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        wallet.setUsers(new ArrayList<>());

        User owner2 = new User();
        owner2.setAge(1);
        owner2.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        owner2.setId(1L);
        owner2.setInn("Inn");
        owner2.setMiddleName("Middle Name");
        owner2.setName("Name");
        owner2.setRole("Role");
        owner2.setSurname("Doe");
        owner2.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        owner2.setWallets(new ArrayList<>());

        Wallet wallet2 = new Wallet();
        wallet2.setBalance(new BigDecimal("2.3"));
        wallet2.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        wallet2.setId(1L);
        wallet2.setOwner(owner2);
        wallet2.setTransactionHistory(new ArrayList<>());
        wallet2.setTransactionHistoryId(1L);
        wallet2.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        wallet2.setUsers(new ArrayList<>());
        when(walletRepository.save(Mockito.<Wallet>any())).thenReturn(wallet2);
        when(walletRepository.findByOwnerId(Mockito.<Long>any())).thenReturn(wallet);

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
        when(userContext.getUserId()).thenReturn(1L);

        walletService.addUser("42");

        verify(userRepository).findById(isNull());
        verify(walletRepository).save(isA(Wallet.class));
        verify(userContext).getUserId();
        verify(walletRepository).findByOwnerId(eq(1L));
    }

    @Test
    void testAddUserWithError() {
        when(userContext.getUserId()).thenThrow(new SmartBudgetException("An error occurred"));

        assertThrows(SmartBudgetException.class, () -> walletService.addUser("42"));
        verify(userContext).getUserId();
    }
}
