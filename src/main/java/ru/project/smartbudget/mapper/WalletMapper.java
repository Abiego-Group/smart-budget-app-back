package ru.project.smartbudget.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.project.smartbudget.entity.Wallet;
import ru.project.smartbudget.model.WalletDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WalletMapper {
    WalletDto toDto(Wallet wallet);
    Wallet toEntity(WalletDto walletDto);
}
