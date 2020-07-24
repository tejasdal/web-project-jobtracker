package com.web.project.jobtracker.jobboard;

import com.web.project.jobtracker.configurations.DBConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tejas Patel
 * Persistence class containing persistence logic to perform CRUD operation on jobboard table of DB.
 */
@Repository
public class JobBoardPersistence implements IJobBoardPersistence {

    Logger log = LoggerFactory.getLogger(JobBoardPersistence.class);

    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;

    private static final String FIND_BY_ID = "SELECT id, user_id, name, created_date, updated_date FROM jobboard where id = ?";
    private static final String FIND_BY_USER_ID = "SELECT id, user_id, name, created_date, updated_date FROM jobboard where user_id = ?";
    private static final String FIND_BY_NAME_USER_ID_CREATED_DATE = "SELECT id, user_id, name, created_date, updated_date FROM jobboard " +
            " where name = ? AND user_id = ? AND created_date = ?";
    private static final String SAVE = "INSERT INTO jobboard(user_id, name, created_date, updated_date) VALUE (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE jobboard SET name = ?, updated_date = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM jobboard WHERE id = ?";

    @Override
    public JobBoard findById(Long jobBoardId) {
        try {
            this.getConnection();
            this.getPreparedStatement(FIND_BY_ID);
            this.preparedStatement.setLong(1, jobBoardId);
            ResultSet resultSet = this.preparedStatement.executeQuery();
            JobBoard jobBoard = null;
            if(resultSet.next()){
                jobBoard = new JobBoard();
                jobBoard.setId(resultSet.getLong(1));
                jobBoard.setUserId(resultSet.getString(2));
                jobBoard.setName(resultSet.getString(3));
                jobBoard.setCreatedAt(resultSet.getTimestamp(4));
                jobBoard.setUpdatedAt(resultSet.getTimestamp(5));
            }
            return jobBoard;
        } catch (SQLException e) {
            log.error("Error executing select query on JobBoard table: {}", e.getMessage());
            return null;
        } finally {
            this.closePreparedStatement();
            this.cleanConnection();
        }
    }

    public JobBoard findByNameAndUserIdAndCreatedDate(JobBoard jobBoard) {
        try {
            this.getConnection();
            this.getPreparedStatement(FIND_BY_NAME_USER_ID_CREATED_DATE);
            this.preparedStatement.setString(1, jobBoard.getName());
            this.preparedStatement.setString(2, jobBoard.getUserId());
            this.preparedStatement.setTimestamp(3, jobBoard.getCreatedAt());
            ResultSet resultSet = this.preparedStatement.executeQuery();
            JobBoard dbJobBoard = null;
            if(resultSet.next()){
                dbJobBoard = new JobBoard();
                dbJobBoard.setId(resultSet.getLong(1));
                dbJobBoard.setUserId(resultSet.getString(2));
                dbJobBoard.setName(resultSet.getString(3));
                dbJobBoard.setCreatedAt(resultSet.getTimestamp(4));
                dbJobBoard.setUpdatedAt(resultSet.getTimestamp(5));
            }
            return dbJobBoard;
        } catch (SQLException e) {
            log.error("Error executing select query on JobBoard table: {}", e.getMessage());
            return null;
        } finally {
            this.closePreparedStatement();
            this.cleanConnection();
        }
    }

    @Override
    public JobBoard save(JobBoard jobBoard) {
        try {
            this.getConnection();
            this.getPreparedStatement(SAVE);
            this.preparedStatement.setString(1, jobBoard.getUserId());
            this.preparedStatement.setString(2, jobBoard.getName());
            this.preparedStatement.setTimestamp(3, jobBoard.getCreatedAt());
            this.preparedStatement.setTimestamp(4, jobBoard.getUpdatedAt());
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
            log.error("Error executing insert query on JobBoard table: {}", e.getMessage());
            return null;
        } finally {
            this.closePreparedStatement();
            this.cleanConnection();
        }
    }

    @Override
    public JobBoard update(JobBoard jobBoard) {
        try {
            this.getConnection();
            this.getPreparedStatement(UPDATE);
            this.preparedStatement.setString(1, jobBoard.getName());
            this.preparedStatement.setTimestamp(2, jobBoard.getUpdatedAt());
            this.preparedStatement.setLong(3, jobBoard.getId());
            int result = this.preparedStatement.executeUpdate();
            if (result == 1){
                return findById(jobBoard.getId());
            }
            return null;
        } catch (SQLException e) {
            log.error("Error executing update query on JobBoard table: {}", e.getMessage());
            return null;
        } finally {
            this.closePreparedStatement();
            this.cleanConnection();
        }
    }

    @Override
    public void delete(Long jobBoardId) {
        try {
            this.getConnection();
            this.getPreparedStatement(DELETE);
            this.preparedStatement.setLong(1, jobBoardId);
            int result = this.preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error executing delete query on JobBoard table: {}", e.getMessage());
        } finally {
            this.closePreparedStatement();
            this.cleanConnection();
        }
    }

    @Override
    public List<JobBoard> getJobBoardByUserId(String userId) {
        try {
            this.getConnection();
            this.getPreparedStatement(FIND_BY_USER_ID);
            this.preparedStatement.setString(1, userId);
            ResultSet resultSet = this.preparedStatement.executeQuery();
            List<JobBoard> jobBoards = new ArrayList<>();
            while(resultSet.next()){
                JobBoard jobBoard = new JobBoard();
                jobBoard.setId(resultSet.getLong(1));
                jobBoard.setUserId(resultSet.getString(2));
                jobBoard.setName(resultSet.getString(3));
                jobBoard.setCreatedAt(resultSet.getTimestamp(4));
                jobBoard.setUpdatedAt(resultSet.getTimestamp(5));
                jobBoards.add(jobBoard);
            }
            return jobBoards;
        } catch (SQLException e) {
            log.error("Error executing select query on JobBoard table: {}", e.getMessage());
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
