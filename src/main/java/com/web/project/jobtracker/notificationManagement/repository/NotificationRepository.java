package com.web.project.jobtracker.notificationManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web.project.jobtracker.notificationManagement.model.NotificationModel;


@Repository
public interface NotificationRepository extends JpaRepository<NotificationModel, Integer> {
	
	@Query(value = "SELECT activity_detail FROM `activity` WHERE (activity_deadline BETWEEN :Sdate AND :Edate) && activity_status=0 && user_id=:user_id",nativeQuery = true)
	List<String> NotificationData( @Param("Sdate") String CurrentDate,@Param("Edate") String WeekAfterDate,@Param("user_id") String user_id);

	
	@Query(value = "SELECT activity_deadline FROM `activity` WHERE (activity_deadline BETWEEN :Sdate AND :Edate) &&  activity_status=0 && user_id=:user_id",nativeQuery = true)
	List<String> NotificationDeadline( @Param("Sdate") String CurrentDate,@Param("Edate") String WeekAfterDate,@Param("user_id") String user_id);

	
}
