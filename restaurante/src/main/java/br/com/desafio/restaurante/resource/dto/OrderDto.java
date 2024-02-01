package br.com.desafio.restaurante.resource.dto;

import java.util.List;

public record OrderDto(Long id, Float total_amount, Boolean isOpen, String dateCreation, List<ProductByOrderDto> products) {

}
