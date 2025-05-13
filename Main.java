/**
 * Karli Bryant
 * April 23, 2025
 * COMP 167 Section 004
 *
 * JavaFX Application entry point. Launches the primary stage,
 * constructs a GamePane, and sets up the scene size based on
 * the default grid dimensions.
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        GamePane game = new GamePane();

        int rows = 8;
        int cols = 8;


        Scene scene = new Scene(game, rows*100, cols*100);

        stage.setTitle("Concentration Game");
        stage.setScene(scene);
        stage.show();
    }
}
