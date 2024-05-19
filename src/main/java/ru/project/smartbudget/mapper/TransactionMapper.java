package ru.project.smartbudget.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.project.smartbudget.entity.Transaction;
import ru.project.smartbudget.model.TransactionDto;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionMapper {

    Transaction toEntity(TransactionDto transactionDto);

    @Mapping(target = "currency", source = "currency", ignore = true)
    @Mapping(target = "category", source = "category", ignore = true)
    TransactionDto toDto(Transaction transaction);
}
