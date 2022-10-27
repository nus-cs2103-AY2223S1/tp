package seedu.address.model.promotion;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

/**
 * Manages the operations for parsing images(promotions) for the GUI.
 */
public class Promotion {
    private ObservableList<Image> promotionList;

    public Promotion() {
        this.promotionList = FXCollections.observableArrayList();
    }

    /**
     * Retrieves and stores all the images from a directory into an ObservableList.
     * @param filePath Filepath indicating the directory location to retrieve images from
     * @throws IOException if some error occurs when opening/reading the images
     */
    public void parseAllPromotions(String filePath) throws IOException {
        try {
            File directory = new File(filePath);
            for (File f : Objects.requireNonNull(directory.listFiles())) {
                Image image = new Image(new FileInputStream(f.getAbsolutePath()));
                this.promotionList.add(image);
            }
        } catch (IOException e) {
            throw new IOException("Parsing Promotion Poster failed! Please Restart & Try Again!");
        }
    }

    public ObservableList<Image> getPromotionList() {
        return this.promotionList;
    }

}
