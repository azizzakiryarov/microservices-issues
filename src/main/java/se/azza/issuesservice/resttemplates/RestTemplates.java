package se.azza.issuesservice.resttemplates;

import org.springframework.web.client.RestTemplate;

import se.azza.issuesservice.model.User;

import static se.azza.issuesservice.constants.Urls.GET_USER_BY_ID;

public class RestTemplates {

	public RestTemplates() {
		super();
	}

	public static User getUserById(RestTemplate restTemplate, long userId) {
		return restTemplate.getForObject(GET_USER_BY_ID + userId, User.class);
	}
}