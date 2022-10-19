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
    public void isValidPdfFilePathTest() {
        // null file path
        assertThrows(NullPointerException.class, () -> FilePath.isValidPdfFilePath(null));

        // invalid file path
        assertFalse(FilePath.isValidPdfFilePath(" ")); // spaces only
        assertFalse(FilePath.isValidPdfFilePath("")); // empty string

        // valid file path
        assertTrue(FilePath.isValidPdfFilePath("src/test/data/TestPDFs/Test_PDF5.pdf"));
    }

    @Test
    public void isValidFilePathTest() {
        // null file path
        assertThrows(NullPointerException.class, () -> FilePath.isValidFilePath(null));

        // invalid file path
        assertFalse(FilePath.isValidFilePath(" ")); // spaces only
        assertFalse(FilePath.isValidFilePath("src/test/data/TestPDFs/Test_PDF5.p"));

        // valid file path
        assertTrue(FilePath.isValidFilePath("")); // empty string
        assertTrue(FilePath.isValidFilePath("src/test/data/TestPDFs/Test_PDF5.pdf"));
    }

    @Test
    public void isEmptyFilePathTest() {
        // null file path
        assertThrows(NullPointerException.class, () -> FilePath.isEmptyPdfFilePath(null));

        // invalid file path
        assertFalse(FilePath.isEmptyPdfFilePath(" ")); // spaces only
        assertFalse(FilePath.isEmptyPdfFilePath("src/test/data/TestPDFs/Test_PDF5.pdf"));
        assertFalse(FilePath.isEmptyPdfFilePath("src/test/data/TestPDFs/Test_PDF5.p"));

        // valid file path
        assertTrue(FilePath.isEmptyPdfFilePath("")); // empty string
    }
}
