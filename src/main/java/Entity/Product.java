package Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(precision = 10,scale = 2)
    private BigDecimal price;
    private int stock;
    private String description;
    private boolean status=true;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at",insertable = false, updatable = false)
    private LocalDateTime updatedAt;

}
