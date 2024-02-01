package br.com.desafio.restaurante.exception;

public class ProductWithEptyNameException extends RuntimeException {

    private static final String PRODUCT_NOT_FOUND = "Nome do produto não pode ser vazio";
    public ProductWithEptyNameException() {
        super(String.format(PRODUCT_NOT_FOUND));
    }
}
