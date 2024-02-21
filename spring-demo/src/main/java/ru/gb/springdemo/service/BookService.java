package ru.gb.springdemo.service;

import org.springframework.stereotype.Service;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.repository.BookRepository;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class BookService {
    private final BookRepository bookRepository;


    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book getBookById(Long id){
        Book book = bookRepository.findById(id).get();
        if (book != null){
            return book;
        }else {
            throw new NoSuchElementException("не найдена книга с id: "+ id);
        }
    }
    public Book delBookById(Long id){
        Book book = bookRepository.findById(id).get();
        if (book != null){
            bookRepository.delete(book);
            return book;
        }else {
            throw new NoSuchElementException("не найдена книга с id: "+ id);
        }
    }
    public Book createBook(String name){
        Book book = new Book(name);
        return bookRepository.save(book);
    }
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

}
