package seedu.address.storage;

import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;

public class JsonSerializableBuyerBookTest {

    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonSerializablePersonBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsPersonBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonPersonBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonPersonBook.json");

    //    @Test
    //    public void toModelType_typicalPersonsFile_success() throws Exception {
    //        JsonSerializableBuyerBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
    //                JsonSerializableBuyerBook.class).get();
    //        BuyerBook personBookFromFile = dataFromFile.toModelType();
    //        BuyerBook typicalPersonsPersonBook = TypicalPersons.getTypicalPersonsBook();
    //        assertEquals(personBookFromFile, typicalPersonsPersonBook);
    //    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableBuyerBook dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableBuyerBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    //    @Test
    //    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
    //        JsonSerializableBuyerBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
    //                JsonSerializableBuyerBook.class).get();
    //        assertThrows(IllegalValueException.class, JsonSerializableBuyerBook.MESSAGE_DUPLICATE_PERSON,
    //                dataFromFile::toModelType);
    //    }

}
