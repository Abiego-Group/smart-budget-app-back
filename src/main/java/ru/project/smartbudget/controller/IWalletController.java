package ru.project.smartbudget.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.project.smartbudget.model.WalletDto;

@RequestMapping("api/v1/wallet")
public interface IWalletController {

    @PutMapping("/open")
    WalletDto openWallet(WalletDto wallet);

    @PostMapping("/add-user/{id}")
    void addUser(@PathVariable("id") String id);
}
