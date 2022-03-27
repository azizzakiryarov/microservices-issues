package se.azza.issuesservice.services;

import org.springframework.http.ResponseEntity;
import se.azza.issuesservice.constants.States;
import se.azza.issuesservice.model.Issue;

public interface IssueService {

    ResponseEntity<Issue> addIssueToUser(String comment, long userId);

    ResponseEntity<Issue> updateIssueForUser(Long id, Long userId, String comment, States.issueState issueState);
}