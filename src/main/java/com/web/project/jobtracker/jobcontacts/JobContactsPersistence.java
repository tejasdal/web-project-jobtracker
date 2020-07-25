package com.web.project.jobtracker.jobcontacts;

import com.web.project.jobtracker.configurations.DBConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Anudish Jinturkar
 * Persistence class containing persistence logic to perform CRUD operation on job contacts table of DB.
 */

@Repository
public class JobContactsPersistence implements IJobContactsPersistence {

    Logger log = LoggerFactory.getLogger(JobContactsPersistence.class);

    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;

    public static final String FIND_ALL = "SELECT ContactID, ContactName, ContactEmail, Company, JobPosition, UserID FROM Contact WHERE UserID = ?";
    public static final String INSERT = "INSERT INTO Contact(ContactName, ContactEmail, Company, JobPosition, UserID) VALUE(?, ?, ?, ?, ?)";
    public static final String UPDATE = "UPDATE Contact SET ContactName = ?, ContactEmail = ?, Company = ?, JobPosition = ? WHERE ContactID = ?";
    public static final String DELETE = "DELETE FROM Contact WHERE ContactID = ?";
    public static final String FIND_BY_ID = "SELECT ContactID, ContactName, ContactEmail, Company, JobPosition, UserID FROM Contact WHERE ContactID = ?";

    @Override
    public JobContacts save(JobContacts jobContacts) {
        try {
            this.getConnection();
            this.getPreparedStatement(INSERT);
            this.preparedStatement.setString(1, jobContacts.getContactName());
            this.preparedStatement.setString(2, jobContacts.getContactEmail());
            this.preparedStatement.setString(3, jobContacts.getCompany());
            this.preparedStatement.setString(4, jobContacts.getJobPosition());
            this.preparedStatement.setString(5,jobContacts.getUserID());
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
        }catch (SQLException e) {
            log.error("Error executing insert query on Contact table: {}", e.getMessage());
            return null;
        } finally {
            this.closePreparedStatement();
            this.cleanConnection();
        }
    }

    @Override
    public JobContacts update(JobContacts jobContacts) {
        try {
            this.getConnection();
            this.getPreparedStatement(UPDATE);
            this.preparedStatement.setString(1,jobContacts.getContactName());
            this.preparedStatement.setString(2,jobContacts.getContactEmail());
            this.preparedStatement.setString(3,jobContacts.getCompany());
            this.preparedStatement.setString(4,jobContacts.getJobPosition());
            this.preparedStatement.setLong(5,jobContacts.getContactID());
            int result = this.preparedStatement.executeUpdate();
            if(result == 1) {
                return this.findById(jobContacts.getContactID());
            }
            return null;
            }catch (SQLException e) {
            log.error("Error executing update query on Contact table: {}", e.getMessage());
            return null;
        } finally {
            this.closePreparedStatement();
            this.cleanConnection();
        }
    }

    @Override
    public void delete(Long jobContactId) {
        try {
            this.getConnection();
            this.getPreparedStatement(DELETE);
            this.preparedStatement.setLong(1, jobContactId);
            this.preparedStatement.executeUpdate();

        }catch (SQLException e) {
            log.error("Error executing delete query on Contact table: {}", e.getMessage());
         } finally {
            this.closePreparedStatement();
            this.cleanConnection();
        }
    }

    @Override
    public List<JobContacts> searchAll(String userID) {
     try {
         this.getConnection();
         this.getPreparedStatement(FIND_ALL);
         this.preparedStatement.setString(1,userID);
         ResultSet resultSet = this.preparedStatement.executeQuery();
         List<JobContacts> jobContacts = new ArrayList<JobContacts>();
         while (resultSet.next()){
             JobContacts jc = new JobContacts();
             jc.setContactID(resultSet.getLong("ContactID"));
             jc.setContactName(resultSet.getString("ContactName"));
             jc.setContactEmail(resultSet.getString("ContactEmail"));
             jc.setCompany(resultSet.getString("Company"));
             jc.setJobPosition(resultSet.getString("JobPosition"));
             jc.setUserID(resultSet.getString("UserID"));
             jobContacts.add(jc);
         }
         return jobContacts;
     }catch (SQLException e){
         log.error("Error executing select query on Contact table: {}", e.getMessage());
         return null;
     } finally {
         this.closePreparedStatement();
         this.cleanConnection();
     }
    }

    @Override
    public JobContacts findById(Long jobContactsId){
        try {
            this.getConnection();
            this.getPreparedStatement(FIND_BY_ID);
            this.preparedStatement.setLong(1, jobContactsId);
            ResultSet resultSet = this.preparedStatement.executeQuery();
            JobContacts jc = null;
            if(resultSet.next()){
                jc = new JobContacts();
                jc.setContactID(resultSet.getLong(1));
                jc.setContactName(resultSet.getString(2));
                jc.setContactEmail(resultSet.getString(3));
                jc.setCompany(resultSet.getString(4));
                jc.setJobPosition(resultSet.getString(5));
                jc.setUserID(resultSet.getString(6));
            }
            return jc;
        } catch (SQLException e) {
            log.error("Error executing select unique ID query on Contact table: {}", e.getMessage());
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
