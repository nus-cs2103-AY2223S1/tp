package seedu.uninurse.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.uninurse.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.uninurse.commons.exceptions.IllegalValueException;
import seedu.uninurse.commons.util.JsonUtil;
import seedu.uninurse.model.UninurseBook;
import seedu.uninurse.testutil.TypicalPatients;

public class JsonSerializableUninurseBookTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableUninurseBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPatientsUninurseBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPatientUninurseBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePatientUninurseBook.json");

    @Test
    public void toModelType_typicalPatientsFile_success() throws Exception {
        JsonSerializableUninurseBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableUninurseBook.class).get();
        UninurseBook uninurseBookFromFile = dataFromFile.toModelType();
        UninurseBook typicalPatientsUninurseBook = TypicalPatients.getTypicalUninurseBook();
        assertEquals(uninurseBookFromFile, typicalPatientsUninurseBook);
    }

    @Test
    public void toModelType_invalidPatientFile_throwsIllegalValueException() throws Exception {
        JsonSerializableUninurseBook dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableUninurseBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePatients_throwsIllegalValueException() throws Exception {
        JsonSerializableUninurseBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableUninurseBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableUninurseBook.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }
}
