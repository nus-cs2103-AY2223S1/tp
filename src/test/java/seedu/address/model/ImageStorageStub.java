package seedu.address.model;

import java.awt.image.BufferedImage;
import java.nio.file.Path;

import seedu.address.storage.ImageStorage;

/**
 * A default ImageStorage stub that have all of its methods failing.
 */
public class ImageStorageStub implements ImageStorage {

    @Override
    public Path getBaseDirectoryPath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getRandomImagePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean isPathInBaseDirectory(Path path) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean isValidImage(Path path) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public BufferedImage getImage(Path path) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void saveImage(BufferedImage image, Path path) {
        throw new AssertionError("This method should not be called.");
    }
}
