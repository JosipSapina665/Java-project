/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal;

import hr.algebra.model.Actor;
import hr.algebra.model.Movie;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

public class MovieActorRepositoryImpl implements MovieActorRepository {

    private static final String ACTOR_ID = "IdActor";
    private static final String FULLNAME = "fullName";

    private static final String SAVE_MOVIE_ACTOR = "{ CALL SaveMovieActor (?, ?, ?) }";
    private static final String GET_MOVIE_ACTOR = "{ CALL GetMovieActor (?) }";
    private static final String DELETE_MOVIE_ACTOR = "{ CALL DeleteMovieActor  (?, ?) }";
    private static final String DELETE_ALL = "{ CALL DeleteAll  () }";

    @Override
    public void saveMovieActor(int movieId, Integer actorId) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SAVE_MOVIE_ACTOR)) {

            stmt.setInt(1, actorId);
            stmt.setInt(2, movieId);
            stmt.registerOutParameter(3, Types.INTEGER);
            stmt.executeLargeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(ActorRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void saveMovieActors(List<Movie> movies, List<Actor> actors) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(SAVE_MOVIE_ACTOR)) {
            movies.forEach((m) -> {
                if (!m.getActors().isEmpty()) {
                    m.getActors().forEach(ma -> {
                        actors.forEach(a -> {
                            if (ma.equals(a)) {
                                try {
                                    stmt.setInt(1, a.getId());
                                    stmt.setInt(2, m.getId());
                                    stmt.executeUpdate();
                                } catch (Exception ex) {
                                    Logger.getLogger(MovieActorRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                    });
                }
            });

        }
    }

    @Override
    public void deleteMovieActor(Integer movieId, Integer actorId) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_MOVIE_ACTOR)) {

            stmt.setInt(1, movieId);
            stmt.setInt(2, actorId);

            stmt.executeUpdate();
        }
    }

    @Override
    public List<Actor> getMovieActors(Integer id) throws Exception {
        List<Actor> actors = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(GET_MOVIE_ACTOR)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    actors.add(new Actor(
                            rs.getInt(ACTOR_ID),
                            rs.getString(FULLNAME)));
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MovieActorRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return actors;
    }

    @Override
    public void deleteAll() throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(DELETE_ALL)) {
            stmt.executeUpdate();
        }
    }

}
