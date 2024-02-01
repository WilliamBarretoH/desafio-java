package br.com.desafio.restaurante.domain.repository;

import br.com.desafio.restaurante.domain.entity.Category;
import br.com.desafio.restaurante.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findCategoryByName(String name);

}
