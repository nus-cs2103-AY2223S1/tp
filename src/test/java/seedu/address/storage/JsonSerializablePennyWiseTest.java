package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.PennyWise;
import seedu.address.testutil.TypicalEntry;
public class JsonSerializablePennyWiseTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializablePennyWiseTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsAddressBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonAddressBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonAddressBook.json");

//     @Test
//     public void toModelType_typicalPersonsFile_success() throws Exception {
//         JsonSerializablePennyWise dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
//                 JsonSerializablePennyWise.class).get();
//         PennyWise pennyWiseFromFile = dataFromFile.toModelType();
//         PennyWise typicalEntryPennyWise = TypicalEntry.getTypicalPennyWise();
//         assertEquals(pennyWiseFromFile, typicalEntryPennyWise);
//
//     }

//     @Test
//     public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
//         JsonSerializablePennyWise dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
//                 JsonSerializablePennyWise.class).get();
//         assertThrows(IllegalValueException.class, dataFromFile::toModelType);
//     }

    // @Test
    // public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
    //     JsonSerializablePennyWise dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
    //             JsonSerializablePennyWise.class).get();
    //     assertThrows(IllegalValueException.class, JsonSerializablePennyWise.MESSAGE_DUPLICATE_ENTRY,
    //             dataFromFile::toModelType);
    // }

}
