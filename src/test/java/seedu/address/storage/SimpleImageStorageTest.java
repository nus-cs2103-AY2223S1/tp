package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class SimpleImageStorageTest {
    @TempDir
    public Path testFolder = Path.of("images");
    private ImageStorage imageStorage;

    @BeforeEach
    void setUp() {
        imageStorage = new SimpleImageStorage(testFolder);
    }

    @Test
    void getBaseDirectoryPath() {
        assertEquals(imageStorage.getBaseDirectoryPath(), testFolder);
    }

    @Test
    void getRandomImagePath() {
        assertNotEquals(imageStorage.getRandomImagePath(), imageStorage.getRandomImagePath());
    }

    @Test
    void isPathInBaseDirectory() {
        assertFalse(imageStorage.isPathInBaseDirectory(Path.of("notImages", "subpath")));
        assertTrue(imageStorage.isPathInBaseDirectory(imageStorage.getBaseDirectoryPath().resolve("subpath")));
        assertTrue(imageStorage.isPathInBaseDirectory(imageStorage.getRandomImagePath()));
    }

    @Test
    void isValidImage() {
        assertFalse(imageStorage.isValidImage(Path.of("images")));
    }

    @Test
    void saveImage() {
        try {
            imageStorage.saveImage(new BufferedImage(10, 10, BufferedImage.TYPE_3BYTE_BGR),
                    imageStorage.getBaseDirectoryPath().resolve("test1.png"));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error saving this image.");
        }
    }

    @Test
    void getImage() {
        try {
            imageStorage.getImage(Paths.get("src", "test", "data", "images", "test_image_1.png"));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error saving this image.");
        }
    }

}
