/**
 * Karli Bryant
 * April 23, 2025
 * COMP 167 Section 004
 *
 * Class GameTimerPane extends Pane to display a running game clock for the
 * Concentration game. Uses an AnimationTimer to update every
 * tenth of a second, and provides start, stop, reset methods
 * plus getters for minutes, seconds, and tenths.
 */
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

public class GameTimerPane extends Pane {
    private AnimationTimer gameTimer;

    // Records when the timer was started (nanoseconds) & time since start
    private long startTime;
    private long timeElapsed;

    // Displays formatted time
    private Text timerText;


    // CONSTRUCTOR
    public GameTimerPane() {
        timerText = new Text("Time: 00:00.0");
        timerText.setFont(Font.font("Courier New", 36));

        this.getChildren().add(timerText); // adds to pane

        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                timeElapsed = now - startTime;
                long timeMillisec = timeElapsed / 1000000;

                long seconds = (timeMillisec / 1000) % 60;
                long minutes = (timeMillisec / 1000) / 60;
                long tenths = (timeMillisec % 1000) / 100;

                String display = String.format("Time: %02d:%02d.%d", minutes, seconds, tenths);
                timerText.setText(display);
            }
        };
    }
// METHODS to use timer during game
        public void start() {
            startTime = System.nanoTime();
            gameTimer.start();
        }
        public void stop() {
            gameTimer.stop();
        }

        public void reset() {
            timeElapsed = 0;
            timerText.setText("Time: 00:00.0");
        }

        public Text getText() {
        return timerText;
        }

        public int getMinutes() {
        return (int) ((timeElapsed/1000000000) / 60);
        }
        public int getSeconds() {
        return (int) ((timeElapsed/1000000000) % 60);
        }
        public int getTenths() {
        return (int) ((timeElapsed/100000000) % 10);
        }
}

