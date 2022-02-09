/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal;

import hr.algebra.model.Actor;
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

public class ActorRepositoryImpl implements ActorRepository {

    private static final String ID = "IdActor";
    private static final String FULLNAME = "fullName";

    private static final String SAVE_ACTOR = "{ CALL SaveActor (?, ?)}";
    private static final String GET_ACTORS = "{ CALL GetActors ()}";
    private static final String GET_ACTOR = "{ CALL GetActor (?)}";
    private static final String UPDATE_ACTOR = "{ CALL UpdateActor (?, ?)}";
    private static final String DELETE_ACTOR = "{ CALL DeleteActor (?)}";

    @Override
    public Optional<Integer> save(Actor actor) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SAVE_ACTOR)) {
            stmt.setString(1, actor.getFullName());
            stmt.registerOutParameter(2, Types.INTEGER);

            stmt.executeUpdate();
            actor.setId(stmt.getInt(2));
            
            return Optional.of(stmt.getInt(2));
        } catch (SQLException e) {
            Logger.getLogger(MovieRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            return Optional.empty();
        }
    }

    @Override
    public List<Actor> getMany() throws Exception {
        List<Actor> actors = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_ACTORS)) {

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    actors.add(new Actor(
                            rs.getInt(ID),
                            rs.getString(FULLNAME))
                    );
                }
            }
        }
        return actors;
    }

    @Override
    public void update(Integer id, Actor actor) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_ACTOR)) {
            stmt.setString(1, actor.getFullName());
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_ACTOR)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void saveBunch(List<Actor> actors) throws Exception {
        if (actors == null) {
            return;
        }
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SAVE_ACTOR)) {
            for (Actor actor : actors) {
                stmt.setString(1, actor.getFullName());
                stmt.registerOutParameter(2, Types.INTEGER);

                stmt.executeUpdate();
                actor.setId(stmt.getInt(2));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ActorRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Optional<Actor> getOne(Integer id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(GET_ACTOR)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Actor(
                            rs.getInt(ID),
                            rs.getString(FULLNAME)));
                }
            }
        }
        return Optional.empty();
    }
}
