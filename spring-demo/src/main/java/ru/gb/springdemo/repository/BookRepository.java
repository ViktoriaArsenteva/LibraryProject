package ru.gb.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {


//    private final List<Book> books;
//
//  public BookRepository() {
//    this.books = new ArrayList<>();
//  }
//
//  @PostConstruct
//  public void generateData() {
//    books.addAll(List.of(
//      new Book("война и мир"),
//      new Book("мертвые души"),
//      new Book("чистый код")
//    ));
//  }
//
//  public Book getBookById(long id) {
//    return books.stream().filter(it -> Objects.equals(it.getId(), id))
//      .findFirst()
//      .orElse(null);
//  }
//  public Book delBookById(long id){
//    Book book = getBookById(id);
//    if (book != null){
//      books.remove(book);
//    }
//    return book;
//  }
//   public Book createBook(String name){
//    Book book = new Book(name);
//    books.add(book);
//    return book;
//   }
//    public List<Book> getAllBooks(){
//        List<Book>copyBooks = new ArrayList<>(books);
//        return copyBooks;
//    }
}
