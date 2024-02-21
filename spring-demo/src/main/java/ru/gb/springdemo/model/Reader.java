package ru.gb.springdemo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "readers")
@Data
@RequiredArgsConstructor
@Schema(name = "Читатель")
public class Reader {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(name = "идентификатор")
  private  Long id;
  @Schema(name = "имя")
  private  String name;

  public Reader(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Reader(String name) {
    this.name = name;
  }
}
