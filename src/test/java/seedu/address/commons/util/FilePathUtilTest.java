package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.FilePath;

public class FilePathUtilTest {

    @Test
    public void isValidPath() {
        // valid path
        assertTrue(FileUtil.isValidPath("valid/file/path"));

        // invalid path
        assertFalse(FileUtil.isValidPath("a\0"));

        // null path -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> FileUtil.isValidPath(null));
    }

    @Test
    public void checkPdfFilePathTest() {
        //valid path
        assertTrue(FileUtil.checkValidPdfFilePath(new FilePath("src/test/data/TestPDFs/Test_PDF.pdf")));

        //invalid path
        assertFalse(FileUtil.checkValidPdfFilePath(new FilePath("a\0")));

        // null path -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> FileUtil.checkValidPdfFilePath(new FilePath(null)));
    }

    @Test
    public void openPdfFileTest() {
        //pdf file does not exist in filepath
        assertThrows(IOException.class, () -> FileUtil.openPdfFile("src/Test_PDF.pdf"));

        // null path -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> FileUtil.openPdfFile(null));
    }
}
