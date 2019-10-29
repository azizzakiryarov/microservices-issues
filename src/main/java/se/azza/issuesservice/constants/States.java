package se.azza.issuesservice.constants;

public class States {
	
	public enum userState {
		ACTIVE,
		INACTIVE
	}
	
	public enum issueState {
		INWORK,
		PAUSED,
		REOPENED,
		SOLVED,
		CLOSED
	}
	
	public enum userRole {
		ADMIN,
		USER
	}
}