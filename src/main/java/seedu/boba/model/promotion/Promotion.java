package seedu.boba.model.promotion;

import java.io.InputStream;

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
     */
    public void parseAllPromotions(String filePath) {
        for (int i = 1; i <= 5; i++) {
            String currFilePath = filePath + "/promo" + i + ".png";
            InputStream is = getFileFromResourceAsStream(currFilePath);
            Image image = new Image(is);
            this.promotionList.add(image);
        }
        InputStream is = getFileFromResourceAsStream(filePath + "/promo-anya.gif");
        Image image = new Image(is);
        this.promotionList.add(image);
    }

    public ObservableList<Image> getPromotionList() {
        return this.promotionList;
    }

    /**
     * Retrieves individual files as resources (compatible with JAR files)
     * Adapted from: https://mkyong.com/java/java-read-a-file-from-resources-folder/
     *
     * @param fileName Filepath of the file we want to retrieve
     * @return InputStream that contains the file information
     */
    private InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("File not found! " + fileName);
        } else {
            return inputStream;
        }

    }
}
