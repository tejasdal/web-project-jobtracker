package com.web.project.jobtracker.usermanagement;

import com.web.project.jobtracker.configurations.DBConfig;
import org.springframework.stereotype.Repository;

import java.sql.*;

/**
 * @author Parth Bagaria
 *
 * Banner ID: B00839783
 */
@Repository
public class UserManagerPersistence implements IUserManagerPersistence{

    private Connection connection;
    private PreparedStatement preparedStatement;

    @Override
    public User getUser(String email) {
        User user = new User();
        try {
            this.getConnection();
            this.getPreparedStatement("Select * from user_profile where email=?");
            this.preparedStatement.setString(1, email);
            ResultSet rs = this.preparedStatement.executeQuery();
            while(rs.next()){
                System.out.println("resultset : ");
                user.setEmail(rs.getString(1));
                user.setFirstName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setPhone(rs.getString(4));
                user.setLinkedInURL(rs.getString(5));
                user.setPassword(rs.getString(6));
            }
            return user;
        }catch (SQLException e) {
            return null;
        } finally {
            this.closePreparedStatement();
            this.cleanConnection();
        }
    }

    @Override
    public void updateUser(User user) {
        try {
            this.getConnection();
            this.getPreparedStatement("UPDATE user_profile SET email=?, firstname=?, lastname=?, phone=?, url=?,password=? WHERE email=?");
            this.preparedStatement.setString(1, user.getEmail());
            this.preparedStatement.setString(2, user.getFirstName());
            this.preparedStatement.setString(3, user.getLastName());
            this.preparedStatement.setString(4, user.getPhone());
            this.preparedStatement.setString(5, user.getLinkedInURL());
            this.preparedStatement.setString(6, user.getPassword());
            this.preparedStatement.setString(7, user.getEmail());
            this.preparedStatement.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            this.closePreparedStatement();
            this.cleanConnection();
        }
    }

    @Override
    public void newUser(NewUser user) {
        try {
            this.getConnection();
            this.getPreparedStatement("INSERT into user values (?,?)");
            this.preparedStatement.setString(1, user.getEmail());
            this.preparedStatement.setString(2, user.getPassword());
            this.preparedStatement.executeUpdate();
            this.getPreparedStatement("INSERT into user_profile values (?,?,?,?,?,?)");
            this.preparedStatement.setString(1, user.getEmail());
            this.preparedStatement.setString(2, "");
            this.preparedStatement.setString(3, "");
            this.preparedStatement.setString(4, "");
            this.preparedStatement.setString(5, "");
            this.preparedStatement.setString(6, user.getPassword());
            this.preparedStatement.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
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
