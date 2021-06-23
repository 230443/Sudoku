package pl.cp.sudoku.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * Creates connection with database from URL property.
     * @return Connection
     * @throws DaoException connection errors.
     */
    public static Connection connect() throws DaoException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            throw new DaoException(DaoException.CONNECTION_ERROR, e);
        }
        return connection;
    }

    /**
     * Get names of sudoku boards from database from URL property.
     * @return List of strings.
     */
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
