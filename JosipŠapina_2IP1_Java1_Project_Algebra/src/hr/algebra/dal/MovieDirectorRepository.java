/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal;

import hr.algebra.model.Director;
import hr.algebra.model.Movie;
import java.util.List;

/**
 *
 * @author josip
 */
public interface MovieDirectorRepository extends Repository {

    void saveMovieDirector(int movieId, Integer directors) throws Exception;

    void saveMovieDirectors(List<Movie> movies, List<Director> directors) throws Exception;

    List<Director> getMovieDirectors(Integer id) throws Exception;

    void deleteMovieDirector(Integer movieId, Integer directorId) throws Exception;

    void deleteAll() throws Exception;
}
