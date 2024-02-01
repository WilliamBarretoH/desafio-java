package br.com.desafio.restaurante.mapper.impl;

import br.com.desafio.restaurante.domain.entity.Order;
import br.com.desafio.restaurante.domain.repository.CategoryRepository;
import br.com.desafio.restaurante.mapper.Mapper;
import br.com.desafio.restaurante.resource.dto.OrderDto;
import br.com.desafio.restaurante.resource.dto.ProductByOrderDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor
@Component
public class OrderMapper implements Mapper<Order, OrderDto> {

    @Override
    public OrderDto to(Order obj) {
        return new OrderDto(obj.getId(),
                obj.getTotalAmount(),
                obj.getIsOpen(),
                formatDate(obj.getOrderDateCreation()),
                obj.getProducts()
                        .stream()
                        .map(product -> new ProductByOrderDto(product.getName(), product.getPrice()))
                        .toList());
    }

    @Override
    public Order from(OrderDto obj) {
        return Order.builder()
                .isOpen(true)
                .totalAmount(obj.total_amount())
                .build();
    }

    private String formatDate(Date date) {
        SimpleDateFormat customFormat = new SimpleDateFormat("dd/MM/yy - HH:mm");
        return customFormat.format(date);
    }
}
