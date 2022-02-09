/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

public class DeleteAllRepositoryImpl implements DeleteAllRepository {

    private static final String DELETE_ALL = "{CALL DeleteAll()}";

    @Override
    public void deleteAll() throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_ALL)) {
            stmt.executeUpdate();
        }
    }

}
