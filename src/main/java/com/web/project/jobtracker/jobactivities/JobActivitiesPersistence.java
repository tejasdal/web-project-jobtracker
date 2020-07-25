package com.web.project.jobtracker.jobactivities;

import com.web.project.jobtracker.configurations.DBConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Anudish Jinturkar
 * Persistence class containing persistence logic to perform CRU operation on job activities table of DB.
 */

@Repository
public class JobActivitiesPersistence implements IJobActivitiesPersistence{

    Logger log = LoggerFactory.getLogger(JobActivitiesPersistence.class);

    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;

    public static final String FIND_ALL = "SELECT id,user_id,date_created,date_completed,activity_status,activity_deadline,activity_detail FROM activity WHERE user_id= ?";
    public static final String INSERT = "INSERT INTO activity(user_id, date_created,activity_status,activity_deadline,activity_detail) VALUE(?,?,?,?,?)";
    public static final String UPDATE = "UPDATE activity SET activity_status = ?, date_completed = ? WHERE id = ?";
    public static final String FIND_BY_ID = "SELECT id,user_id,date_created,date_completed,activity_status,activity_deadline,activity_detail FROM activity WHERE id = ?";

    @Override
    public List<JobActivities> getAllJobActivities(String userID) {
        try{
             this.getConnection();
             this.getPreparedStatement(FIND_ALL);
             this.preparedStatement.setString(1,userID);
             ResultSet resultSet = this.preparedStatement.executeQuery();
             List<JobActivities> jobActivitiesList = new ArrayList<JobActivities>();
             while (resultSet.next()){
                 JobActivities ja = new JobActivities();
                 ja.setId(resultSet.getInt("id"));
                 ja.setUser_id(resultSet.getString("user_id"));
                 ja.setActivity_status(resultSet.getByte("activity_status"));
                 ja.setActivity_detail(resultSet.getString("activity_detail"));
                 ja.setActivity_deadline(resultSet.getDate("activity_deadline"));
                 ja.setDate_created(resultSet.getDate("date_created"));
                 ja.setDate_completed(resultSet.getDate("date_completed"));
                 jobActivitiesList.add(ja);
             }
             return  jobActivitiesList;
        }catch (SQLException e){
            log.error("Error executing select query on Activities table: {}", e.getMessage());
            return null;
        } finally {
            this.closePreparedStatement();
            this.cleanConnection();
        }
    }

    @Override
    public JobActivities insert(JobActivities jobActivities) {
       try {
           this.getConnection();
           this.getPreparedStatement(INSERT);
           this.preparedStatement.setString(1,jobActivities.getUser_id());
           this.preparedStatement.setDate(2, jobActivities.getDate_created());
           this.preparedStatement.setByte(3,jobActivities.getActivity_status());
           this.preparedStatement.setDate(4, jobActivities.getActivity_deadline());
           this.preparedStatement.setString(5,jobActivities.getActivity_detail());
           int result = this.preparedStatement.executeUpdate();

           if (result == 1){
               this.statement = this.connection.createStatement();
               ResultSet rs = this.statement.executeQuery("SELECT LAST_INSERT_ID()");
               if (rs.next()){
                   int id = rs.getInt(1);
                   return findById(id);
               }
           }
           return null;
       }catch (SQLException e) {
           log.error("Error executing insert query on Activities table: {}", e.getMessage());
           return null;
       } finally {
           this.closePreparedStatement();
           this.cleanConnection();
       }
    }

    @Override
    public JobActivities update(JobActivities jobActivities) {
        try {
            this.getConnection();
            this.getPreparedStatement(UPDATE);
            this.preparedStatement.setByte(1,jobActivities.getActivity_status());
            this.preparedStatement.setDate(2,jobActivities.getDate_completed());
            this.preparedStatement.setInt(3,jobActivities.getId());
            int result = this.preparedStatement.executeUpdate();
            if(result == 1) {
                return this.findById(jobActivities.getId());
            }
            return null;
        }catch (SQLException e) {
            log.error("Error executing update query on Activities table: {}", e.getMessage());
            return null;
        } finally {
            this.closePreparedStatement();
            this.cleanConnection();
        }
    }

    @Override
    public JobActivities findById(int jobActivityId) {
        try {
            this.getConnection();
            this.getPreparedStatement(FIND_BY_ID);
            this.preparedStatement.setLong(1, jobActivityId);
            ResultSet resultSet = this.preparedStatement.executeQuery();
            JobActivities ja = null;
            if(resultSet.next()){
                ja = new JobActivities();
                ja.setId(resultSet.getInt("id"));
                ja.setUser_id(resultSet.getString("user_id"));
                ja.setActivity_status(resultSet.getByte("activity_status"));
                ja.setActivity_detail(resultSet.getString("activity_detail"));
                ja.setActivity_deadline(resultSet.getDate("activity_deadline"));
                ja.setDate_created(resultSet.getDate("date_created"));
                ja.setDate_completed(resultSet.getDate("date_completed"));
            }
            return ja;
        }catch (SQLException e) {
            log.error("Error executing findbyID query on Activities table: {}", e.getMessage());
            return null;
        } finally {
            this.closePreparedStatement();
            this.cleanConnection();
        }
    }

    public void getConnection() throws SQLException {
        this.connection = DBConfig.instance().getDBConnection();
    }

    public void cleanConnection() {
        try {
            if (this.connection != null) {
                if (!this.connection.isClosed()) {
                    this.connection.close();
                }
            }
        }catch (SQLException e){
            log.error(e.getMessage());
        }
    }

    public void getPreparedStatement(String query) throws SQLException {
        this.preparedStatement = this.connection.prepareStatement(query);
    }

    public void closePreparedStatement() {
        try{
            if(null != this.preparedStatement){
                this.preparedStatement.close();
            }
        }catch (SQLException e){

        }
    }
}
