package seedu.taassist.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.taassist.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.taassist.commons.exceptions.IllegalValueException;
import seedu.taassist.commons.util.JsonUtil;
import seedu.taassist.model.TaAssist;
import seedu.taassist.testutil.TypicalStudents;

public class JsonSerializableTaAssistTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableTaAssistTest");
    private static final Path TYPICAL_STUDENTS_FILE = TEST_DATA_FOLDER.resolve("typicalStudentsTaAssist.json");
    private static final Path INVALID_STUDENT_FILE = TEST_DATA_FOLDER.resolve("invalidStudentTaAssist.json");
    private static final Path DUPLICATE_STUDENT_FILE = TEST_DATA_FOLDER.resolve("duplicateStudentTaAssist.json");
    private static final Path DUPLICATE_MODULE_CLASSES_FILE =
            TEST_DATA_FOLDER.resolve("duplicateModuleClassTaAssist.json");
    private static final Path NON_EXISTENT_MODULE_FILE =
            TEST_DATA_FOLDER.resolve("nonExistentModuleTaAssist.json");
    private static final Path NON_EXISTENT_SESSION_FILE =
            TEST_DATA_FOLDER.resolve("nonExistentSessionTaAssist.json");

    @Test
    public void toModelType_typicalStudentsFile_success() throws Exception {
        JsonSerializableTaAssist dataFromFile = JsonUtil.readJsonFile(TYPICAL_STUDENTS_FILE,
                JsonSerializableTaAssist.class).get();
        TaAssist taAssistFromFile = dataFromFile.toModelType();
        TaAssist typicalStudentsTaAssist = TypicalStudents.getTypicalTaAssist();
        assertEquals(taAssistFromFile, typicalStudentsTaAssist);
    }

    @Test
    public void toModelType_invalidStudentFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTaAssist dataFromFile = JsonUtil.readJsonFile(INVALID_STUDENT_FILE,
                JsonSerializableTaAssist.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateStudents_throwsIllegalValueException() throws Exception {
        JsonSerializableTaAssist dataFromFile = JsonUtil.readJsonFile(DUPLICATE_STUDENT_FILE,
                JsonSerializableTaAssist.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTaAssist.MESSAGE_DUPLICATE_STUDENT,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateModuleClasses_throwsIllegalValueException() throws Exception {
        JsonSerializableTaAssist dataFromFile = JsonUtil.readJsonFile(DUPLICATE_MODULE_CLASSES_FILE,
                JsonSerializableTaAssist.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTaAssist.MESSAGE_DUPLICATE_MODULE_CLASS,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_nonExistentModule_throwsIllegalValueException() throws Exception {
        JsonSerializableTaAssist dataFromFile = JsonUtil.readJsonFile(NON_EXISTENT_MODULE_FILE,
                JsonSerializableTaAssist.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTaAssist.MESSAGE_CLASS_NOT_FOUND,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_nonExistentSession_throwsIllegalValueException() throws Exception {
        JsonSerializableTaAssist dataFromFile = JsonUtil.readJsonFile(NON_EXISTENT_SESSION_FILE,
            JsonSerializableTaAssist.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTaAssist.MESSAGE_SESSION_NOT_FOUND,
            dataFromFile::toModelType);
    }
}
