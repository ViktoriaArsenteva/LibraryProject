package ru.gb.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Issue;

@Repository
public interface IssueRepository extends JpaRepository<Issue,Long> {

//  private final List<Issue> issues;
//
//  public IssueRepository() {
//    this.issues = new ArrayList<>();
//  }
//
//  public void save(Issue issue) {
//    // insert into ....
//    issues.add(issue);
//  }
//  public Issue getIssue(long id){
//    return issues.stream().filter(is -> Objects.equals(is.getId(),id)).findFirst().orElse(null);
//  }
//  public List<Issue> getIssues(){
//    List<Issue>copyIssues = new ArrayList<>(issues);
//    return copyIssues;
//  }

}
