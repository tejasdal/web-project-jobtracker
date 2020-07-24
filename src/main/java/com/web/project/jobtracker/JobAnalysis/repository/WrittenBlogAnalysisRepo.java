package com.web.project.jobtracker.JobAnalysis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface WrittenBlogAnalysisRepo extends JpaRepository<com.web.project.jobtracker.JobAnalysis.model.WrittenBlogModel, Integer> {
	
	
	@Query(value = "SELECT count(*) FROM blog where YEAR(`created_date`)=:year &&  MONTH(`created_date`)=:month  && user_id=:user_id",nativeQuery = true)
	int findWrittenCount( @Param("year") int year,@Param("month") int month,@Param("user_id") String user_id);

}
