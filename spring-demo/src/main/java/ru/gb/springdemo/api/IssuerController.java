package ru.gb.springdemo.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.service.IssuerService;

import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/issue")
@Tag(name = "Issue")
public class IssuerController {

  @Autowired
  private IssuerService service;

//  @PutMapping
//  public void returnBook(long issueId) {
//    // найти в репозитории выдачу и проставить ей returned_at
//  }

  @PostMapping
  @Operation(summary = "create issue", description = "Создает событие выдачи книги")
  public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest request) {
    log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", request.getReaderId(), request.getBookId());

    final Issue issue;
    try {
      issue = service.issue(request);
    } catch (NoSuchElementException e) {
      return ResponseEntity.notFound().build();
    } catch (IllegalArgumentException e){
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    return ResponseEntity.status(HttpStatus.CONFLICT).body(issue);
  }
  @GetMapping("/{id}")
  @Operation(summary = "get issue", description = "Загружает событие выдачи книги по идентификатору")
  public ResponseEntity<Issue> getIssue(@PathVariable long id){
    final Issue issue;
    try {
      issue = service.getIssueById(id);
    }catch (NoSuchElementException e) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(issue);
  }

}
