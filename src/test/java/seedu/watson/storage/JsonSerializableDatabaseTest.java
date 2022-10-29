package seedu.watson.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.watson.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.watson.commons.exceptions.IllegalValueException;
import seedu.watson.commons.util.JsonUtil;
import seedu.watson.model.Database;
import seedu.watson.testutil.TypicalStudents;

public class JsonSerializableDatabaseTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableDatabaseTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalStudentsAddressBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidStudentAddressBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicateStudentAddressBook.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableDatabase dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                                                                         JsonSerializableDatabase.class).get();
        Database databaseFromFile = dataFromFile.toModelType();
        Database typicalPersonsDatabase = TypicalStudents.getTypicalDatabase();
        assertEquals(databaseFromFile, typicalPersonsDatabase);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableDatabase dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                                                                         JsonSerializableDatabase.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableDatabase dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                                                                         JsonSerializableDatabase.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableDatabase.MESSAGE_DUPLICATE_PERSON,
                     dataFromFile::toModelType);
    }

}
