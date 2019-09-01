package se.azza.issuesservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import se.azza.issuesservice.constants.States.issueState;
import se.azza.issuesservice.model.Issue;
import se.azza.issuesservice.model.User;
import se.azza.issuesservice.repository.IssuesRepository;
import se.azza.issuesservice.resttemplates.RestTemplates;

@RestController
@RequestMapping("/issues")
public class IssuesResources {

	@Autowired
	IssuesRepository issuesRepository;

	@Autowired
	RestTemplate restTemplate;

	@RequestMapping("/add")
	public ResponseEntity<Issue> addIssueToUser(@RequestParam(value = "comment") String comment,
			@RequestParam(value = "userId") long userId) {

		User user = RestTemplates.getUserById(restTemplate, userId);
		Issue newIssue = new Issue(comment, user);
		issuesRepository.save(newIssue);
		return new ResponseEntity<Issue>(newIssue, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Issue> updateIssueForUser(@PathVariable(value = "id") Long id, @RequestParam(value = "userId") long userId, 
			@RequestParam(value = "comment") String comment, @RequestParam(value = "issueState") issueState issueState){
		
		User user = RestTemplates.getUserById(restTemplate, userId);
		Optional<Issue> currentIssue = issuesRepository.findById(id);
		Issue issue = new Issue(currentIssue.get().getId(), comment, issueState, user);
		issuesRepository.save(issue);
		return new ResponseEntity<Issue>(issue, HttpStatus.OK);
	}

	@GetMapping(path = "/get/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Issue>> getUserIssues(@PathVariable(value = "userId") long userId) {
		List<Issue> issues = issuesRepository.findByUserId(userId);
		return new ResponseEntity<List<Issue>>(issues, HttpStatus.OK);
	}
}