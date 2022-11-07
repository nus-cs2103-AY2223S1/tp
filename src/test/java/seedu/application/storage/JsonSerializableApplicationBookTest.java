package seedu.application.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.application.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.application.commons.exceptions.IllegalValueException;
import seedu.application.commons.util.JsonUtil;
import seedu.application.model.ApplicationBook;
import seedu.application.testutil.TypicalApplications;

public class JsonSerializableApplicationBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get(
            "src", "test", "data", "JsonSerializableApplicationBookTest");
    private static final Path TYPICAL_APPLICATIONS_FILE = TEST_DATA_FOLDER.resolve(
            "typicalApplicationApplicationBook.json");
    private static final Path INVALID_APPLICATION_FILE = TEST_DATA_FOLDER.resolve(
            "invalidApplicationApplicationBook.json");
    private static final Path DUPLICATE_APPLICATION_FILE = TEST_DATA_FOLDER.resolve(
            "duplicateApplicationApplicationBook.json");

    @Test
    public void toModelType_typicalApplicationsFile_success() throws Exception {
        JsonSerializableApplicationBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_APPLICATIONS_FILE,
                JsonSerializableApplicationBook.class).get();
        ApplicationBook applicationBookFromFile = dataFromFile.toModelType();
        ApplicationBook typicalApplicationsApplicationBook = TypicalApplications.getTypicalApplicationBook();
        assertEquals(applicationBookFromFile, typicalApplicationsApplicationBook);
    }

    @Test
    public void toModelType_invalidApplicationFile_throwsIllegalValueException() throws Exception {
        JsonSerializableApplicationBook dataFromFile = JsonUtil.readJsonFile(INVALID_APPLICATION_FILE,
                JsonSerializableApplicationBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateApplication_throwsIllegalValueException() throws Exception {
        JsonSerializableApplicationBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_APPLICATION_FILE,
                JsonSerializableApplicationBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableApplicationBook.MESSAGE_DUPLICATE_APPLICATION,
                dataFromFile::toModelType);
    }

}
