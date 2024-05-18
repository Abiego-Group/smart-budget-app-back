package ru.project.smartbudget.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.project.smartbudget.controller.IWalletController;
import ru.project.smartbudget.model.WalletDto;
import ru.project.smartbudget.service.IWalletService;

@RestController
@RequiredArgsConstructor
public class WalletController implements IWalletController {

    private final IWalletService walletService;

    @Override
    public WalletDto openWallet(WalletDto wallet) {
        return walletService.openWallet(wallet);
    }

    @Override
    public void addUser(String id) {
        walletService.addUser(id);
    }
}
