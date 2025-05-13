/**
 * Karli Bryant
 * April 23, 2025
 * COMP 167 Section 004
 *
 * Class GamePane extends BorderPane to implement
 * the main game interface for Concentration
 */
import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.File;

public class GamePane extends BorderPane {
    // private fields
    private CardGridPane cardGridPane;
    private GameTimerPane gameTimer;
    private int rows;
    private int cols;
    private int numClicks;
    private int numMatched;
    private Card clickedCardOne;
    private Card clickedCardTwo;
    // Command / status pane fields
    private Button btnExit;
    private Button btnNewGame;

    private RadioButton btnLevel1;
    private RadioButton btnLevel2;
    private RadioButton btnLevel3;
    private RadioButton btnLevel4;
    private RadioButton btnLevel5;
    private RadioButton btnLevel6;
    private ToggleGroup levelGroup;

    private Label lblNumTurns;
    private int numTurns;

    private long lastClickTime;
    private AnimationTimer timer;

    private AudioClip matchSound;
    private AudioClip missSound;

// ARG & NO-ARG CONSTRUCTORS
    public GamePane() {
        int cardSize = 100;
        numClicks = 0;
        numMatched = 0;
        numTurns = 0;

        cardGridPane = new CardGridPane();

        btnExit = new Button("Exit");
        btnNewGame = new Button("New Game");
        levelGroup = new ToggleGroup();

        btnLevel1 = new RadioButton("Level 1 - 2x3");
        btnLevel2 = new RadioButton("Level 2 - 2x4");
        btnLevel3 = new RadioButton("Level 3 - 4x4");
        btnLevel4 = new RadioButton("Level 4 - 4x6");
        btnLevel5 = new RadioButton("Level 5 - 6x6");
        btnLevel6 = new RadioButton("Level 6 - 8x8");

        btnLevel1.setToggleGroup(levelGroup);
        btnLevel2.setToggleGroup(levelGroup);
        btnLevel3.setToggleGroup(levelGroup);
        btnLevel4.setToggleGroup(levelGroup);
        btnLevel5.setToggleGroup(levelGroup);
        btnLevel6.setToggleGroup(levelGroup);

        lblNumTurns = new Label("Turns: " + numTurns); // when turn changes, it will be (numCLicks/2)

        matchSound = new AudioClip(new File("C:\\Users\\karli\\IdeaProjects\\Comp167\\major-program-3-concentration-karlib1\\soundeffects\\Correct Answer sound effect.mp3").toURI().toString());
        missSound = new AudioClip(new File("C:\\Users\\karli\\IdeaProjects\\Comp167\\major-program-3-concentration-karlib1\\soundeffects\\Wrong Answer Sound effect.mp3").toURI().toString());

        gameTimer = new GameTimerPane();

        this.setCenter(cardGridPane);
        this.setBottom(CommandPane());
        this.setTop(StatusPane());

        newGameHelper();
        newGame();
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - lastClickTime > 800000000) {
                    if (clickedCardOne.getPath().equals(clickedCardTwo.getPath())) {
                        matchSound.play();
                        clickedCardOne.setMatched(true);
                        clickedCardTwo.setMatched(true);
                        // won't be able to flip cards again
                        clickedCardOne.setDisable(true);
                        clickedCardTwo.setDisable(true);
                        clickedCardOne.setPath("cardimages/black.jpg"); // FIXME set to black image when matched
                        clickedCardTwo.setPath("cardimages/black.jpg");
                        ++numMatched;

                        finishGame(); // ends game when all pairs matched
                    }
                    else {
                        missSound.play();
                        clickedCardOne.flipCard();
                        clickedCardTwo.flipCard();
                    }
                    clickedCardOne = null;
                    clickedCardTwo = null;
                    numClicks = 0;
                    ++numTurns;
                    lblNumTurns.setText("Turns: " + numTurns);

                    timer.stop();
                }
            }
        };
    }

    public GamePane(int cardSize) {
        numClicks = 0;
        numMatched = 0;
        numTurns = 0;

        cardGridPane = new CardGridPane();

        btnExit = new Button("Exit");
        btnNewGame = new Button("New Game");
        levelGroup = new ToggleGroup();

        btnLevel1 = new RadioButton("Level 1 - 2x3");
        btnLevel2 = new RadioButton("Level 2 - 2x4");
        btnLevel3 = new RadioButton("Level 3 - 4x4");
        btnLevel4 = new RadioButton("Level 4 - 4x6");
        btnLevel5 = new RadioButton("Level 5 - 6x6");
        btnLevel6 = new RadioButton("Level 6 - 8x8");

        btnLevel1.setToggleGroup(levelGroup);
        btnLevel2.setToggleGroup(levelGroup);
        btnLevel3.setToggleGroup(levelGroup);
        btnLevel4.setToggleGroup(levelGroup);
        btnLevel5.setToggleGroup(levelGroup);
        btnLevel6.setToggleGroup(levelGroup);

        lblNumTurns = new Label("Turns: " + numTurns); // when turn changes, it will be (numCLicks/2)

        matchSound = new AudioClip(new File("C:\\Users\\karli\\IdeaProjects\\Comp167\\major-program-3-concentration-karlib1\\soundeffects\\Correct Answer sound effect.mp3").toURI().toString());
        missSound = new AudioClip(new File("C:\\Users\\karli\\IdeaProjects\\Comp167\\major-program-3-concentration-karlib1\\soundeffects\\Wrong Answer Sound effect.mp3").toURI().toString());

        gameTimer = new GameTimerPane();

        this.setCenter(cardGridPane);
        this.setBottom(CommandPane());
        this.setTop(StatusPane());

        //registerCardListeners();
        newGameHelper();
        newGame();
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - lastClickTime > 800000000) {
                    if (clickedCardOne.getPath().equals(clickedCardTwo.getPath())) {
                        matchSound.play();
                        clickedCardOne.setMatched(true);
                        clickedCardTwo.setMatched(true);
                        // won't be able to flip cards again
                        clickedCardOne.setDisable(true);
                        clickedCardTwo.setDisable(true);
                        clickedCardOne.setPath("cardimages/black.jpg"); // FIXME set to black image when matched
                        clickedCardTwo.setPath("cardimages/black.jpg");
                        ++numMatched;

                        finishGame(); // ends game when all pairs matched
                    }
                    else {
                        missSound.play();
                        clickedCardOne.flipCard();
                        clickedCardTwo.flipCard();
                    }
                    clickedCardOne = null;
                    clickedCardTwo = null;
                    numClicks = 0;
                    ++numTurns;
                    lblNumTurns.setText("Turns: " + numTurns);

                    timer.stop();
                }
            }
        };
    }

    // METHODS
    public void newGameHelper() {
        gameTimer.reset();
        cardGridPane.initCards(rows, cols);
        registerCardListeners();
    }

    public void registerCardListeners() {
        for (int i = 0; i < cardGridPane.getCurrentRows(); ++i) {
            for (int j = 0; j < cardGridPane.getCurrentCols(); ++j) {
                Card card = cardGridPane.getCard(i, j);

                card.setOnMousePressed(e -> {
                    if (numTurns == 0) {
                        gameTimer.start();
                    }
                    if (numClicks < 2) {
                        if (!card.getFlipped()) {
                            card.flipCard();
                        }
                        if (clickedCardOne == null) {
                            clickedCardOne = card;
                        }
                        else {
                            clickedCardTwo = card;
                        }
                        ++numClicks;
                        if (numClicks == 2) {
                            lastClickTime = System.nanoTime();
                            timer.start();
                        }
                    }
                });
            }
        }
        btnExit.setOnMousePressed(e -> {
            Platform.exit(); // closes application
        });

    }


    // create StatusPane & CommandPane with helper methods
    private HBox CommandPane() {
        HBox commandPane = new HBox(10);
        commandPane.getChildren().addAll(btnLevel1, btnLevel2, btnLevel3, btnLevel4, btnLevel5, btnLevel6, btnNewGame, btnExit
        );
        return commandPane;
    }

    private HBox StatusPane() {
        HBox statusPane = new HBox(20);
        statusPane.setAlignment(Pos.CENTER);

        lblNumTurns.setFont(Font.font("Courier New", 36));

        statusPane.getChildren().addAll(lblNumTurns, gameTimer.getText());

        return statusPane;
    }

    // Helper method for CommandPane
    private void updateLevel() {
        RadioButton selectedLevel = (RadioButton) levelGroup.getSelectedToggle();

        if (selectedLevel == btnLevel1) {
            rows = 2;
            cols = 3;
        } else if (selectedLevel == btnLevel2) {
            rows = 2;
            cols = 4;
        } else if (selectedLevel == btnLevel3) {
            rows = 4;
            cols = 4;
        } else if (selectedLevel == btnLevel4) {
            rows = 4;
            cols = 6;
        } else if (selectedLevel == btnLevel5) {
            rows = 6;
            cols = 6;
        } else if (selectedLevel == btnLevel6) {
            rows = 8;
            cols = 8;
        }
        cardGridPane.setCurrentRows(rows);
        cardGridPane.setCurrentCols(cols);
    }

    public void newGame() {
        btnNewGame.setOnAction(e -> {
            updateLevel(); // sets rows & cols before calling newGame

            numTurns = 0;
            numMatched = 0;
            lblNumTurns.setText("Turns: " + numTurns);
            newGameHelper();
        });
    }

    public void finishGame() {
        if (rows * cols / 2 == numMatched) {
            gameTimer.stop();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("CONGRATULATIONS");
            alert.setHeaderText("Game Over");
            alert.show();
        }
    }
}
