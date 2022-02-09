/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal;

import hr.algebra.model.Director;
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

public class MovieDirectorRepositoryImpl implements MovieDirectorRepository {

    private static final String DIRECTOR_ID = "IdDirector";
    private static final String FULLNAME = "fullName";

    private static final String SAVE_MOVIE_DIRECTOR = "{ CALL SaveMovieDirector (?, ?, ?) }";
    private static final String GET_MOVIE_DIRECTOR = "{ CALL GetMovieDirector (?) }";
    private static final String DELETE_MOVIE_DIRECTOR = "{ CALL DeleteMovieDirector  (?,?) }";
    private static final String DELETE_ALL = "{ CALL DeleteAll  () }";

    @Override
    public void saveMovieDirector(int movieId, Integer directors) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SAVE_MOVIE_DIRECTOR)) {
            stmt.setInt(1, directors);
            stmt.setInt(2, movieId);
            stmt.registerOutParameter(3, Types.INTEGER);
            stmt.executeLargeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(ActorRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void saveMovieDirectors(List<Movie> movies, List<Director> directors) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(SAVE_MOVIE_DIRECTOR)) {
            movies.forEach((m) -> {
                if (!m.getDirectors().isEmpty()) {
                    m.getDirectors().forEach(md -> {
                        directors.forEach(d -> {
                            if (md.equals(d)) {
                                try {
                                    stmt.setInt(1, md.getId());
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
    public void deleteMovieDirector(Integer movieId, Integer directorId) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_MOVIE_DIRECTOR)) {

            stmt.setInt(1, movieId);
            stmt.setInt(2, directorId);

            stmt.executeUpdate();
        }
    }

    @Override
    public List<Director> getMovieDirectors(Integer id) throws Exception {
        List<Director> directors = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(GET_MOVIE_DIRECTOR)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    directors.add(new Director(
                            rs.getInt(DIRECTOR_ID),
                            rs.getString(FULLNAME)));
                }
            } catch (Exception ex) {
                Logger.getLogger(MovieActorRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return directors;
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
