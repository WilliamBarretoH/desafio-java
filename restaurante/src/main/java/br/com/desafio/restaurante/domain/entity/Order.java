package br.com.desafio.restaurante.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "order_tb")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order", nullable = false)
    private Long id;

    @Column(name = "order_date_creation", nullable = false)
    private Date orderDateCreation;

    @Column(name = "total_amount")
    private Float totalAmount;

    @Column(name = "is_open", nullable = false)
    private Boolean isOpen;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "order_product_tb",
            joinColumns = @JoinColumn(name = "id_order"),
            inverseJoinColumns = @JoinColumn(name = "id_product")
    )
    private List<Product> products;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDateCreation=" + orderDateCreation +
                ", totalAmount=" + totalAmount +
                ", isOpen=" + isOpen +
                ", products=" + getProductNames() +
                '}';
    }

    private List<String> getProductNames() {
        return products.stream()
                .map(Product::getName)
                .collect(Collectors.toList());
    }

}
