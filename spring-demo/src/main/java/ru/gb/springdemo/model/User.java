package ru.gb.springdemo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
@Schema(name = "Пользователь")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "Идентификатор")
    private Long id;
    @Schema(name = "Имя")
    private String name;
    @Schema(name = "Пароль")
    private String password;
//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    //private Set<Role> roles = new HashSet<>();
    private String role;
    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
