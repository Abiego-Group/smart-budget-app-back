package ru.project.smartbudget.controller.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
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
import ru.project.smartbudget.model.WalletDto;
import ru.project.smartbudget.service.IWalletService;

@ContextConfiguration(classes = {WalletController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class WalletControllerTest {
    @MockBean
    private IWalletService iWalletService;

    @Autowired
    private WalletController walletController;

    @Test
    void testOpenWallet() {
        WalletDto walletDto = new WalletDto();
        walletDto.setBalance(42L);
        walletDto.setTransactionHistory(new ArrayList<>());
        walletDto.setUsers(new ArrayList<>());
        when(iWalletService.openWallet(Mockito.<WalletDto>any())).thenReturn(walletDto);

        WalletDto wallet = new WalletDto();
        wallet.setBalance(42L);
        wallet.setTransactionHistory(new ArrayList<>());
        wallet.setUsers(new ArrayList<>());

        WalletDto actualOpenWalletResult = walletController.openWallet(wallet);

        verify(iWalletService).openWallet(isA(WalletDto.class));
        assertSame(walletDto, actualOpenWalletResult);
    }

    @Test
    void testAddUser() {
        doNothing().when(iWalletService).addUser(Mockito.<String>any());

        walletController.addUser("42");

        verify(iWalletService).addUser(eq("42"));
    }
}
