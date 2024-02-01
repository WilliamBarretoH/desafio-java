package br.com.desafio.restaurante.resource.dto;

import br.com.desafio.restaurante.domain.entity.Category;

public record ProductCreateDto(String name, Float price) {
}
