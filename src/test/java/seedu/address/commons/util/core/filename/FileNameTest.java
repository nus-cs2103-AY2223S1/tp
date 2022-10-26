package seedu.address.commons.util.core.filename;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.filename.FileName;

class FileNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FileName(null));
    }
    @Test
    public void constructor_invalidFileName_throwsIllegalArgumentException() {
        String invalidFileName = "";
        assertThrows(IllegalArgumentException.class, () -> new FileName(invalidFileName));
    }

    @Test
    void isValidFileName() {
        //null fileName
        assertThrows(NullPointerException.class, () -> FileName.isValidFileName(null));

        // invalid fileName
        assertFalse(FileName.isValidFileName("")); // empty string
        assertFalse(FileName.isValidFileName(" ")); // space only
        assertFalse(FileName.isValidFileName("  ")); // tab
        assertFalse(FileName.isValidFileName("&"));
        assertFalse(FileName.isValidFileName("*"));
        assertFalse(FileName.isValidFileName("%"));

        // valid fileName
        assertTrue(FileName.isValidFileName("filename"));
        assertTrue(FileName.isValidFileName("FILENAME"));
        assertTrue(FileName.isValidFileName("0123456789"));
        assertTrue(FileName.isValidFileName("."));
    }

    @Test
    void toCsvFormat() {
        String expected = "filename.csv";
        FileName test = new FileName("filename");
        assertEquals(expected, test.toCsvFormat());
    }
}
