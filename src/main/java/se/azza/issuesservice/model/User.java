package se.azza.issuesservice.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.*;
import se.azza.issuesservice.constants.States.userState;

import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "firstName", nullable = false)
	private String firstName;
	@Column(name = "lastName", nullable = false)
	private String lastName;
	@Column(name = "userName", nullable = false, unique = true)
	private String userName;
	@Column(name = "password", nullable = false, unique = true)
	private String password;
	@Enumerated(EnumType.STRING)
	private userState userState;

	@ManyToOne
	private Team team;

	@OneToOne(cascade = CascadeType.ALL) 
	private Role role;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return id == user.id && firstName.equals(user.firstName) && lastName.equals(user.lastName) && userName.equals(user.userName) && password.equals(user.password) && userState == user.userState && team.equals(user.team) && role.equals(user.role);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, firstName, lastName, userName, password, userState, team, role);
	}
}