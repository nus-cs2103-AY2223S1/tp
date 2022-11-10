package seedu.address.storage;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Represents a storage for images used by the application.
 */
public interface ImageStorage {
    /**
     * Returns the base directory containing all image files.
     */
    Path getBaseDirectoryPath();

    /** Gets a randomized `.png` file path given a relative directory path to the base directory. */
    Path getRandomImagePath();

    /** Checks if the path is within base directory. */
    boolean isPathInBaseDirectory(Path path);

    boolean isValidImage(Path path);

    /** Loads a buffered image from the given path */
    BufferedImage getImage(Path path) throws IOException;

    /**
     * Saves the given image to the given Path within base directory.
     */
    void saveImage(BufferedImage image, Path path) throws IOException;
}
