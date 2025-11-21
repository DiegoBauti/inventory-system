package Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String username;
    private String password;
    private boolean status=true;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at",insertable = false, updatable = false)
    private LocalDateTime updatedAt;
}
