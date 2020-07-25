package com.web.project.jobtracker.jobnotes;

import com.web.project.jobtracker.configurations.DBConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Anudish Jinturkar
 * Persistence class containing persistence logic to perform CRUD operation on job notes table of DB.
 */

@Repository
public class JobNotesPersistence implements IJobNotesPersistence {
    Logger log = LoggerFactory.getLogger(JobNotesPersistence.class);

    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;

    public static final String FIND_ALL = "SELECT noteID,noteDetails,userID FROM notes WHERE userID = ?";
    public static final String INSERT = "INSERT INTO notes(noteDetails,userID) VALUE(?,?)";
    public static final String UPDATE = "UPDATE notes SET noteDetails = ? WHERE noteID = ?";
    public static final String DELETE = "DELETE FROM notes WHERE noteID = ?";
    public static final String FIND_BY_ID = "SELECT noteID, noteDetails, userID FROM notes WHERE noteID = ?";

    @Override
    public JobNotes save(JobNotes jobNotes) {
        try {
            this.getConnection();
            this.getPreparedStatement(INSERT);
            this.preparedStatement.setString(1,jobNotes.getNoteDetails());
            this.preparedStatement.setString(2,jobNotes.getUserID());
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
        } catch (SQLException e) {
            log.error("Error executing insert query on Notes table: {}", e.getMessage());
            return null;
        } finally {
            this.closePreparedStatement();
            this.cleanConnection();
        }
    }

    @Override
    public JobNotes update(JobNotes jobNotes) {
        try{
            this.getConnection();
            this.getPreparedStatement(UPDATE);
            this.preparedStatement.setString(1,jobNotes.getNoteDetails());
            this.preparedStatement.setInt(2,jobNotes.getNoteID());
            int result = this.preparedStatement.executeUpdate();
            if(result == 1) {
                return this.findById(jobNotes.getNoteID());
            }
            return null;
        }catch (SQLException e) {
            log.error("Error executing update query on Notes table: {}", e.getMessage());
            return null;
        } finally {
            this.closePreparedStatement();
            this.cleanConnection();
        }
    }

    @Override
    public void delete(int jobNoteId) {
        try {
            this.getConnection();
            this.getPreparedStatement(DELETE);
            this.preparedStatement.setInt(1,jobNoteId);
            this.preparedStatement.executeUpdate();

        }catch (SQLException e) {
            log.error("Error executing delete query on Notes table: {}", e.getMessage());
        } finally {
            this.closePreparedStatement();
            this.cleanConnection();
        }
    }

    @Override
    public List<JobNotes> searchAll(String userID) {
        try {
            this.getConnection();
            this.getPreparedStatement(FIND_ALL);
            this.preparedStatement.setString(1,userID);
            ResultSet resultSet = this.preparedStatement.executeQuery();

            List<JobNotes> jobNotesList = new ArrayList<JobNotes>();

            while (resultSet.next()){
                JobNotes jn = new JobNotes();
                jn.setNoteID(resultSet.getInt("NoteID"));
                jn.setNoteDetails(resultSet.getString("NoteDetails"));
                jn.setUserID(resultSet.getString("UserID"));
                jobNotesList.add(jn);
            }
            return jobNotesList;

        }catch (SQLException e){
            log.error("Error executing select query on Notes table: {}", e.getMessage());
            return null;
        } finally {
            this.closePreparedStatement();
            this.cleanConnection();
        }
    }

    @Override
    public JobNotes findById(int jobNoteId) {
        try{
            this.getConnection();
            this.getPreparedStatement(FIND_BY_ID);
            this.preparedStatement.setInt(1,jobNoteId);
            ResultSet resultSet = this.preparedStatement.executeQuery();
            JobNotes jn = null;
            if(resultSet.next()){
                jn = new JobNotes();
                jn.setNoteID(resultSet.getInt(1));
                jn.setNoteDetails(resultSet.getString(2));
                jn.setUserID(resultSet.getString(3));
            }
            return jn;
        }catch (SQLException e) {
            log.error("Error executing select unique ID query on Notes table: {}", e.getMessage());
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
