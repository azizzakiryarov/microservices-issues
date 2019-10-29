package se.azza.issuesservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import se.azza.issuesservice.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}