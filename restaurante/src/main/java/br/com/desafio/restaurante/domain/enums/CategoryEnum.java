package br.com.desafio.restaurante.domain.enums;

public enum CategoryEnum {
    BEBIDA("bebida"), SOBREMESA("sobremesa"), PRATOPRINCIPAL("prato principal"), ENTRADA("entrada");

    public final String description;
    private CategoryEnum(String description) {
        this.description = description;
    }
}
