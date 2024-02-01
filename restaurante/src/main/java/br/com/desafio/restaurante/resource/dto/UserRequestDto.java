package br.com.desafio.restaurante.resource.dto;

import br.com.desafio.restaurante.domain.entity.Role;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserRequestDto {

    private Long id;
    private String username;
    private String password;
    private Set<Role> roles;


}