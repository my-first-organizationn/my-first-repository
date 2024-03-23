package uz.shoh.perfumeryonlineshop.entitys.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.shoh.perfumeryonlineshop.entitys.basket.Basket;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(length = 1000)
    private String description;
    @Column(nullable = false)
    private double price;
    private int quantity;
    @Lob
    private String imageUrl;
    @ManyToOne
    @JoinColumn(name = "basket_id")
    private Basket basket;
}
