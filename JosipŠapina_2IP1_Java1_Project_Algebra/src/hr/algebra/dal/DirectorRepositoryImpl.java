/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal;

import hr.algebra.model.Director;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

public class DirectorRepositoryImpl implements DirectorRepository {

    private static final String ID = "IdDirector";
    private static final String FULLNAME = "fullName";

    private static final String SAVE_DIRECTOR = "{ CALL SaveDirector (?, ?)}";
    private static final String GET_DIRECTOR = "{ CALL GetDirector (?)}";
    private static final String GET_DIRECTORS = "{ CALL GetDirectors ()}";
    private static final String UPDATE_DIRECTOR = "{ CALL UpdateDirector (?, ?)}";
    private static final String DELETE_DIRECTOR = "{ CALL DeleteDirector (?)}";

    @Override
    public Optional<Integer> save(Director director) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SAVE_DIRECTOR)) {
            stmt.setString(1, director.getFullName());
            stmt.registerOutParameter(2, Types.INTEGER);

            stmt.executeUpdate();
            director.setId(stmt.getInt(2));

            return Optional.of(stmt.getInt(2));
        } catch (SQLException e) {
            Logger.getLogger(MovieRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            return Optional.empty();
        }

    }

    @Override
    public List<Director> getMany() throws Exception {
        List<Director> directors = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_DIRECTORS)) {

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    directors.add(new Director(
                            rs.getInt(ID),
                            rs.getString(FULLNAME))
                    );
                }
            }
        }
        return directors;
    }

    @Override
    public void update(Integer id, Director director) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_DIRECTOR)) {
            stmt.setString(1, director.getFullName());
            stmt.setInt(2, id);
            stmt.executeUpdate();

        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_DIRECTOR)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void saveBunch(List<Director> directors) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SAVE_DIRECTOR)) {
            for (Director director : directors) {
                stmt.setString(1, director.getFullName());
                stmt.registerOutParameter(2, Types.INTEGER);

                stmt.executeUpdate();
                director.setId(stmt.getInt(2));
            }

        }
    }

    @Override
    public Optional<Director> getOne(Integer id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(GET_DIRECTOR)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Director(
                            rs.getInt(ID),
                            rs.getString(FULLNAME)));
                }
            }
        }
        return Optional.empty();
    }
}
