package br.com.desafio.restaurante.exception;

public class OrderEmptyException extends RuntimeException {

    private static final String ORDER_EMPTY = "Não é possível criar um pedido sem produtos";
    public OrderEmptyException() {
        super(ORDER_EMPTY);
    }
}
