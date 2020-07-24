package com.web.project.jobtracker.JobAnalysis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.project.jobtracker.JobAnalysis.model.jobApplicationModel;



public interface JobApplicationRepository extends JpaRepository<jobApplicationModel, Integer> {
	
	@Query(value = "SELECT * FROM  jobapplication",nativeQuery = true)
	List<jobApplicationModel> findAllActiveUsersNative();
	
	@Query(value = "SELECT count(*) FROM jobapplication where status_id=:status && updated_date>:date && user_id=:user_id",nativeQuery = true)
	int findCount( @Param("status") Integer status,@Param("date") String date,@Param("user_id") String user_id);
	
}
