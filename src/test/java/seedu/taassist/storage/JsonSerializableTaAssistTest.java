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

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_STUDENTS_FILE = TEST_DATA_FOLDER.resolve("typicalStudentsAddressBook.json");
    private static final Path INVALID_STUDENT_FILE = TEST_DATA_FOLDER.resolve("invalidStudentAddressBook.json");
    private static final Path DUPLICATE_STUDENT_FILE = TEST_DATA_FOLDER.resolve("duplicateStudentAddressBook.json");

    @Test
    public void toModelType_typicalStudentsFile_success() throws Exception {
        JsonSerializableTaAssist dataFromFile = JsonUtil.readJsonFile(TYPICAL_STUDENTS_FILE,
                JsonSerializableTaAssist.class).get();
        TaAssist addressBookFromFile = dataFromFile.toModelType();
        TaAssist typicalStudentsAddressBook = TypicalStudents.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalStudentsAddressBook);
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

}
