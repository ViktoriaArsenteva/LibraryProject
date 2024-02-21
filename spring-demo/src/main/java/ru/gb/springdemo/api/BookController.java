package ru.gb.springdemo.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.service.BookService;
import ru.gb.timer.aspect.Timer;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/book")
@Tag(name = "Book")
@Timer
public class BookController {
    @Autowired
    private BookService bookService;


    @GetMapping("/{id}")
    @Operation(summary = "get book by ID", description = "Загружает книгу по ID")
    public ResponseEntity<Book> getById(@PathVariable long id){
        Book book = null;
        try {
            book = bookService.getBookById(id);
        }catch (NoSuchElementException e){
           return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "delete book by ID", description = "Удаляет книгу по ID")
    public ResponseEntity<Book>  delById(@PathVariable long id){
        Book book = null;
        try {
            book = bookService.delBookById(id);
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(book);
    }
    @PostMapping()
    @Operation(summary = "create book", description = "Создает книгу по имени")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        Book book1 = bookService.createBook(book.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(book1);
    }

}
