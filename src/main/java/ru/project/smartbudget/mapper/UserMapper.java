package ru.project.smartbudget.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.project.smartbudget.entity.User;
import ru.project.smartbudget.model.UserDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
}
