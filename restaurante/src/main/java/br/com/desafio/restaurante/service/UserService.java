package br.com.desafio.restaurante.service;

import br.com.desafio.restaurante.domain.entity.User;
import br.com.desafio.restaurante.domain.repository.UserRepository;
import br.com.desafio.restaurante.exception.UserNotFoundException;
import br.com.desafio.restaurante.mapper.impl.UserMapper;
import br.com.desafio.restaurante.resource.dto.CustomUserDetail;
import br.com.desafio.restaurante.resource.dto.UserRequestDto;
import br.com.desafio.restaurante.resource.dto.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor
public class UserService implements UserDetailsService {


    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUserName(username).orElseThrow(() -> new UserNotFoundException(username));
        if(user == null){
            throw new UsernameNotFoundException("could not found user..!!");
        }
        return new CustomUserDetail(user);
    }

    public UserResponseDto saveUser(UserRequestDto userRequest) {
        if(userRequest.getUsername() == null){
            throw new RuntimeException("Parameter username is not found in request..!!");
        } else if(userRequest.getPassword() == null){
            throw new RuntimeException("Parameter password is not found in request..!!");
        }

        User savedUser = null;

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = userRequest.getPassword();
        String encodedPassword = encoder.encode(rawPassword);

        User user = userMapper.to(userRequest);
        user.setPassword(encodedPassword);
        if(userRequest.getId() != null){
            User oldUser = userRepository.findById(userRequest.getId()).orElseThrow(() -> new UserNotFoundException(userRequest.getUsername()));
            if(oldUser != null){
                oldUser.setId(user.getId());
                oldUser.setPassword(user.getPassword());
                oldUser.setUserName(user.getUserName());
                oldUser.setRoles(user.getRoles());

                savedUser = userRepository.save(oldUser);

            } else {
                throw new RuntimeException("Can't find record with identifier: " + userRequest.getId());
            }
        } else {
            savedUser = userRepository.save(user);
        }
        return UserResponseDto.builder()
                .username(savedUser.getUserName())
                .id(savedUser.getId())
                .roles(savedUser.getRoles())
                .build();
    }
}
