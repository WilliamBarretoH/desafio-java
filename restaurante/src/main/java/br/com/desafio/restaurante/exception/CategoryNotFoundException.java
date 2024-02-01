package br.com.desafio.restaurante.exception;

public class CategoryNotFoundException extends RuntimeException {

    private static final String CATEGORY_NOT_FOUND = "Categoria não encontrada: ";
    public CategoryNotFoundException(String category) {
        super(String.format(CATEGORY_NOT_FOUND + category));
    }
}
