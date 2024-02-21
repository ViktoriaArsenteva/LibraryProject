package ru.gb.springdemo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "roles")
@Schema(name = "статус")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "Идентификатор")
    private Long id;
    @Schema(name = "Имя")
    private String name;
//    @ManyToMany(cascade = {
//            CascadeType.PERSIST,
//            CascadeType.MERGE
//    }, fetch = FetchType.EAGER, mappedBy = "roles")
//    private Set<User> users = new HashSet<>();

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }
}
