package com.maddoxgraham.TimeToToast.Mappers;

import com.maddoxgraham.TimeToToast.DTOs.SignUpDto;
import com.maddoxgraham.TimeToToast.DTOs.UserDto;
import com.maddoxgraham.TimeToToast.Models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user );

    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto signUpDto);

    @Mapping(target = "password", ignore = true)
    User toEntity(UserDto userDto);
}
