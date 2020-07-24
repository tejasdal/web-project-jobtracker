package com.web.project.jobtracker.JobAnalysis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface JobActivityRepository extends JpaRepository<com.web.project.jobtracker.JobAnalysis.model.JobActivityModel, Integer> {
	
	@Query(value = "SELECT COUNT(*) FROM `activity` WHERE YEAR(`date_created`)=:year && MONTH(`date_created`)=:month && activity_status=0  && user_id=:user_id",nativeQuery = true)
	Integer pendingActivity( @Param("year") Integer year,@Param("month") Integer month,@Param("user_id") String user_id);

	
	@Query(value = "SELECT COUNT(*) FROM `activity` WHERE YEAR(`date_completed`)=:year AND  MONTH(`date_completed`)=:month AND activity_status=1  && user_id=:user_id",nativeQuery = true)
	Integer completedActivity( @Param("year") Integer year,@Param("month") Integer month,@Param("user_id") String user_id);	

}
