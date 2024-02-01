package br.com.desafio.restaurante.service;

import br.com.desafio.restaurante.domain.entity.Product;
import br.com.desafio.restaurante.domain.repository.ProductRepository;
import br.com.desafio.restaurante.exception.PriceNotAuthorizedException;
import br.com.desafio.restaurante.exception.ProductNotFoundException;
import br.com.desafio.restaurante.exception.ProductWithEptyNameException;
import br.com.desafio.restaurante.mapper.impl.ProductMapper;
import br.com.desafio.restaurante.resource.dto.ProductCreateDto;
import br.com.desafio.restaurante.resource.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream().map(productMapper::to).collect(Collectors.toList());
    }

    public ProductDto createProduct(ProductCreateDto productCreateDto, String category) {

        if(productCreateDto.name().isBlank() || productCreateDto.name().isEmpty())
            throw new ProductWithEptyNameException();

        return productMapper.to(productRepository
                .save(productMapper.from(new ProductDto(productCreateDto.name(),
                                productCreateDto.price(),
                                category))));

    }

    public ProductDto updateProduct(ProductCreateDto productCreateDto, Long id) {

        if(productCreateDto.name().isBlank() || productCreateDto.name().isEmpty())
            throw new ProductWithEptyNameException();

        if(productCreateDto.price() <= 0 || productCreateDto.price().isNaN())
            throw new PriceNotAuthorizedException();

        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id.toString()));

        product.setName(productCreateDto.name());
        product.setPrice(productCreateDto.price());

        return productMapper.to(productRepository.save(product));

    }

    public void deleteProduct(Long id) {
        productRepository.delete(productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id.toString())));
    }

}
