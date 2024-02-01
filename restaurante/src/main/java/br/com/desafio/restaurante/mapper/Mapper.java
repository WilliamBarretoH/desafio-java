package br.com.desafio.restaurante.mapper;

public interface Mapper<T, F> {

    public F to(T obj);

    public T from(F obj);
}
