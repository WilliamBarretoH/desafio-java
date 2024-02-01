package br.com.desafio.restaurante.exception;

public class UpdateOrderWithEmptyListException extends RuntimeException {

    private static final String ORDER_EMPTY = "Não foi possivel atualizar o pedido: ";
    public UpdateOrderWithEmptyListException(Long id) {
        super(ORDER_EMPTY + id.toString());
    }
}
