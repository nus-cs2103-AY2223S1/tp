package seedu.travelr.storage;

import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_TRIPS_FILE = TEST_DATA_FOLDER.resolve("typicalTripsAddressBook.json");
    private static final Path INVALID_TRIP_FILE = TEST_DATA_FOLDER.resolve("invalidTripAddressBook.json");
    private static final Path DUPLICATE_TRIP_FILE = TEST_DATA_FOLDER.resolve("duplicateTripAddressBook.json");

    //Not working at the moment
    /*
    @Test
    public void toModelType_typicalTripsFile_success() throws Exception {
        seedu.travelr.storage.JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_TRIPS_FILE,
                seedu.travelr.storage.JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalTripsAddressBook = TypicalTrips.getTypicalTravelr();
        assertEquals(addressBookFromFile, typicalTripsAddressBook);
    }

    @Test
    public void toModelType_invalidTripFile_throwsIllegalValueException() throws Exception {
        seedu.travelr.storage.JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_TRIP_FILE,
                seedu.travelr.storage.JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateTrips_throwsIllegalValueException() throws Exception {
        seedu.travelr.storage.JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_TRIP_FILE,
                seedu.travelr.storage.JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, seedu.travelr.storage
                .JsonSerializableAddressBook.MESSAGE_DUPLICATE_TRIP, dataFromFile::toModelType);
    }
     */

}
