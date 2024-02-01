package br.com.desafio.restaurante.exception;

public class OrderNotFoundException extends RuntimeException {

    private static final String ORDERS_NOT_FOUND = "Pedido não encontrado";
    public OrderNotFoundException() {
        super(ORDERS_NOT_FOUND);
    }
}
