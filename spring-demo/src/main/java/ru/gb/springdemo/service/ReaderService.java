package ru.gb.springdemo.service;

import org.springframework.stereotype.Service;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.repository.ReaderRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class ReaderService {
    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;

    public ReaderService(ReaderRepository readerRepository, IssueRepository issueRepository) {
        this.readerRepository = readerRepository;
        this.issueRepository = issueRepository;
    }
    public Reader getReaderById(Long id){
        Reader reader = readerRepository.findById(id).get();
        if (reader != null){
            return reader;
        }else {
            throw new NoSuchElementException("не найден читатель с id: "+ id);
        }
    }
    public Reader delReaderById(Long id){
        Reader reader = readerRepository.findById(id).get();
        if (reader != null){
            readerRepository.delete(reader);
            return reader;
        }else {
            throw new NoSuchElementException("не найден читатель с id: "+ id);
        }
    }
    public Reader createReader(String name){
        Reader reader = new Reader(name);
        return readerRepository.save(reader);
    }
    public List<Issue> getIssuesForReaderId(long id){
        List<Issue> issues = issueRepository.findAll();
        return issues.stream().filter(issue -> Objects.equals(issue.getReaderId(),id)).toList();
    }
    public List<Reader> getAllReader(){
        return readerRepository.findAll();
    }
}
