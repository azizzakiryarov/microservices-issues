package se.azza.issuesservice.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import se.azza.issuesservice.constants.States;
import se.azza.issuesservice.constants.States.issueState;
import se.azza.issuesservice.model.Issue;
import se.azza.issuesservice.model.User;
import se.azza.issuesservice.repository.IssuesRepository;
import se.azza.issuesservice.repository.UserRepository;
import se.azza.issuesservice.resttemplates.RestTemplates;
import se.azza.issuesservice.services.IssueService;

@RestController
@RequestMapping("/issues")
public class IssuesResources {

	@Autowired
	IssuesRepository issuesRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	IssueService issueService;

	@Autowired
	RestTemplate restTemplate;

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping("/add")
	public ResponseEntity<Issue> addIssueToUser(@RequestParam(value = "comment") String comment,
			@RequestParam(value = "userId") long userId) {
		User user = RestTemplates.getUserById(restTemplate, userId);
		user.setUserState(States.userState.ACTIVE);
		userRepository.save(user);
		Issue addedIssueToUser = issueService.addIssueToUser(new Issue(comment, user));
		return new ResponseEntity<Issue>(addedIssueToUser, HttpStatus.CREATED);
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Issue> updateIssueForUser(@PathVariable(value = "id") Long id,
			@RequestParam(value = "userId") long userId, @RequestParam(value = "comment") String comment,
			@RequestParam(value = "issueState") issueState issueState) {
		User user = RestTemplates.getUserById(restTemplate, userId);
		Optional<Issue> currentIssue = issuesRepository.findById(id);
		Issue issue = new Issue(currentIssue.get().getId(), comment, issueState, user);
		issuesRepository.save(issue);
		return new ResponseEntity<Issue>(issue, HttpStatus.OK);
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PutMapping(value = "/updateState/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Issue> updateState(@PathVariable(value = "id") Long id,
			@RequestParam(value = "issueState") issueState issueState) {
		Optional<Issue> currentIssue = issuesRepository.findById(id);
		currentIssue.get().setIssueState(issueState);
		Issue savedIssue = issuesRepository.save(currentIssue.get());
		return new ResponseEntity<Issue>(savedIssue, HttpStatus.OK);
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PutMapping(value = "/assignIssueTo/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Issue> assignIssueTo(@PathVariable(value = "id") Long id,
			@RequestParam(value = "userId") long userId) {
		User user = RestTemplates.getUserById(restTemplate, userId);
		Optional<Issue> currentIssue = issuesRepository.findById(id);
		Issue issue = new Issue(currentIssue.get().getId(), currentIssue.get().getComment(),
				currentIssue.get().getIssueState(), user);
		issuesRepository.save(issue);
		return new ResponseEntity<Issue>(issue, HttpStatus.OK);
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@DeleteMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Issue> deleteIssueForUser(@PathVariable(value = "id") Long id,
			@RequestParam(value = "userId") String userId) {
		List<Issue> issues = issuesRepository.findByUserId(Long.parseLong(userId));
		Optional<Issue> issue = issuesRepository.findById(id);
		if (issues.contains(issue.get())) {
			issuesRepository.deleteById(id);
		}
		IssueService.isIssuesEmptyForUser(restTemplate, issuesRepository, userRepository, userId);
		return new ResponseEntity<Issue>(HttpStatus.OK);
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(path = "/getAllIssuesFor/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Issue>> getUserIssues(@PathVariable(value = "userId") long userId) {
		List<Issue> issues = issuesRepository.findByUserId(userId);
		return new ResponseEntity<List<Issue>>(issues, HttpStatus.OK);
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(path = "/getIssues", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Issue>> getIssues() {
		List<Issue> issues = issuesRepository.findAll();
		return new ResponseEntity<List<Issue>>(issues, HttpStatus.OK);
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(path = "/getUsedId/{issueId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Long> getUserId(@PathVariable(value = "issueId") long issueId) {
		Optional<Issue> issue = issuesRepository.findById(issueId);
		return new ResponseEntity<Long>(issue.get().getUser().getId(), HttpStatus.OK);
	}
}