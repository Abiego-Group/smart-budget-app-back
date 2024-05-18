package ru.project.smartbudget.service;


import ru.project.smartbudget.model.WalletDto;

public interface IWalletService {

    WalletDto openWallet(WalletDto wallet);

    void addUser(String id);
}
