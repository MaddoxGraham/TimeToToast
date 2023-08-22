package com.maddoxgraham.TimeToToast.Mappers;

import com.maddoxgraham.TimeToToast.DTOs.SignUpDto;
import com.maddoxgraham.TimeToToast.DTOs.UserDto;
import com.maddoxgraham.TimeToToast.Models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "role", target = "role")
    @Mapping(source = "name", target = "name")
    UserDto toUserDto(User user );

    @Mapping(target = "password", ignore = true)
    @Mapping(source = "name", target = "name")
    User signUpToUser(SignUpDto signUpDto);

    @Mapping(target = "password", ignore = true)
    @Mapping(source = "role", target = "role")
    User toEntity(UserDto userDto);
}
