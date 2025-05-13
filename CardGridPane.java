/**
 * Karli Bryant
 * April 23, 2025
 * COMP 167 Section 004
 *
 * Class CardGridPane extends GridPane to manage an 8×8 array of Card objects.
 * Responsible for initializing the grid, enabling/disabling
 * cards per selected level, creating and shuffling the list
 * of image paths, and assigning those images to the cards.
 */
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Collections;

public class CardGridPane extends GridPane {
    // private fields
    private Card[][] cards;
    private ArrayList<String> cardList; // pathnames of all available card images
    private final int MAXROWS = 8; // final bc this is the MAX & won't change
    private final int MAXCOLS = 8;
    private int currentRows;
    private int currentCols;
    private int cardSize; // sidelength (in pixels) of square card

    // ARG & NO-ARG CONSTRUCTORS
    public CardGridPane() {
       this(64);
        cardList = new ArrayList<String>();
        cards = new Card[MAXROWS][MAXCOLS]; // 2D array has rows & cols as max level
        // nested for-loop to populate 2D array
        for (int i = 0; i < MAXROWS; ++i) {
            for (int j = 0; j < MAXCOLS; ++j) {
                Card card = new Card("cardimages/gray.jpg");
                card.setCardAndImageSize(64, 64);
                card.setDisable(true); // makes card unflippable to start game
                this.add(card, j, i);
                cards[i][j] = card;
            }
        }
    }

    public CardGridPane(int cardSize) {
        cards = new Card[MAXROWS][MAXCOLS]; // 2D array has rows & cols as max level
        // nested for-loop to populate 2D array
        for (int i = 0; i < MAXROWS; ++i) {
            for (int j = 0; j < MAXCOLS; ++j) {
                Card card = new Card();
                card.setCardAndImageSize(cardSize, cardSize);
                card.setDisable(true); // makes card unflippable to start game
                this.getChildren().add(card);
                cards[i][j] = card;
            }
        }
    }

    // METHODS

    public void setCardImages() {
        int counter = 0;
        for (int i = 0; i < currentRows; ++i) {
            for (int j = 0; j < currentCols; ++j) {
                cards[i][j].setPath(cardList.get(counter));
                ++counter;
                cards[i][j].reset();
            }
        }
    }

    public void shuffleImages() {
        Collections.shuffle(cardList);
    }
    public Card getCard(int r, int c) {
        return cards[r][c];
    }
    public void initCards(int rows, int cols) {
        this.currentRows = rows;
        this.currentCols = cols;
//        final int totalCards = rows * cols;
        for (int i = 0; i < MAXROWS; i++) {
            for (int j = 0; j < MAXCOLS; j++) {
                if (i < rows && j < cols) {
                    cards[i][j].setDisable(false);
                    cards[i][j].reset(); // resets grey style
                } else {
                    cards[i][j].setDisable(true);
                    cards[i][j].setPath("cardimages/gray.jpg");
                }
            }
        }
        createCardImageList(rows*cols);
        shuffleImages();
        setCardImages();
    }

    public void createCardImageList(int size) {
        cardList.clear();
        int neededPairs = size / 2;
        int counter = 0;
       // add image paths to ArrayList
        for (int i = 0; i < neededPairs; i++) {
            cardList.add("cardimages/image_" + counter + ".png"); // image PAIRS are used, so image is added twice
            cardList.add("cardimages/image_" + counter + ".png");
            counter++;
        }
    }

    // GETTERS & SETTERS
    public void setCurrentRows(int rows) {
        currentRows = rows;
    }
    public int getCurrentRows() {
        return currentRows;
    }
    public void setCurrentCols(int cols) {
        currentCols = cols;
    }
    public int getCurrentCols() {
        return currentCols;
    }
}

