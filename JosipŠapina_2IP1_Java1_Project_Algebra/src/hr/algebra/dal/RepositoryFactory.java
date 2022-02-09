/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal;

/**
 *
 * @author josip
 */
public class RepositoryFactory {

    public RepositoryFactory() {
    }

    public static <T extends Repository> T getRepoFactory(Class<T> clazz) throws Exception {
        return clazz.newInstance();
    }
}
