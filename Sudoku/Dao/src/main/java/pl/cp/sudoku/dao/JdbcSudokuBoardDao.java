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

    private final String boardName;
    private int board_id;
    private final Connection connection;

    /**
     * Initialize DAO component.
     * @param boardName name of the board in the database.
     */
    public JdbcSudokuBoardDao(String boardName) {
        this.boardName = boardName;

        try {
            connection = DbConnector.connect();
            logger.trace("Connecting...");
        } catch (DaoException e) {
            DaoException daoException = new DaoException(DaoException.CONNECTION_ERROR, e);
            logger.error(daoException.getLocalizedMessage(), e);
            throw new DaoException(daoException.getLocalizedMessage(), e);
        }

        board_id = getBoardId();


    }

    @Override
    public SudokuBoard read() throws DaoException {
        SudokuBoard board = SudokuBoardPrototype.getInstance();

        if (board_id == 0) {
            DaoException daoException = new DaoException(DaoException.BOARD_NOT_FOUND);
            logger.warn(daoException.getLocalizedMessage());
            throw daoException;
            //return null;
        }

        String selectFieldsQuery = "SELECT * FROM `field_values` WHERE board_id=" + board_id;

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectFieldsQuery)) {

            logger.info("executed query: " + selectFieldsQuery);

            int rowCount = 0;
            while (resultSet.next()) {
                int x = resultSet.getInt("x");
                int y = resultSet.getInt("y");
                //logger.info("(x,y) = (" + x + "," + y + ")");
                //logger.info("rows number: " + (resultSet.getRow()));

                board.set(x, y, resultSet.getInt("value"));

                if (resultSet.getBoolean("is_unmodifiable")) {
                    board.makeFieldUnmodifiable(x, y);
                }
                rowCount++;
            }

            if (rowCount != 81) {
                logger.error("rows number: " + (rowCount));
                throw new DaoException(DaoException.BOARD_CORRUPTED);
            }
        } catch (SQLException throwables) {
            throw new DaoException(DaoException.BOARD_CORRUPTED, throwables);
        }

        return board;

    }

    @Override
    public void write(SudokuBoard board) {

        try (Statement statement = connection.createStatement()) {

            connection.setAutoCommit(false);

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

                for (int y = 0; y < 9; y++) {
                    for (int x = 0; x < 9; x++) {
                        String query = getInsertQuery(
                                x, y, board.get(x, y), board.isFieldUnmodifiable(x, y));
                        logger.info(query);
                        statement.execute(query);

                        logger.info(query);
                    }
                }
            } else {
                for (int y = 0; y < 9; y++) {
                    for (int x = 0; x < 9; x++) {
                        String query = getUpdateQuery(
                                x, y, board.get(x, y), board.isFieldUnmodifiable(x, y));
                        logger.info(query);
                        statement.execute(query);

                        logger.info(query);
                    }
                }
            }

            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new DaoException(DaoException.BOARD_CORRUPTED, e);
        }


    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    private String getInsertQuery(int x, int y, int val, boolean is_unmodifiable) {
        int bool = is_unmodifiable ? 1 : 0;
        String resultQuery = "INSERT INTO `field_values` "
                + "(`board_id`, `x`, `y`, `value`, `is_unmodifiable`)"
                + " VALUES ("
                + board_id + ","
                + x + ","
                + y + ","
                + val + ","
                + bool
                + ")";

        return resultQuery;
    }

    private String getUpdateQuery(int x, int y, int val, boolean is_unmodifiable) {
        int bool = is_unmodifiable ? 1 : 0;
        String resultQuery = "UPDATE `field_values` SET "
                + "`value`='"
                + val
                + "', `is_unmodifiable`='"
                + bool
                + "' WHERE `board_id`='"
                + board_id
                + "' AND `x`='"
                + x
                + "' AND `y`='"
                + y
                + "'";

        return resultQuery;
    }

    private int getBoardId() {

        String selectIdQuery = "SELECT id FROM `sudokuboards` WHERE boardname='" + boardName + "'";

        int id = 0;

        try (ResultSet result = executeQuery(selectIdQuery)) {
            while (result.next()) {
                id = result.getInt("id");
                logger.info("board_id: " + id);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return id;
    }

    private ResultSet executeQuery(String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

}
