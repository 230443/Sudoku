package pl.cp.sudoku.dao;

import java.sql.*;
import java.util.*;

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

    public static List<String> getSudokuBoardNames() {
        List<String> result = new ArrayList<>();
        String sql = "SELECT boardname FROM `sudokuboards`";
        try (
                Connection c = connect();
                Statement statement = c.createStatement();
                ResultSet rs = statement.executeQuery(sql);
        ) {
            while (rs.next()) {
                result.add(rs.getString(1));
            }

        } catch (SQLException e) {
            throw new DaoException(DaoException.CONNECTION_ERROR, e);
        }
        return result;
    }
}
