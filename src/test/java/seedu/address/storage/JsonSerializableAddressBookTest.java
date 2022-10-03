package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.testutil.TypicalCompanies;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_COMPANIES_FILE = TEST_DATA_FOLDER.resolve("typicalCompaniesAddressBook.json");
    private static final Path INVALID_COMPANY_FILE = TEST_DATA_FOLDER.resolve("invalidCompanyAddressBook.json");
    private static final Path DUPLICATE_COMPANY_FILE = TEST_DATA_FOLDER.resolve("duplicateCompanyAddressBook.json");

    @Test
    public void toModelType_typicalCompaniesFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_COMPANIES_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalCompaniesAddressBook = TypicalCompanies.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalCompaniesAddressBook);
    }

    @Test
    public void toModelType_invalidCompanyFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_COMPANY_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateCompanies_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_COMPANY_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_COMPANY,
                dataFromFile::toModelType);
    }

}
