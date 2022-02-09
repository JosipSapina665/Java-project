/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import hr.algebra.model.Movie;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MovieRepositoryImpl implements MovieRepository {

    private static final String ID_MOVIE = "IdMovie";
    private static final String TITLE = "title";
    private static final String PUB_DATE = "pubDate";
    private static final String DESCRIPTION = "descr";
    private static final String DURATION = "duration";
    private static final String GENRE = "genre";
    private static final String IMAGE_PATH = "imagePath";

    private static final String SAVE_MOVIE = "{CALL SaveMovie (?, ?, ?, ?, ?, ?, ?)}";
    private static final String GET_MOVIES = "{CALL GetMovies()}";
    private static final String GET_MOVIE = "{CALL GetMovie(?)}";
    private static final String UPDATE_MOVIE = "{CALL UpdateMovie (?, ?, ?, ?, ?, ?, ?)}";
    private static final String DELETE_MOVIE = "{CALL DeleteMovie (?)}";

    @Override
    public Optional<Integer> save(Movie movie) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SAVE_MOVIE)) {
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getPubDate().format(Movie.DATE_FORMAT));
            stmt.setString(3, movie.getDescription());
            stmt.setInt(4, movie.getDuration());
            stmt.setString(5, movie.getGenre());
            stmt.setString(6, movie.getImagePath());
            stmt.registerOutParameter(7, Types.INTEGER);
            stmt.executeUpdate();
            movie.setId(stmt.getInt(7));
            return Optional.of(stmt.getInt(7));
        } catch (Exception e) {
            Logger.getLogger(MovieRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
            return Optional.empty();
        }
    }

    @Override
    public void saveBunch(List<Movie> movies) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SAVE_MOVIE)) {
            for (Movie movie : movies) {
                stmt.setString(1, movie.getTitle());
                stmt.setString(2, movie.getPubDate().format(Movie.DATE_FORMAT));
                stmt.setString(3, movie.getDescription());
                stmt.setInt(4, movie.getDuration());
                stmt.setString(5, movie.getGenre());
                stmt.setString(6, movie.getImagePath());
                stmt.registerOutParameter(7, Types.INTEGER);
                stmt.executeUpdate();
            }

        } catch (SQLException ex) {
            Logger.getLogger(MovieRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Movie> getMany() throws Exception {
        List<Movie> movies = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_MOVIES);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Movie movie = new Movie(
                        rs.getInt(ID_MOVIE),
                        rs.getString(TITLE),
                        rs.getString(DESCRIPTION),
                        rs.getInt(DURATION),
                        rs.getString(GENRE),
                        rs.getString(IMAGE_PATH),
                        LocalDateTime.parse(rs.getString(PUB_DATE), Movie.DATE_FORMAT));
                movies.add(movie);
            }
        }

        return movies;
    }

    @Override
    public void update(Integer id, Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_MOVIE)) {
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getPubDate().format(Movie.DATE_FORMAT));
            stmt.setString(3, movie.getDescription());
            stmt.setInt(4, movie.getDuration());
            stmt.setString(5, movie.getGenre());
            stmt.setString(6, movie.getImagePath());
            stmt.setInt(7, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_MOVIE)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Movie> getOne(Integer id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(GET_MOVIE)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                Movie movie = null;
                if (rs.next()) {
                    return Optional.of(new Movie(
                            rs.getInt(ID_MOVIE),
                            rs.getString(TITLE),
                            rs.getString(DESCRIPTION),
                            rs.getInt(DURATION),
                            rs.getString(GENRE),
                            rs.getString(IMAGE_PATH),
                            LocalDateTime.parse(rs.getString(PUB_DATE), Movie.DATE_FORMAT)));
                }
                return Optional.ofNullable(movie);
            }

        }

    }
}
