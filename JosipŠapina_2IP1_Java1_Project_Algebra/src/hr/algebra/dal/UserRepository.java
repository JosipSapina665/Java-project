/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal;

import java.util.Optional;
import hr.algebra.model.User;
import java.util.List;

/**
 *
 * @author josip
 */
public interface UserRepository extends Repository {

    Optional<User> getUser(String username, String password) throws Exception;

    void createUser(String username, String password) throws Exception;

    List<User> getMany() throws Exception;

    void update(User user) throws Exception;

    void delete(User id) throws Exception;
}
