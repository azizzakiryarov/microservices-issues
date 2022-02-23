package se.azza.issuesservice.model;

import lombok.Getter;
import lombok.Setter;
import se.azza.issuesservice.constants.States.issueState;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "issues")
public class Issue {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "comment", nullable = false)
	private String comment;
	@Enumerated(EnumType.STRING)
	private issueState issueState;

	@ManyToOne
	private User user;

	public Issue() {
	}

	public Issue(long id, String comment, issueState issueState, User user) {
		super();
		this.id = id;
		this.comment = comment;
		this.issueState = issueState;
		this.user = user;
	}

	public Issue(String comment, User user) {
		super();
		this.comment = comment;
		this.issueState = se.azza.issuesservice.constants.States.issueState.INWORK;
		this.user = user;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Issue issue = (Issue) o;
		return id == issue.id && comment.equals(issue.comment) && issueState == issue.issueState && user.equals(issue.user);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, comment, issueState, user);
	}
}