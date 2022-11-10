package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.testutil.TypicalCustomers;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_CUSTOMERS_FILE = TEST_DATA_FOLDER.resolve("typicalCustomersAddressBook.json");
    private static final Path INVALID_CUSTOMER_FILE = TEST_DATA_FOLDER.resolve("invalidCustomerAddressBook.json");
    private static final Path DUPLICATE_CUSTOMER_FILE = TEST_DATA_FOLDER.resolve("duplicateCustomerAddressBook.json");

    @Test
    public void toModelType_typicalCustomersFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_CUSTOMERS_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = new AddressBook(dataFromFile.toModelType());
        AddressBook typicalCustomersAddressBook = new AddressBook(TypicalCustomers.getTypicalAddressBook());
        assertTrue(addressBookFromFile.isSameAddressBook(typicalCustomersAddressBook));
    }

    @Test
    public void toModelType_invalidCustomerFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_CUSTOMER_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateCustomers_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_CUSTOMER_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_CUSTOMER,
                dataFromFile::toModelType);
    }

}
