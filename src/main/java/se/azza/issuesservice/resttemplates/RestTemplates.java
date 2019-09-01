package se.azza.issuesservice.resttemplates;

import org.springframework.web.client.RestTemplate;

import se.azza.issuesservice.constants.States;
import se.azza.issuesservice.model.User;

public class RestTemplates {
	
	public RestTemplates() {
		super();
	}
	
	public static User getUserById (RestTemplate restTemplate, long userId) {
		User user = restTemplate.getForObject("http://microservices-users/users/get/" + userId, User.class);
		if (user.getUserState().equals(States.userState.INACTIVE)) {
			user.setUserState(States.userState.ACTIVE);
		}
		return user;
	}
}