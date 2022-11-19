package com.fivedaysincloud.cryptoexchange.mapper;

import com.fivedaysincloud.cryptoexchange.dto.UserDto;
import com.fivedaysincloud.cryptoexchange.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public UserDto mapToDto(User user) {
        return new UserDto(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName());
    }

    public User mapToUser(UserDto dto) {
        return new User(dto.getId(), dto.getEmail(), dto.getFirstName(), dto.getLastName());
    }
}
