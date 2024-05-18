package ru.project.smartbudget.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class WalletDto {
    private Long balance;
    private List<TransactionDto> transactionHistory;
    private List<UserDto> users;
}
