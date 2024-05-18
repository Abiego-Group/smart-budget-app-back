package ru.project.smartbudget.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.project.smartbudget.model.dictionary.Category;
import ru.project.smartbudget.model.dictionary.Currency;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class TransactionDto {
    private Long balanceDifference;
    private Long balanceResult;
    private LocalDateTime date;
    private Currency currency;
    private Category category;
    private UserDto user;
}
