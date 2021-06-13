package pl.cp.sudoku.dao;

import org.apache.commons.lang3.builder.ToStringBuilder;
import pl.cp.sudoku.SudokuBoardPrototype;
import pl.cp.sudoku.model.SudokuBoard;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard> {


    private String boardName;
    private Connection connection;
    private String selectFieldsQuery = "select * from field_values where board_id=(select id from sudokuboards where boardname='"
            + boardName
            + "')";

    public JdbcSudokuBoardDao(String boardName) {
        this.boardName = boardName;

        try {
            connection = DbConnector.connect();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new NullPointerException("cannot connect to database");
        }
    }

    @Override
    public SudokuBoard read() {
        SudokuBoard board = SudokuBoardPrototype.getInstance();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectFieldsQuery);

            while (resultSet.next()) {
                int x = resultSet.getInt("x");
                int y = resultSet.getInt("y");
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

            statement.executeQuery("INSERT INTO sudokuboards(boardname) VALUES ('"
                    + boardName
                    + "')"
            );

            for (int y = 0; y < 9; y++) {
                for (int x = 0; x < 9; x++) {
                    String query = getInsertQuery(x, y, board.get(x, y), board.isFieldUnmodifiable(x, y));
                    statement.executeQuery(query);
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
        String resultQuery = "INSERT INTO sudoku.field_values VALUES ("
                + x
                + y
                + val
                + is_unmodifiable
                + ")";

        return resultQuery;
    }

}
