package ru.gb.springdemo.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.service.ReaderService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/reader")
@Tag(name = "Reader")
public class ReaderController {
    @Autowired
    private ReaderService readerService;


    @GetMapping("/{id}")
    @Operation(summary = "get Reader", description = "Загружает читателя по ID")
    public ResponseEntity<Reader> getById(@PathVariable long id){
        Reader reader = null;
        try {
            reader = readerService.getReaderById(id);
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(reader);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "delete Reader", description = "удаляет читателя по ID")
    public ResponseEntity<Reader>  delById(@PathVariable long id){
        Reader reader = null;
        try {
            reader = readerService.delReaderById(id);
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(reader);
    }
    @PostMapping()
    @Operation(summary = "create Reader", description = "Создает читателя по имени")
    public ResponseEntity<Reader> createReader(@RequestBody Reader reader){
        Reader reader1 = readerService.createReader(reader.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(reader1);
    }
    @GetMapping("/{id}/issue")
    @Operation(summary = "get All Issues for reader", description = "Выдает список выдачей читателя по его ID")
    public ResponseEntity<List<Issue> >getAllIssues(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK).body(readerService.getIssuesForReaderId(id));
    }
}
