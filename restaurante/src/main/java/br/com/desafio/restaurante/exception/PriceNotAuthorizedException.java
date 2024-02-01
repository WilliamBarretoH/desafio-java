package br.com.desafio.restaurante.exception;

public class PriceNotAuthorizedException extends RuntimeException {

    private static final String PRICE_NOT_AUTHORIZED = "Preço não autorizado";
    public PriceNotAuthorizedException() {
        super(String.format(PRICE_NOT_AUTHORIZED));
    }
}
