/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal;

import java.util.List;
import hr.algebra.model.Actor;
import hr.algebra.model.Movie;

/**
 *
 * @author josip
 */
public interface MovieActorRepository extends Repository {

    void saveMovieActor(int movieId, Integer actor) throws Exception;

    void saveMovieActors(List<Movie> movies, List<Actor> actors) throws Exception;

    List<Actor> getMovieActors(Integer id) throws Exception;

    void deleteMovieActor(Integer movieId, Integer actorId) throws Exception;

    void deleteAll() throws Exception;

}
