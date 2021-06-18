package pl.cp.sudoku.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.cp.sudoku.SudokuBoardPrototype;
import pl.cp.sudoku.model.SudokuBoard;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard> {

    private static final Logger logger = LoggerFactory.getLogger(JdbcSudokuBoardDao.class);

    private String boardName;
    private int board_id;
    private Connection connection;



    public JdbcSudokuBoardDao(String boardName) {
        this.boardName = boardName;

        try {
            connection = DbConnector.connect();
            logger.trace("Connecting...");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Cannot connect to database.",e);
            throw new NullPointerException("cannot connect to database");
        }

        board_id = getBoardId();


    }

    @Override
    public SudokuBoard read() {
        SudokuBoard board = SudokuBoardPrototype.getInstance();
        String selectFieldsQuery = "SELECT * FROM `field_values` WHERE board_id=" + board_id;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectFieldsQuery);
            logger.info("executed query: " + selectFieldsQuery);

            while (resultSet.next()) {
                int x = resultSet.getInt("x");
                int y = resultSet.getInt("y");
                logger.info("(x,y) = ("
                        + x
                        + ","
                        + y
                        + ")");
                board.set(x, y, resultSet.getInt("value"));

                if (resultSet.getBoolean("is_unmodifiable")) {
                    board.makeFieldUnmodifiable(x, y);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return board;

    }

    @Override
    public void write(SudokuBoard board) {

        try {
            Statement statement = connection.createStatement();

            if (board_id == 0) {

                statement.execute("INSERT INTO `sudokuboards` (`id`, `boardname`) VALUES (NULL, '"
                        + boardName
                        + "')"
                );

                board_id = getBoardId();

                logger.trace("new board: "
                        + boardName
                        + ", id: "
                        + board_id);
            }

            for (int y = 0; y < 9; y++) {
                for (int x = 0; x < 9; x++) {
                    String query = getInsertQuery(x, y, board.get(x, y), board.isFieldUnmodifiable(x, y));
                    logger.info(query);
                    statement.execute(query);

                    logger.info(query);
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }



    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    private String getInsertQuery(int x, int y, int val, boolean is_unmodifiable) {
        int bool = is_unmodifiable ? 1 : 0;
        String resultQuery = "INSERT INTO `field_values` (`board_id`, `x`, `y`, `value`, `is_unmodifiable`) VALUES ("
                + board_id + ","
                + x + ","
                + y + ","
                + val + ","
                + bool
                + ")";

        return resultQuery;
    }

    private int getBoardId() {

        String selectIdQuery = "SELECT id FROM `sudokuboards` WHERE boardname='" + boardName + "'";

        int id = 0;

        try {
            ResultSet result = null;
            result = executeQuery(selectIdQuery);
            result.next();
            if (result.isAfterLast()) return id;
            id = result.getInt("id");
            logger.info("board_id: " + id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return id;
    }

    private ResultSet executeQuery(String query) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);
        return result;
    }

}
