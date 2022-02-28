package se.azza.issuesservice.services;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import se.azza.issuesservice.constants.States;
import se.azza.issuesservice.model.Issue;
import se.azza.issuesservice.model.Role;
import se.azza.issuesservice.model.Team;
import se.azza.issuesservice.model.User;
import se.azza.issuesservice.repository.IssuesRepository;
import se.azza.issuesservice.repository.UserRepository;
import org.springframework.web.client.RestTemplate;
import se.azza.issuesservice.resttemplates.RestTemplates;
import static org.mockito.Mockito.*;
import static se.azza.issuesservice.resttemplates.RestTemplates.getUserById;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class IssuesServiceTest {

    IssueService issueService;

    @Mock
    UserRepository mockUserRepository;

    @Mock
    IssuesRepository mockIssueRepository;

    @Mock
    RestTemplate mockRestTemplateWebClient;

    @Mock
    RestTemplates mockRestTemplates;

    @Mock
    Issue mockIssue;

    @Mock
    User mockUser;

    @Mock
    Role mockRole;

    @Mock
    Team mockTeam;

    @Before
    public void init(){
        mockUser = new User();
        mockUser.setId(1L);
        mockUser.setFirstName("firstname");
        mockUser.setLastName("lastname");
        mockUser.setUserName("username");
        mockUser.setPassword("password");
        mockUser.setUserState(States.userState.ACTIVE);

        mockRole = new Role();
        mockRole.setId(1L);
        mockRole.setUserRole(States.userRole.ADMIN);
        mockRole.setRoleDescription("Bla bla bla");

        mockTeam = new Team();
        mockTeam.setId(1L);
        mockTeam.setTeamName("Alfa");
        mockTeam.setTeamState(States.teamState.ACTIVE);
        mockTeam.setTeamDescription("Bla bla bla");

        mockUser.setTeam(mockTeam);
        mockUser.setRole(mockRole);

        mockIssue = new Issue("Bla bla bla", mockUser);

        issueService = new IssueService(mockIssueRepository, mockUserRepository, mockRestTemplateWebClient);
    }

    @Test
    public void addIssueToUserSuccessfully() {

        ResponseEntity<Issue> expectedIssueResponseEntity = new ResponseEntity<>(mockIssue, HttpStatus.CREATED);

        when(getUserById(mockRestTemplateWebClient, mockUser.getId())).thenReturn(mockUser);
        when(mockIssueRepository.save(mockIssue)).thenReturn(mockIssue);

        ResponseEntity<Issue> actualIssueResponseEntity = issueService.addIssueToUser(mockIssue.getComment(), mockUser.getId());
        Assert.assertEquals(expectedIssueResponseEntity, actualIssueResponseEntity);
    }

    @Test
    public void addIssueToUserFailUserIsNull() {

        ResponseEntity<Issue> expectedIssueResponseEntity = new ResponseEntity<>(HttpStatus.BAD_GATEWAY);

        when(getUserById(mockRestTemplateWebClient, 2L)).thenReturn(null);

        ResponseEntity<Issue> actualIssueResponseEntity = issueService.addIssueToUser(anyString(), anyLong());
        Assert.assertEquals(expectedIssueResponseEntity, actualIssueResponseEntity);
    }

    @Test
    public void addIssueToUserFailGetUserByIdThrowException() {

        ResponseEntity<Issue> expectedIssueResponseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        when(RestTemplates.getUserById(mockRestTemplateWebClient, 0L)).thenThrow(new RestClientException("Error"));

        ResponseEntity<Issue> actualIssueResponseEntity = issueService.addIssueToUser(anyString(), anyLong());

        Assert.assertEquals(expectedIssueResponseEntity, actualIssueResponseEntity);
    }

    @Test
    public void updateIssueForUser() {
    }

    @Test
    public void updateState() {
    }

    @Test
    public void assignIssueTo() {
    }

    @Test
    public void deleteIssueForUser() {
    }

    @Test
    public void getUserIssues() {
    }

    @Test
    public void getIssues() {
    }

    @Test
    public void getUserId() {
    }
}