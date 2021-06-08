package pl.cp;

import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import pl.cp.sudoku.Difficulty;



/**
 * JavaFX App.
 */
public class App extends Application {

    private Difficulty difficulty;

    @Override
    public void start(Stage stage) throws IOException {

        MainView view = new MainView();

        view.initialize(stage);
    }

    public static void main(String[] args) {
        launch();
    }

}