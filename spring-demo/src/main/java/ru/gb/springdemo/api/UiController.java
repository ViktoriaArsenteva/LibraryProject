package ru.gb.springdemo.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.service.BookService;
import ru.gb.springdemo.service.IssuerService;
import ru.gb.springdemo.service.ReaderService;

import java.util.ArrayList;
import java.util.List;

@RestController
@Tag(name = "UI")
public class UiController {

    private BookService bookService;
    private ReaderService readerService;
    private IssuerService issuerService;

    public UiController(BookService bookService, ReaderService readerService, IssuerService issuerService) {
        this.bookService = bookService;
        this.readerService = readerService;
        this.issuerService = issuerService;
    }

    @GetMapping("ui/books")
    @Operation(summary = "get all books", description = "Выдает список всех книг")
    public String books(Model model){
        List<Book> bookList = bookService.getAllBooks();
        model.addAttribute("bookList",bookList);
        return "books";
    }

    @GetMapping("ui/reader")
    @Operation(summary = "get all readers", description = "Выдает список всех читателей")
    public String readers(Model model){
        List<Reader> readerList = readerService.getAllReader();
        model.addAttribute("readerList",readerList);
        return "readers";
    }
    @GetMapping("ui/issues")
    @Operation(summary = "get all issues", description = "Выдает список всех выдачей")
    public String issues(Model model){
        List<Issue> issueList = issuerService.getAllIssues();
        List<IssueItem>issueItems = new ArrayList<>();
        for (Issue issue:issueList) {
            issueItems.add(new IssueItem(bookService.getBookById(issue.getBookId()),readerService.getReaderById(issue.getReaderId()),issuerService.getIssueById(issue.getId()).getTimestamp()));
        }
        model.addAttribute("issueItems",issueItems);
        return "issues";
    }
    @GetMapping("ui/reader/{id}")
    @Operation(summary = "get reader issues", description = "Выдает список всех выдачей читателя по ID")
    public String readerIssues(@PathVariable long id, Model model){
        List<Issue> issueList = readerService.getIssuesForReaderId(id);
        Reader reader = readerService.getReaderById(id);
        List<IssueItem>issueItems = new ArrayList<>();
        for (Issue issue:issueList) {
            issueItems.add(new IssueItem(bookService.getBookById(issue.getBookId()),readerService.getReaderById(issue.getReaderId()),issuerService.getIssueById(issue.getId()).getTimestamp()));
        }
        model.addAttribute("issueItems",issueItems);
        model.addAttribute("reader",reader);
        return "readerIssues";
    }

}
