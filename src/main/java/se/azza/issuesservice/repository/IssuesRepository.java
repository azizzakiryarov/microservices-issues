package se.azza.issuesservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import se.azza.issuesservice.model.Issue;

public interface IssuesRepository extends JpaRepository<Issue, Long> {

}