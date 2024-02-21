package ru.gb.springdemo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Запись о факте выдачи книги (в БД)
 */
@Entity
@Table(name = "issues")
@Data
// @Entity
@Schema(name = "Выдача")
public class Issue {

 // public static long sequence = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(name = "идентификатор")
  private  Long id;
  @Schema(name = "идентификатор книги")
  private  Long bookId;
  @Schema(name = "идентификатор читателя")
  private  Long readerId;

  public Issue(Long id, Long bookId, Long readerId) {
    this.id = id;
    this.bookId = bookId;
    this.readerId = readerId;
    this.timestamp = LocalDateTime.now();
  }

  /**
   * Дата выдачи
   */
  private  LocalDateTime timestamp;

  public Issue(Long bookId, Long readerId) {
    this.bookId = bookId;
    this.readerId = readerId;
    this.timestamp = LocalDateTime.now();
  }

  public Issue() {

  }
}
