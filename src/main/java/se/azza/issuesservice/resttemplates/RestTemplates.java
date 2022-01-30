package se.azza.issuesservice.resttemplates;

import org.springframework.web.client.RestTemplate;

import se.azza.issuesservice.model.User;

public class RestTemplates {

	public RestTemplates() {
		super();
	}

	public static User getUserById(RestTemplate restTemplate, long userId) {
		return restTemplate.getForObject("http://microservices-users-deployment-0.microservices-users.microservices.svc.cluster.local:8081/users/get/" + userId, User.class);
	}
}