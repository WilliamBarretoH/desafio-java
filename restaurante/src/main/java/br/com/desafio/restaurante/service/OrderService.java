package br.com.desafio.restaurante.service;

import br.com.desafio.restaurante.domain.entity.Order;
import br.com.desafio.restaurante.domain.entity.Product;
import br.com.desafio.restaurante.domain.repository.CategoryRepository;
import br.com.desafio.restaurante.domain.repository.OrderRepository;
import br.com.desafio.restaurante.domain.repository.ProductRepository;
import br.com.desafio.restaurante.exception.OrderEmptyException;
import br.com.desafio.restaurante.exception.OrderNotFoundException;
import br.com.desafio.restaurante.exception.ProductNotFoundException;
import br.com.desafio.restaurante.exception.UpdateOrderWithEmptyListException;
import br.com.desafio.restaurante.mapper.impl.OrderMapper;
import br.com.desafio.restaurante.resource.dto.OrderCreateDto;
import br.com.desafio.restaurante.resource.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final OrderMapper orderMapper;


    public List<OrderDto> getOpenOrders() {
        try {
            return orderRepository.findAll().stream()
                    .filter(Order::getIsOpen)
                    .map(orderMapper::to).collect(Collectors.toList());
        } catch (Exception exception) {
            throw new OrderNotFoundException();
        }
    }

    public OrderDto createOrder(OrderCreateDto orderDto) {
        if(orderDto.products().isEmpty())
            throw new OrderEmptyException();

        List<Product> products = orderDto.products().stream()
                .map(s -> productRepository.findByName(s)
                        .orElseThrow(() -> new ProductNotFoundException(s)))
                .toList();


        Order order = Order.builder()
                .products(products)
                .isOpen(true)
                .orderDateCreation(new Date())
                .totalAmount(products.stream().map(Product::getPrice).reduce(0.0f, Float::sum))
                .build();

        Order orderSaved = orderRepository.save(order);
        return orderMapper.to(orderSaved);

    }

    public void updateOrder(List<String> products, Long id) {
        if(products.isEmpty())
            throw new UpdateOrderWithEmptyListException(id);

        Order order = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);

        order.getProducts().addAll(products.stream()
                .map(p -> productRepository.findByName(p)
                        .orElseThrow(() -> new ProductNotFoundException(p))).toList());


        order.setTotalAmount(order.getTotalAmount() + products.stream()
                .map(p -> productRepository.findByName(p)
                        .orElseThrow(() -> new ProductNotFoundException(p)))
                .map(Product::getPrice).reduce(0.0f, Float::sum));

        orderRepository.save(order);
    }


}
