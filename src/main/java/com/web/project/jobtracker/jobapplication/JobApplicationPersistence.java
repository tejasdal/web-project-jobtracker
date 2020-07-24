package com.web.project.jobtracker.jobapplication;

import com.web.project.jobtracker.configurations.DBConfig;
import com.web.project.jobtracker.jobboard.JobBoard;
import com.web.project.jobtracker.jobboard.JobBoardPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tejas Patel
 * Persistence class containing persistence logic to perform CRUD operation on jobapplication table of DB.
 */
@Repository
public class JobApplicationPersistence implements IJobApplicationPersistence {

    Logger log = LoggerFactory.getLogger(JobApplicationPersistence.class);

    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;

    public static final String FIND_BY_ID = "SELECT id, role, company, user_id, job_board_id, status_id, created_date, updated_date FROM jobapplication where id = ?";
    public static final String FIND_BY_ROLE_COMPANY_STATUS_CREATEDAT = "SELECT id, role, company, user_id, job_board_id, status_id, created_date, updated_date FROM jobapplication where role = ?, company = ?, job_board_id = ?, status_id = ?, created_date = ?";
    public static final String FIND_BY_JOB_BOARD_ID = "SELECT id, role, company, user_id, job_board_id, status_id, created_date, updated_date FROM jobapplication where job_board_id = ?";
    public static final String INSERT = "INSERT INTO jobapplication(role, company, user_id, job_board_id, status_id, created_date, updated_date) VALUE(?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE = "UPDATE jobapplication SET role = ?, company = ?, status_id = ?, updated_date = ? WHERE id = ?";
    public static final String DELETE = "DELETE FROM jobapplication WHERE id = ?";

    @Override
    public JobApplication findById(Long jobApplicationId) {
        try {
            this.getConnection();
            this.getPreparedStatement(FIND_BY_ID);
            this.preparedStatement.setLong(1, jobApplicationId);
            ResultSet resultSet = this.preparedStatement.executeQuery();
            JobApplication jobApplication = null;
            if(resultSet.next()){
                jobApplication = new JobApplication();
                jobApplication.setId(resultSet.getLong(1));
                jobApplication.setJobRole(resultSet.getString(2));
                jobApplication.setCompany(resultSet.getString(3));
                jobApplication.setUserId(resultSet.getString(4));
                jobApplication.setJobBoardId(resultSet.getLong(5));
                jobApplication.setStatus(JobApplicationStatus.valueOf(resultSet.getInt(6)));
                jobApplication.setCreatedAt(resultSet.getTimestamp(7));
                jobApplication.setUpdatedAt(resultSet.getTimestamp(8));
            }
            return jobApplication;
        } catch (SQLException e) {
            log.error("Error executing select query on JobApplication table: {}", e.getMessage());
            return null;
        } finally {
            this.closePreparedStatement();
            this.cleanConnection();
        }
    }

    public JobApplication findByRoleAndCompanyAndStatusAndCreatedAt(JobApplication jobApplication) {
        try {
            this.getConnection();
            this.getPreparedStatement(FIND_BY_ROLE_COMPANY_STATUS_CREATEDAT);
            this.preparedStatement.setString(1, jobApplication.getJobRole());
            this.preparedStatement.setString(2, jobApplication.getCompany());
            this.preparedStatement.setLong(3, jobApplication.getJobBoardId());
            this.preparedStatement.setInt(4, jobApplication.getStatus().getValue());
            this.preparedStatement.setTimestamp(5, jobApplication.getCreatedAt());
            ResultSet resultSet = this.preparedStatement.executeQuery();
            JobApplication dbJobApplication = null;
            if(resultSet.next()){
                dbJobApplication = new JobApplication();
                dbJobApplication.setId(resultSet.getLong(1));
                dbJobApplication.setJobRole(resultSet.getString(2));
                dbJobApplication.setCompany(resultSet.getString(3));
                jobApplication.setUserId(resultSet.getString(4));
                jobApplication.setJobBoardId(resultSet.getLong(5));
                jobApplication.setStatus(JobApplicationStatus.valueOf(resultSet.getInt(6)));
                jobApplication.setCreatedAt(resultSet.getTimestamp(7));
                jobApplication.setUpdatedAt(resultSet.getTimestamp(8));
            }
            return dbJobApplication;
        } catch (SQLException e) {
            log.error("Error executing select query on JobApplication table: {}", e.getMessage());
            return null;
        } finally {
            this.closePreparedStatement();
            this.cleanConnection();
        }
    }

