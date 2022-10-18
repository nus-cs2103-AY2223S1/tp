package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FilePathTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Address(null));
    }

    @Test
    public void constructor_invalidFilePath_throwsIllegalArgumentException() {
        String invalidFilePath = "src/main/resources/misc/Test_PDF3.p";
        assertThrows(IllegalArgumentException.class, () -> new FilePath(invalidFilePath));
    }

    @Test
    public void isValidPdfFilePath() {
        // null file path
        assertThrows(NullPointerException.class, () -> FilePath.isValidPdfFilePath(null));

        // invalid file path
        assertFalse(FilePath.isValidPdfFilePath("")); // empty string
        assertFalse(FilePath.isValidPdfFilePath(" ")); // spaces only

        // valid file path
        assertTrue(FilePath.isValidPdfFilePath("src/main/resources/misc/Test_PDF3.pdf"));
    }
}