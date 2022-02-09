/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal;

import hr.algebra.model.Role;
import hr.algebra.model.User;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

public class UserRepositoryImpl implements UserRepository {

    private static final String USERNAME = "username";
    private static final String PASSWORD = "pass";
    private static final String ROLE = "roleId";
    private static final String ID = "IdUser";

    private static final String GET_USER = "{CALL GetUser(?, ?)}";
    private static final String GET_USERS = "{CALL GetUsers()}";
    private static final String CREATE_USER = "{CALL CreateUser(?, ?)}";
    private static final String UPDATE_USER = "{CALL UpdateUser(?, ?)}";
    private static final String DELETE_USER = "{CALL DeleteUser(?)}";

    @Override
    public Optional<User> getUser(String username, String password) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_USER)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                User user = null;
                if (rs.next()) {
                    return Optional.of(user = new User(
                            rs.getInt(ID),
                            rs.getString(USERNAME),
                            rs.getString(PASSWORD),
                            Role.from(rs.getInt(ROLE)).get()
                    ));
                }
                return Optional.ofNullable(user);
            }
        }
    }

    @Override
    public void createUser(String username, String password) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_USER)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<User> getMany() throws Exception {
        List<User> user = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_USERS)) {

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    user.add(new User(
                            rs.getInt(ID),
                            rs.getString(USERNAME),
                            rs.getString(PASSWORD),
                            Role.from(rs.getInt(ROLE)).get())
                    );
                }
            }
        }
        return user;
    }

    @Override
    public void update(User user) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_USER)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());

            stmt.executeUpdate();

        } catch (SQLException e) {
            Logger.getLogger(MovieRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void delete(User id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_USER)) {
            stmt.setString(1, id.getUsername());
            stmt.executeUpdate();
        }
    }

}
