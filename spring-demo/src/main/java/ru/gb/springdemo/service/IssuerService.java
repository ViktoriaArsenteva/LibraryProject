package ru.gb.springdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.api.IssueRequest;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.repository.BookRepository;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.repository.ReaderRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class IssuerService {

  // спринг это все заинжектит
  private final BookRepository bookRepository;
  private final ReaderRepository readerRepository;
  private final IssueRepository issueRepository;

  public Issue issue(IssueRequest request) {
    if (bookRepository.findById(request.getBookId()) == null) {
      throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
    }
    if (readerRepository.findById(request.getReaderId()) == null) {
      throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
    }
    // можно проверить, что у читателя нет книг на руках (или его лимит не превышает в Х книг)
    List<Issue> issueList = issueRepository.findAll();
    long countBooksOnHand = issueList.stream().filter(issue -> Objects.equals(issue.getReaderId(),request.getReaderId())).count();
    if (countBooksOnHand > 2){
      throw new IllegalArgumentException("превышен лимит выдачи книг");
    }
    Issue issue = new Issue(request.getBookId(), request.getReaderId());
    issueRepository.save(issue);
    return issue;
  }
  public Issue getIssueById(long id){
    Issue issue = issueRepository.findById(id).get();
    if (issue!=null){
      return issue;
    }else {
      throw new NoSuchElementException("не найдена выдача книги с id: "+ id);
    }
  }
  public List<Issue> getAllIssues(){
    return issueRepository.findAll();
  }
}
