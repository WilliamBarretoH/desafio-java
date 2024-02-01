package br.com.desafio.restaurante.resource.dto;

import br.com.desafio.restaurante.domain.entity.Role;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserResponseDto {

    private Long id;
    private String username;
    private Set<Role> roles;


}