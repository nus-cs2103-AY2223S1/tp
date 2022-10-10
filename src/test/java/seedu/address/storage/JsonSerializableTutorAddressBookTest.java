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
import seedu.address.model.person.tutor.Tutor;
import seedu.address.testutil.TypicalTutors;

public class JsonSerializableTutorAddressBookTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_TUTORS_FILE = TEST_DATA_FOLDER.resolve("typicalTutorsAddressBook.json");
    private static final Path INVALID_TUTOR_FILE = TEST_DATA_FOLDER.resolve("invalidTutorAddressBook.json");
    private static final Path DUPLICATE_TUTOR_FILE = TEST_DATA_FOLDER.resolve("duplicateTutorAddressBook.json");

    @Test
    public void toModelType_typicalTutorsFile_success() throws Exception {
        JsonSerializableTutorAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_TUTORS_FILE, JsonSerializableTutorAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalTutorsAddressBook = TypicalTutors.getTypicalTutorsAddressBook();
        assertEquals(addressBookFromFile, typicalTutorsAddressBook);
    }

    @Test
    public void toModelType_invalidTutorFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTutorAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_TUTOR_FILE,
                JsonSerializableTutorAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableTutorAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_TUTOR_FILE,
                JsonSerializableTutorAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTutorAddressBook.MESSAGE_DUPLICATE_TUTOR,
                dataFromFile::toModelType);
    }

    @Test
    public void getTutorsList_typicalTutorsFile_success() throws Exception {
        JsonSerializableTutorAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_TUTORS_FILE, JsonSerializableTutorAddressBook.class).get();
        List<Tutor> tutorList = new ArrayList<>(Arrays.asList(TypicalTutors.TUTOR1, TypicalTutors.TUTOR2));
        assertEquals(tutorList, dataFromFile.getTutorsList());
    }

}
