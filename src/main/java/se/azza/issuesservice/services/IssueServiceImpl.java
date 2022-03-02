package se.azza.issuesservice.services;

import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import se.azza.issuesservice.constants.States;
import se.azza.issuesservice.model.Issue;
import se.azza.issuesservice.model.User;
import se.azza.issuesservice.repository.IssuesRepository;
import se.azza.issuesservice.repository.UserRepository;
import se.azza.issuesservice.resttemplates.RestTemplates;

@Slf4j
@AllArgsConstructor
@Service
public class IssueServiceImpl implements IssueService {

    private final IssuesRepository issuesRepository;
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    public ResponseEntity<Issue> addIssueToUser(String comment, long userId) {
        try {
            User user = RestTemplates.getUserById(restTemplate, userId);
            if (Objects.nonNull(user)) {
                user.setUserState(States.userState.ACTIVE);
                log.info("Trying to save user: {}", user.getUserName());
                userRepository.save(user);
                log.info("Successfully saved user: {}", user.getUserName());
                log.info("Trying to save issue with comment: {}, to user: {}", comment, user.getUserName());
                Issue savedIssue = issuesRepository.save(new Issue(comment, user));
                log.info("Successfully saved issue with comment: {}, to user: {}", comment, user.getUserName());
                return new ResponseEntity<>(savedIssue, HttpStatus.CREATED);
            }
        } catch (RestClientException restClientException) {
            log.debug("User with userId: {} is not found! ", userId);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
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