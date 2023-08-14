package com.maddoxgraham.TimeToToast.Mappers;

import com.maddoxgraham.TimeToToast.DTOs.UserDTo;
import com.maddoxgraham.TimeToToast.Models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTo toUserDto(User user );
}
