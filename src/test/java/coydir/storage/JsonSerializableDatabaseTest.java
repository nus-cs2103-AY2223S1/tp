package coydir.storage;

import static coydir.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import coydir.commons.exceptions.IllegalValueException;
import coydir.commons.util.JsonUtil;
import coydir.model.Database;
import coydir.testutil.TypicalPersons;

public class JsonSerializableDatabaseTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableDatabaseTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsDatabase.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonDatabase.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonDatabase.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableDatabase dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableDatabase.class).get();
        Database databaseFromFile = dataFromFile.toModelType();
        Database typicalPersonsDatabase = TypicalPersons.getTypicalDatabase();
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
