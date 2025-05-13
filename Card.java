/**
 * Karli Bryant
 * April 23, 2025
 * COMP 167 Section 004
 *
 * Class Card extends StackPane to represent a single playing card in the
 * Concentration game. Encapsulates its flipped/matched state,
 * image path, ImageView display, and provides methods to flip,
 * reset, and resize itself.
 */
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;


public class Card extends StackPane {
    //private fields
    private boolean flipped;
    private boolean matched;
    private String path;
    private Image image;
    private ImageView imageView;
    private final Image defaultImageView = new Image("file:/C:\\Users\\karli\\IdeaProjects\\Comp167\\major-program-3-concentration-karlib1\\cardimages\\NCAT-logo.png");

    // No-Arg & arg constructors
    public Card() {
        flipped = true;
        matched = false;
        path = ""; // Must be specified with mutator to set image
        imageView = new ImageView(); // empty view for now
        this.getChildren().add(imageView);
    }
    public Card(String imagePath) {
        flipped = true;
        matched = false;
        image = new Image(imagePath);
        imageView = new ImageView(image);
        this.getChildren().add(imageView);

    }
    // Methods to interact with card

    public void flipCard() {
        if (flipped) { // (flipped = true means default pic should currently show)
            flipped = false;
            imageView.setImage(defaultImageView);
        }
        else{ // switches picture & bool val
            flipped = true;
           imageView.setImage(image);
        }
    }

    // helper method to reset cards for NewGame
    public void reset() {
        flipped = false;
        imageView.setImage(defaultImageView);
    }
   // Getters & Setters
    public void setCardAndImageSize(int width, int height) {
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
    }
    public void setPath(String path) {
        this.path = path;
        image = new Image(path);
        imageView.setImage(image);
    }
    public String getPath() {
        return path;
    }
    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }
    public boolean getFlipped() {
        return flipped;
    }
    public void setMatched(boolean matched) {
        this.matched = matched;
    }
    public boolean getMatched() {
        return matched;
    }
    public void setImage(Image image) {
        this.image = image;
        imageView.setImage(image);
    }
    public Image getImage() {
        return image;
    }

}
