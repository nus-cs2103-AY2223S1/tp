package taskbook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import taskbook.commons.exceptions.IllegalValueException;
import taskbook.commons.util.JsonUtil;
import taskbook.model.TaskBook;
import taskbook.testutil.Assert;
import taskbook.testutil.TypicalTaskBook;

public class JsonSerializableTaskBookTest {

    private static final Path TEST_DATA_FOLDER = Paths
            .get("src", "test", "data", "JsonSerializableTaskBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsTaskBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonTaskBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonTaskBook.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableTaskBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableTaskBook.class).get();
        TaskBook taskBookFromFile = dataFromFile.toModelType();
        TaskBook typicalPersonsTaskBook = TypicalTaskBook.getTypicalTaskBook();
        assertEquals(taskBookFromFile, typicalPersonsTaskBook);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTaskBook dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableTaskBook.class).get();
        Assert.assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableTaskBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableTaskBook.class).get();
        Assert.assertThrows(IllegalValueException.class, JsonSerializableTaskBook.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
