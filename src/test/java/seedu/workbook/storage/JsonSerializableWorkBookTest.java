package seedu.workbook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.workbook.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.workbook.commons.exceptions.IllegalValueException;
import seedu.workbook.commons.util.JsonUtil;
import seedu.workbook.model.WorkBook;
import seedu.workbook.testutil.TypicalInternships;

public class JsonSerializableWorkBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableWorkBookTest");
    private static final Path TYPICAL_INTERNSHIPS_FILE = TEST_DATA_FOLDER.resolve("typicalInternshipsWorkBook.json");
    private static final Path INVALID_INTERNSHIP_FILE = TEST_DATA_FOLDER.resolve("invalidInternshipWorkBook.json");
    private static final Path DUPLICATE_INTERNSHIP_FILE = TEST_DATA_FOLDER.resolve("duplicateInternshipWorkBook.json");

    @Test
    public void toModelType_typicalInternshipsFile_success() throws Exception {
        JsonSerializableWorkBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_INTERNSHIPS_FILE,
                JsonSerializableWorkBook.class).get();
        WorkBook workBookFromFile = dataFromFile.toModelType();
        WorkBook typicalInternshipsWorkBook = TypicalInternships.getTypicalWorkBook();
        assertEquals(workBookFromFile, typicalInternshipsWorkBook);
    }

    @Test
    public void toModelType_invalidInternshipFile_throwsIllegalValueException() throws Exception {
        JsonSerializableWorkBook dataFromFile = JsonUtil.readJsonFile(INVALID_INTERNSHIP_FILE,
                JsonSerializableWorkBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateInternships_throwsIllegalValueException() throws Exception {
        JsonSerializableWorkBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_INTERNSHIP_FILE,
                JsonSerializableWorkBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableWorkBook.MESSAGE_DUPLICATE_INTERNSHIP,
                dataFromFile::toModelType);
    }

}
