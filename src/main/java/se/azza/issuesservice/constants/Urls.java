package se.azza.issuesservice.constants;

public final class Urls {

    public static final String BASE_URL = "http://microservices-users-deployment-0.microservices-users.microservices.svc.cluster.local:8081";

    public static final String GET_USER_BY_ID = BASE_URL + "/users/get/";

    private Urls() {}
}
