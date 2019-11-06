package se.azza.issuesservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import se.azza.issuesservice.model.Issue;

public interface IssuesRepository extends JpaRepository<Issue, Long> {
	List<Issue> findByUserId(Long id);
}