package se.azza.issuesservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import se.azza.issuesservice.constants.States.teamState;

@Entity
@Table(name = "teams")
public final class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "teamName", nullable = false)
	private String teamName;
	@Column(name = "teamDescription", nullable = false)
	private String teamDescription;
	@Enumerated(EnumType.STRING)
	private teamState teamState;

	public Team() {
	}

	public Team(long id, String teamName, String teamDescription, teamState teamState) {
		super();
		this.id = id;
		this.teamName = teamName;
		this.teamDescription = teamDescription;
		this.teamState = teamState;
	}

	public Team(String teamName, String teamDescription, teamState teamState) {
		super();
		this.teamName = teamName;
		this.teamDescription = teamDescription;
		this.teamState = teamState;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamDescription() {
		return teamDescription;
	}

	public void setTeamDescription(String teamDescription) {
		this.teamDescription = teamDescription;
	}

	public teamState getTeamState() {
		return teamState;
	}

	public void setTeamState(teamState teamState) {
		this.teamState = teamState;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((teamDescription == null) ? 0 : teamDescription.hashCode());
		result = prime * result + ((teamName == null) ? 0 : teamName.hashCode());
		result = prime * result + ((teamState == null) ? 0 : teamState.hashCode());
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
		Team other = (Team) obj;
		if (id != other.id)
			return false;
		if (teamDescription == null) {
			if (other.teamDescription != null)
				return false;
		} else if (!teamDescription.equals(other.teamDescription))
			return false;
		if (teamName == null) {
			if (other.teamName != null)
				return false;
		} else if (!teamName.equals(other.teamName))
			return false;
		if (teamState != other.teamState)
			return false;
		return true;
	}
}