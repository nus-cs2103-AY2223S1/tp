package friday.storage;

/*
import static friday.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
 */

import java.nio.file.Path;
import java.nio.file.Paths;

/*
import org.junit.jupiter.api.Test;

import friday.commons.exceptions.IllegalValueException;
import friday.commons.util.JsonUtil;
import friday.model.Friday;
import friday.testutil.TypicalStudents;
 */
public class JsonSerializableFridayTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableMasteryCheckBookTest");
    private static final Path TYPICAL_STUDENTS_FILE = TEST_DATA_FOLDER.resolve("typicalStudentsAddressBook.json");
    private static final Path INVALID_STUDENT_FILE = TEST_DATA_FOLDER.resolve("invalidStudentAddressBook.json");
    private static final Path DUPLICATE_STUDENT_FILE = TEST_DATA_FOLDER.resolve("duplicateStudentAddressBook.json");

    /*
    @Test
    public void toModelType_typicalStudentsFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_STUDENTS_FILE,
                JsonSerializableAddressBook.class).get();
        Friday addressBookFromFile = dataFromFile.toModelType();
        Friday typicalStudentsAddressBook = TypicalStudents.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalStudentsAddressBook);
    }

    @Test
    public void toModelType_invalidStudentFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_STUDENT_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateStudents_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_STUDENT_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_STUDENT,
                dataFromFile::toModelType);
    }
     */

}
