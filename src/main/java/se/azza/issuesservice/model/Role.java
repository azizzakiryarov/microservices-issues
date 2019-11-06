package se.azza.issuesservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import se.azza.issuesservice.constants.States.userRole;

@Entity
@Table(name = "roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Enumerated(EnumType.STRING)
	private userRole userRole;
	@Column(name = "roleDescription", nullable = false)
	private String roleDescription;

	public Role() {
	}

	public Role(long id, userRole userRole, String roleDescription) {
		super();
		this.id = id;
		this.userRole = userRole;
		this.roleDescription = roleDescription;
	}

	public Role(userRole userRole, String roleDescription) {
		super();
		this.userRole = userRole;
		this.roleDescription = roleDescription;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public userRole getUserRole() {
		return userRole;
	}

	public void setUserRole(userRole userRole) {
		this.userRole = userRole;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((roleDescription == null) ? 0 : roleDescription.hashCode());
		result = prime * result + ((userRole == null) ? 0 : userRole.hashCode());
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
		Role other = (Role) obj;
		if (id != other.id)
			return false;
		if (roleDescription == null) {
			if (other.roleDescription != null)
				return false;
		} else if (!roleDescription.equals(other.roleDescription))
			return false;
		if (userRole != other.userRole)
			return false;
		return true;
	}
}