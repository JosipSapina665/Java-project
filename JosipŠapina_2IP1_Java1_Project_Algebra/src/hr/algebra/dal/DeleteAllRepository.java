/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal;

import java.sql.SQLException;

/**
 *
 * @author josip
 */
public interface DeleteAllRepository extends Repository {

    void deleteAll() throws SQLException;
}
