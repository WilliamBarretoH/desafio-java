package br.com.desafio.restaurante.mapper.impl;

import br.com.desafio.restaurante.domain.entity.Product;
import br.com.desafio.restaurante.domain.repository.CategoryRepository;
import br.com.desafio.restaurante.exception.CategoryNotFoundException;
import br.com.desafio.restaurante.exception.ProductNotFoundException;
import br.com.desafio.restaurante.mapper.Mapper;
import br.com.desafio.restaurante.resource.dto.ProductDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ProductMapper implements Mapper<Product, ProductDto> {

    private final CategoryRepository categoryRepository;

    @Override
    public ProductDto to(Product obj) {
        return new ProductDto(obj.getName(), obj.getPrice(), obj.getCategory().getName());
    }

    @Override
    public Product from(ProductDto obj) {
        return Product.builder()
                .name(obj.name())
                .price(obj.price())
                .category(categoryRepository.findCategoryByName(obj.category()).orElseThrow(() -> new CategoryNotFoundException(obj.category())))
                .build();
    }
}
