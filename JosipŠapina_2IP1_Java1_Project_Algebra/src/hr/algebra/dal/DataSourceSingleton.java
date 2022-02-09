/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal;

import javax.sql.DataSource;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

/**
 *
 * @author josip
 */
class DataSourceSingleton {

    private static final String SERVER_NAME = "127.0.0.1";
    private static final String DATABASE_NAME = "JosipSapina";
    private static final String USER = "SA";
    private static final String PASSWORD = "Hrdim022";

    public DataSourceSingleton() {
    }

    private static DataSource instance;

    public static DataSource getInstance() {
        if (instance == null) {
            instance = createInstance();
        }
        return instance;
    }

    private static DataSource createInstance() {
        SQLServerDataSource dataSource = new SQLServerDataSource();
        dataSource.setServerName(SERVER_NAME);
        dataSource.setDatabaseName(DATABASE_NAME);
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }

}
