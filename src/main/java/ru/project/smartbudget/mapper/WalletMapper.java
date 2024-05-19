package ru.project.smartbudget.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import ru.project.smartbudget.entity.Transaction;
import ru.project.smartbudget.entity.Wallet;
import ru.project.smartbudget.model.TransactionDto;
import ru.project.smartbudget.model.UserDto;
import ru.project.smartbudget.model.WalletDto;
import ru.project.smartbudget.model.dictionary.Category;
import ru.project.smartbudget.model.dictionary.Currency;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WalletMapper {

    @Mapping(source = "transactionHistory", target = "transactionHistory", qualifiedByName = "transactionsToDtos")
    WalletDto toDto(Wallet wallet);

    Wallet toEntity(WalletDto walletDto);

    @Named("transactionsToDtos")
    default List<TransactionDto> transactionsToDtos(List<Transaction> transactions) {
        return transactions.stream().map(transaction ->
                TransactionDto.builder()
                        .balanceDifference(transaction.getBalanceDifference().longValue())
                        .balanceResult(transaction.getBalanceResult().longValue())
                        .user(UserDto.builder().name(transaction.getUser().getName()).build())
                        .date(transaction.getCreatedAt())
                        .currency(Currency.valueOf(transaction.getCurrency().getName()))
                        .category(Category.valueOf(transaction.getCategory().getName()))
                        .build()
        ).toList();
    }
}
