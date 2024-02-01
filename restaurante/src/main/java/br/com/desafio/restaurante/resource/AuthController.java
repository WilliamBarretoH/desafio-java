package br.com.desafio.restaurante.resource;

import br.com.desafio.restaurante.resource.dto.TokenDto;
import br.com.desafio.restaurante.resource.dto.UserCredentialsDto;
import br.com.desafio.restaurante.resource.dto.UserRequestDto;
import br.com.desafio.restaurante.resource.dto.UserResponseDto;
import br.com.desafio.restaurante.service.JwtService;
import br.com.desafio.restaurante.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication Endpoint")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final JwtService jwtService;

    @PostMapping("/api/v1/login")
    public TokenDto AuthenticateAndGetToken(@RequestBody UserCredentialsDto authRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUserName(), authRequestDTO.getPassword()));
        if (authentication.isAuthenticated()) {
            return TokenDto.builder()
                    .accessToken(jwtService.GenerateToken(authRequestDTO.getUserName()))
                    .build();
        } else {
            throw new UsernameNotFoundException(authRequestDTO.getUserName());
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity saveUser(@RequestBody UserRequestDto userRequest) {
        try {
            UserResponseDto userResponse = userService.saveUser(userRequest);
            return ResponseEntity.ok(userResponse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}