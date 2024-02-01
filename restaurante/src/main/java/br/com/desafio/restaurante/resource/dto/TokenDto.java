package br.com.desafio.restaurante.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {

    private String userName;
    private Boolean isAuthenticated;
    private Date created;
    private Date expiration;
    private String accessToken;
    private String refreshToken;

}
