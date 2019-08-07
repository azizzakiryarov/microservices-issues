package se.azza.issuesservice.services;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@RequestMapping("/add")
	public ResponseEntity<Issue> addIssueToUser(@RequestParam(value = "comment") String comment,
			@RequestParam(value = "state") String state, @RequestParam(value = "userId") long userId) {
		
		RestTemplate restTemplate = new RestTemplate();
		User user = restTemplate.getForObject("http://localhost:8081/users/get/" + userId, User.class);
		
		Issue newIssue = new Issue(comment, state, user);
		issuesRepository.save(newIssue);
		return new ResponseEntity<Issue>(newIssue, HttpStatus.CREATED);
	}
}