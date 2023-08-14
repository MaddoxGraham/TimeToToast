package com.maddoxgraham.TimeToToast.Mappers;

import com.maddoxgraham.TimeToToast.DTOs.SignUpDto;
import com.maddoxgraham.TimeToToast.DTOs.UserDTo;
import com.maddoxgraham.TimeToToast.Models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTo toUserDto(User user );

    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto signUpDto);
}
