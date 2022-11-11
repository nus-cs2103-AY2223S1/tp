package seedu.studmap.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.studmap.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.studmap.commons.exceptions.IllegalValueException;
import seedu.studmap.commons.util.JsonUtil;
import seedu.studmap.model.StudMap;
import seedu.studmap.testutil.TypicalStudents;

public class JsonSerializableStudMapTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableStudMapTest");
    private static final Path TYPICAL_STUDENTS_FILE = TEST_DATA_FOLDER.resolve("typicalStudentsStudMap.json");
    private static final Path INVALID_STUDENT_FILE = TEST_DATA_FOLDER.resolve("invalidStudentStudMap.json");
    private static final Path DUPLICATE_STUDENT_FILE = TEST_DATA_FOLDER.resolve("duplicateStudentStudMap.json");

    @Test
    public void toModelType_typicalStudentsFile_success() throws Exception {
        JsonSerializableStudMap dataFromFile = JsonUtil.readJsonFile(TYPICAL_STUDENTS_FILE,
                JsonSerializableStudMap.class).get();
        StudMap studMapFromFile = dataFromFile.toModelType();
        StudMap typicalStudentsStudMap = TypicalStudents.getTypicalStudMap();
        assertEquals(studMapFromFile, typicalStudentsStudMap);
    }

    @Test
    public void toModelType_invalidStudentFile_throwsIllegalValueException() throws Exception {
        JsonSerializableStudMap dataFromFile = JsonUtil.readJsonFile(INVALID_STUDENT_FILE,
                JsonSerializableStudMap.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateStudents_throwsIllegalValueException() throws Exception {
        JsonSerializableStudMap dataFromFile = JsonUtil.readJsonFile(DUPLICATE_STUDENT_FILE,
                JsonSerializableStudMap.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableStudMap.MESSAGE_DUPLICATE_STUDENT,
                dataFromFile::toModelType);
    }

}
