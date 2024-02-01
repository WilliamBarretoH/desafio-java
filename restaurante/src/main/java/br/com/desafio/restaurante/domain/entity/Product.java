package br.com.desafio.restaurante.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "product_tb")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long id;

    @Column
    private String name;

    @Column
    private Float price;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;

}
