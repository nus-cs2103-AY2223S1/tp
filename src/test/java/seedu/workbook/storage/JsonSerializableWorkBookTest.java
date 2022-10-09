package seedu.workbook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.workbook.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.workbook.commons.exceptions.IllegalValueException;
import seedu.workbook.commons.util.JsonUtil;
import seedu.workbook.model.WorkBook;
import seedu.workbook.testutil.TypicalPersons;

public class JsonSerializableWorkBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableWorkBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsWorkBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonWorkBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonWorkBook.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableWorkBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableWorkBook.class).get();
        WorkBook workBookFromFile = dataFromFile.toModelType();
        WorkBook typicalPersonsWorkBook = TypicalPersons.getTypicalWorkBook();
        assertEquals(workBookFromFile, typicalPersonsWorkBook);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableWorkBook dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableWorkBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableWorkBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableWorkBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableWorkBook.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
