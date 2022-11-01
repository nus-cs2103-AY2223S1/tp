package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.File;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;



public class ImageStorageTest {
    @Test
    void validJpgFileSuccess() throws Exception {
        File validFile = new File("src/test/resources/profile/default-profile-pic.jpg");
        assertTrue(ImageStorage.isJpgFile(validFile));
    }

    @Test
    void invalidJpgFileThrowsInvalidFileFormatException() throws Exception {
        File validFile = new File("src/test/resources/profile/default-profile-pic.png");
        assertFalse(ImageStorage.isJpgFile(validFile));
    }

    @Test
    void nonExistentFileThrowsException() {
        File invalidFile = new File("src/test/resources/noSuchFile.jpg");
        assertThrows(CommandException.class, () -> ImageStorage.isJpgFile(invalidFile));
    }

}
