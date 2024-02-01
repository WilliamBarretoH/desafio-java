package br.com.desafio.restaurante.exception;

public class UserNotFoundException extends RuntimeException {

    private static final String USER_NOT_FOUND = "Nome de usuário não encontrado: ";
    public UserNotFoundException(String userName) {
        super(String.format(USER_NOT_FOUND + userName));
    }
}