    @Override
    public JobApplication save(JobApplication jobApplication) {
        try {
            this.getConnection();
            this.getPreparedStatement(INSERT);
            this.preparedStatement.setString(1, jobApplication.getJobRole());
            this.preparedStatement.setString(2, jobApplication.getCompany());
            this.preparedStatement.setString(3, jobApplication.getUserId());
            this.preparedStatement.setLong(4, jobApplication.getJobBoardId());
            this.preparedStatement.setInt(5, jobApplication.getStatus().getValue());
            this.preparedStatement.setTimestamp(6, jobApplication.getCreatedAt());
            this.preparedStatement.setTimestamp(7, jobApplication.getUpdatedAt());
            int result = this.preparedStatement.executeUpdate();
            if (result == 1){
                this.statement = this.connection.createStatement();
                ResultSet rs = this.statement.executeQuery("SELECT LAST_INSERT_ID()");
                if (rs.next()){
                    Long id = rs.getLong(1);
                    return findById(id);
                }
            }
            return null;
        } catch (SQLException e) {
            log.error("Error executing insert query on JobApplication table: {}", e.getMessage());
            return null;
        } finally {
            this.closePreparedStatement();
            this.cleanConnection();
        }
    }

    @Override
    public JobApplication update(JobApplication jobApplication) {
        try {
            this.getConnection();
            this.getPreparedStatement(UPDATE);
            this.preparedStatement.setString(1, jobApplication.getJobRole());
            this.preparedStatement.setString(2, jobApplication.getCompany());
            this.preparedStatement.setInt(3, jobApplication.getStatus().getValue());
            this.preparedStatement.setTimestamp(4, jobApplication.getUpdatedAt());
            this.preparedStatement.setLong(5, jobApplication.getId());
            int result = this.preparedStatement.executeUpdate();
            if(result == 1){
                return this.findById(jobApplication.getId());
            }
            return null;
        } catch (SQLException e) {
            log.error("Error executing update query on JobApplication table: {}", e.getMessage());
            return null;
        } finally {
            this.closePreparedStatement();
            this.cleanConnection();
        }
    }

    @Override
    public void delete(Long jobApplicationId) {
        try {
            this.getConnection();
            this.getPreparedStatement(DELETE);
            this.preparedStatement.setLong(1, jobApplicationId);
            int result = this.preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error executing update query on JobApplication table: {}", e.getMessage());
        } finally {
            this.closePreparedStatement();
            this.cleanConnection();
        }
    }

    @Override
    public List<JobApplication> findByJobBoardId(Long jobBoardId) {
        try {
            this.getConnection();
            this.getPreparedStatement(FIND_BY_JOB_BOARD_ID);
            this.preparedStatement.setLong(1, jobBoardId);
            ResultSet resultSet = this.preparedStatement.executeQuery();
            List<JobApplication> jobApplications = new ArrayList<>();
            while(resultSet.next()){
                JobApplication jobApplication = new JobApplication();
                jobApplication.setId(resultSet.getLong(1));
                jobApplication.setJobRole(resultSet.getString(2));
                jobApplication.setCompany(resultSet.getString(3));
                jobApplication.setUserId(resultSet.getString(4));
                jobApplication.setJobBoardId(resultSet.getLong(5));
                jobApplication.setStatus(JobApplicationStatus.valueOf(resultSet.getInt(6)));
                jobApplication.setCreatedAt(resultSet.getTimestamp(7));
                jobApplication.setUpdatedAt(resultSet.getTimestamp(8));
                jobApplications.add(jobApplication);
            }
            return jobApplications;
        } catch (SQLException e) {
            log.error("Error executing select query on JobApplication table: {}", e.getMessage());
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
