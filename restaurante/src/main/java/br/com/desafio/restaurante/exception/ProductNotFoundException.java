package br.com.desafio.restaurante.exception;

public class ProductNotFoundException extends RuntimeException {

    private static final String PRODUCT_NOT_FOUND = "Produto não encontrado: ";
    public ProductNotFoundException(String product) {
        super(String.format(PRODUCT_NOT_FOUND + product));
    }
}
