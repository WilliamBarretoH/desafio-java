package br.com.desafio.restaurante.mapper.impl;

import br.com.desafio.restaurante.domain.entity.User;
import br.com.desafio.restaurante.mapper.Mapper;
import br.com.desafio.restaurante.resource.dto.UserRequestDto;
import br.com.desafio.restaurante.resource.dto.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<UserRequestDto, User> {
    @Override
    public User to(UserRequestDto obj) {
        return User.builder()
                .id(obj.getId())
                .userName(obj.getUsername())
                .roles(obj.getRoles())
                .build();
    }

    @Override
    public UserRequestDto from(User obj) {
        return UserRequestDto.builder()
                .build();
    }
}
