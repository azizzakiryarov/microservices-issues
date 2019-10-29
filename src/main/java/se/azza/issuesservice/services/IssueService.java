package se.azza.issuesservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import se.azza.issuesservice.constants.States;
import se.azza.issuesservice.model.Issue;
import se.azza.issuesservice.model.User;
import se.azza.issuesservice.repository.IssuesRepository;
import se.azza.issuesservice.repository.UserRepository;
import se.azza.issuesservice.resttemplates.RestTemplates;

@Service
public class IssueService {
	
	@Autowired
	IssuesRepository issuesRepository;

	@Autowired
	UserRepository userRepository;

	public Issue addIssueToUser(Issue issue) {
		return issuesRepository.save(issue);
	}

	public static void isIssuesEmptyForUser(RestTemplate restTemplate, IssuesRepository issuesRepositoryFromResource,
			UserRepository userRepositoryFromResource, String userId) {
		List<Issue> getIssuesAfterDelete = issuesRepositoryFromResource.findByUserId(Long.parseLong(userId));
		if (getIssuesAfterDelete.isEmpty()) {
			User user = RestTemplates.getUserById(restTemplate, Long.parseLong(userId));
			user.setUserState(States.userState.INACTIVE);
			userRepositoryFromResource.save(user);
		}
	}
}