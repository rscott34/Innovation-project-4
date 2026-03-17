package Group4.tracer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Group4.tracer.model.issueReport;

@Repository
public interface IssueRepository extends JpaRepository<issueReport, Long> {
}