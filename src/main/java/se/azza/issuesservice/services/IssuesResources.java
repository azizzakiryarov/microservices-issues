package se.azza.issuesservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import se.azza.issuesservice.model.Issue;
import se.azza.issuesservice.model.User;
import se.azza.issuesservice.repository.IssuesRepository;

@RestController
@RequestMapping("/issues")
public class IssuesResources {

	@Autowired
	IssuesRepository issuesRepository;

	@Autowired
	RestTemplate restTemplate;

	@RequestMapping("/add")
	public ResponseEntity<Issue> addIssueToUser(@RequestParam(value = "comment") String comment,
			@RequestParam(value = "state") String state, @RequestParam(value = "userId") long userId) {

		User user = restTemplate.getForObject("http://microservices-users/users/get/" + userId, User.class);
		Issue newIssue = new Issue(comment, state, user);
		issuesRepository.save(newIssue);
		return new ResponseEntity<Issue>(newIssue, HttpStatus.CREATED);
	}

	@GetMapping(path = "/get/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Issue>> getUserIssues(@PathVariable(value = "userId") long userId) {
		List<Issue> issues = issuesRepository.findByUserId(userId);
		return new ResponseEntity<List<Issue>>(issues, HttpStatus.OK);
	}
}