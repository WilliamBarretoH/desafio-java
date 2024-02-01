package br.com.desafio.restaurante.resource.dto;

import java.util.List;

public record OrderCreateDto(List<String> products) {

}
