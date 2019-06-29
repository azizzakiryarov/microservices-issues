package se.azza.issuesservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import se.azza.issuesservice.model.Issue;
import se.azza.issuesservice.repository.IssuesRepository;

@RestController
@RequestMapping("/issues")
public class IssuesResources {

	@Autowired
	IssuesRepository issuesRepository;

	@RequestMapping("/add")
	public ResponseEntity<Issue> addIssueToUser(@RequestParam(value = "comment") String comment,
			@RequestParam(value = "state") String state, @RequestParam(value = "userId") Long userId) {

		Issue newIssue = new Issue(comment, state, userId);
		issuesRepository.save(newIssue);
		return new ResponseEntity<Issue>(newIssue, HttpStatus.CREATED);
	}
}
