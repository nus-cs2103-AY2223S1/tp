package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.model.tuitionclass.TuitionClass;
import seedu.address.testutil.TypicalTuitionClasses;


public class JsonSerializableTuitionClassTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_TUITIONCLASSES_FILE =
            TEST_DATA_FOLDER.resolve("typicalTuitionClassesAddressBook.json");
    private static final Path INVALID_TUITIONCLASS_FILE =
            TEST_DATA_FOLDER.resolve("invalidTuitionClassAddressBook.json");
    private static final Path DUPLICATE_TUITIONCLASS_FILE =
            TEST_DATA_FOLDER.resolve("duplicateTuitionClassAddressBook.json");

    @Test
    public void toModelType_typicalTuitionClassesFile_success() throws Exception {
        JsonSerializableTuitionClassAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_TUITIONCLASSES_FILE,
                JsonSerializableTuitionClassAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalTuitionClassAddressBook = TypicalTuitionClasses.getTypicalTuitionClassesAddressBook();
        assertEquals(addressBookFromFile, typicalTuitionClassAddressBook);
    }

    @Test
    public void toModelType_invalidTuitionClassFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTuitionClassAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_TUITIONCLASS_FILE,
                JsonSerializableTuitionClassAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateTuitionClasses_throwsIllegalValueException() throws Exception {
        JsonSerializableTuitionClassAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_TUITIONCLASS_FILE,
                JsonSerializableTuitionClassAddressBook.class).get();
        assertThrows(IllegalValueException.class,
                JsonSerializableTuitionClassAddressBook.MESSAGE_DUPLICATE_TUITIONCLASS,
                dataFromFile::toModelType);
    }

    @Test
    public void getTutorsList_typicalTuitionClassesFile_success() throws Exception {
        JsonSerializableTuitionClassAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_TUITIONCLASSES_FILE,
                JsonSerializableTuitionClassAddressBook.class).get();
        List<TuitionClass> tuitionClassList = new ArrayList<>(Arrays.asList(TypicalTuitionClasses.TUITIONCLASS1,
                TypicalTuitionClasses.TUITIONCLASS2));
        assertEquals(tuitionClassList, dataFromFile.getTuitionClassesList());
    }

}
