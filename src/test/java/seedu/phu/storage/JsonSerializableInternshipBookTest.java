package seedu.phu.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.phu.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.phu.commons.exceptions.IllegalValueException;
import seedu.phu.commons.util.JsonUtil;
import seedu.phu.model.InternshipBook;
import seedu.phu.testutil.TypicalInternships;

public class JsonSerializableInternshipBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableInternshipBookTest");
    private static final Path TYPICAL_INTERNSHIPS_FILE = TEST_DATA_FOLDER
            .resolve("typicalInternshipInternshipBook.json");
    private static final Path INVALID_INTERNSHIP_FILE = TEST_DATA_FOLDER
            .resolve("invalidInternshipInternshipBook.json");
    private static final Path DUPLICATE_INTERNSHIP_FILE = TEST_DATA_FOLDER
            .resolve("duplicateInternshipInternshipBook.json");

    @Test
    public void toModelType_typicalInternshipsFile_success() throws Exception {
        JsonSerializableInternshipBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_INTERNSHIPS_FILE,
                JsonSerializableInternshipBook.class).get();
        InternshipBook internshipBookFromFile = dataFromFile.toModelType();
        InternshipBook typicalInternshipsInternshipBook = TypicalInternships.getTypicalInternshipBook();
        assertEquals(internshipBookFromFile, typicalInternshipsInternshipBook);
    }

    @Test
    public void toModelType_invalidInternshipFile_throwsIllegalValueException() throws Exception {
        JsonSerializableInternshipBook dataFromFile = JsonUtil.readJsonFile(INVALID_INTERNSHIP_FILE,
                JsonSerializableInternshipBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateInternships_throwsIllegalValueException() throws Exception {
        JsonSerializableInternshipBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_INTERNSHIP_FILE,
                JsonSerializableInternshipBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableInternshipBook.MESSAGE_DUPLICATE_INTERNSHIP,
                dataFromFile::toModelType);
    }

}
