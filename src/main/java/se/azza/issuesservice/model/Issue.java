package se.azza.issuesservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import se.azza.issuesservice.constants.States.issueState;

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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public issueState getIssueState() {
		return issueState;
	}

	public void setIssueState(issueState issueState) {
		this.issueState = issueState;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((issueState == null) ? 0 : issueState.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Issue other = (Issue) obj;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (id != other.id)
			return false;
		if (issueState != other.issueState)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
}