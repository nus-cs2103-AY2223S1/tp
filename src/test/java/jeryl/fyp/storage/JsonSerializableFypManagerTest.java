package jeryl.fyp.storage;

import static jeryl.fyp.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import jeryl.fyp.commons.exceptions.IllegalValueException;
import jeryl.fyp.commons.util.JsonUtil;
import jeryl.fyp.model.FypManager;
import jeryl.fyp.testutil.TypicalStudents;

public class JsonSerializableFypManagerTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableFypManagerTest");
    private static final Path TYPICAL_STUDENTS_FILE = TEST_DATA_FOLDER.resolve("typicalStudentsFypManager.json");
    private static final Path INVALID_STUDENT_FILE = TEST_DATA_FOLDER.resolve("invalidStudentFypManager.json");
    private static final Path DUPLICATE_STUDENT_FILE = TEST_DATA_FOLDER.resolve("duplicateStudentFypManager.json");

    @Test
    public void toModelType_typicalStudentsFile_success() throws Exception {
        JsonSerializableFypManager dataFromFile = JsonUtil.readJsonFile(TYPICAL_STUDENTS_FILE,
                JsonSerializableFypManager.class).get();
        FypManager fypManagerFromFile = dataFromFile.toModelType();
        FypManager typicalStudentsFypManager = TypicalStudents.getTypicalFypManager();
        assertEquals(fypManagerFromFile, typicalStudentsFypManager);
    }

    @Test
    public void toModelType_invalidStudentFile_throwsIllegalValueException() throws Exception {
        JsonSerializableFypManager dataFromFile = JsonUtil.readJsonFile(INVALID_STUDENT_FILE,
                JsonSerializableFypManager.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateStudents_throwsIllegalValueException() throws Exception {
        JsonSerializableFypManager dataFromFile = JsonUtil.readJsonFile(DUPLICATE_STUDENT_FILE,
                JsonSerializableFypManager.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableFypManager.MESSAGE_DUPLICATE_STUDENT,
                dataFromFile::toModelType);
    }

}
