package friday.storage;

import static friday.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import friday.commons.exceptions.IllegalValueException;
import friday.commons.util.JsonUtil;
import friday.model.Friday;
import friday.testutil.TypicalStudents;

public class JsonSerializableMasteryCheckBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableFridayTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalStudentFriday.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidStudentFriday.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicateStudentFriday.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableFriday dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableFriday.class).get();
        Friday fridayFromFile = dataFromFile.toModelType();
        Friday typicalPersonsFriday = TypicalStudents.getTypicalFridayForTest();
        assertEquals(fridayFromFile, typicalPersonsFriday);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableFriday dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableFriday.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableFriday dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableFriday.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableFriday.MESSAGE_DUPLICATE_STUDENT,
                dataFromFile::toModelType);
    }

}
