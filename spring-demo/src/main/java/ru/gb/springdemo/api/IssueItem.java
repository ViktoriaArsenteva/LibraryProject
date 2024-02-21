package ru.gb.springdemo.api;

import lombok.Data;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Reader;

import java.time.LocalDateTime;

@Data
public class IssueItem {
    private Book bookName;
    private Reader readerName;
    private LocalDateTime timestamp;

    public IssueItem(Book bookName, Reader readerName, LocalDateTime timestamp) {
        this.bookName = bookName;
        this.readerName = readerName;
        this.timestamp = timestamp;
    }
}
