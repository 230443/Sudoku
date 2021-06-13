package pl.cp.sudoku.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    private static String URLsqlserver =
            "jdbc:sqlserver://WINDOWSNOWY;"
                    + "database=PIZZA;"
                    + "IntegratedSecurity=True";

    private static String URLderby =
            "jdbc:sqlserver://WINDOWSNOWY;"
                    + "database=PIZZA;"
                    + "IntegratedSecurity=True";

    private static String URLMySQL =
            "jdbc:mysql://root@127.0.0.1/sudoku";

    private static String URL = URLMySQL;

    public static Connection connect() throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            throw e;
        }
        return connection;
    }
}
