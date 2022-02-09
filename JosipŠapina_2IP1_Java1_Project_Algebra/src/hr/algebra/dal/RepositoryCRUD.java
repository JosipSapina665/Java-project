/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author josip
 */
public interface RepositoryCRUD<T, E> extends Repository {

    Optional<Integer> save(T object);

    void saveBunch(List<T> object) throws Exception;

    List<T> getMany() throws Exception;

    Optional<T> getOne(E id) throws Exception;

    void update(E id, T data) throws Exception;

    void delete(E id) throws Exception;

}
