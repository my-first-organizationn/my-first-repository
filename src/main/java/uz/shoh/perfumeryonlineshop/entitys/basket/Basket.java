package uz.shoh.perfumeryonlineshop.entitys.basket;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.shoh.perfumeryonlineshop.entitys.product.Product;
import uz.shoh.perfumeryonlineshop.entitys.user.entity.User;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToMany(mappedBy = "basket")
    private List<Product> products;
    @OneToOne(mappedBy = "basket")
    private User user;
}
