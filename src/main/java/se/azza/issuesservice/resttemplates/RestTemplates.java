package se.azza.issuesservice.resttemplates;

import org.springframework.web.client.RestTemplate;

import se.azza.issuesservice.model.User;

public class RestTemplates {

	public RestTemplates() {
		super();
	}

	public static User getUserById(RestTemplate restTemplate, long userId) {
		return restTemplate.getForObject("http://microservices-users/users/get/" + userId, User.class);
	}
}