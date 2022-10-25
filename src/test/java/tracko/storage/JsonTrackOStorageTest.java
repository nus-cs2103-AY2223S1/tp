package tracko.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static tracko.testutil.Assert.assertThrows;
import static tracko.testutil.TypicalItems.INVENTORY_ITEM_8;
import static tracko.testutil.TypicalItems.INVENTORY_ITEM_9;
import static tracko.testutil.TypicalOrders.ORDER_8;
import static tracko.testutil.TypicalOrders.ORDER_9;
import static tracko.testutil.TypicalOrders.getTrackOWithTypicalOrders;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import tracko.commons.exceptions.DataConversionException;
import tracko.model.ReadOnlyTrackO;
import tracko.model.TrackO;

public class JsonTrackOStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonTrackOStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readTrackO(null));
    }

    private java.util.Optional<ReadOnlyTrackO> readTrackO(String filePath) throws Exception {
        return new JsonTrackOStorage(Paths.get(filePath)).readTrackO(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTrackO("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readTrackO("notJsonFormatOrders.json"));
    }

    @Test
    public void readAddressBook_invalidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTrackO("invalidOrderOrders.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTrackO("invalidAndValidOrderOrders.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempTrackO.json");
        TrackO original = getTrackOWithTypicalOrders();
        JsonTrackOStorage jsonTrackOStorage = new JsonTrackOStorage(filePath);

        // Save in new file and read back
        jsonTrackOStorage.saveTrackO(original, filePath);
        ReadOnlyTrackO readBack = jsonTrackOStorage.readTrackO(filePath).get();

        assertEquals(original, new TrackO(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addItem(INVENTORY_ITEM_8);
        original.addOrder(ORDER_8);

        // re-add after implementation of remove order
        // original.removePerson(ORDER_1);
        jsonTrackOStorage.saveTrackO(original, filePath);
        readBack = jsonTrackOStorage.readTrackO(filePath).get();

        assertEquals(original, new TrackO(readBack));

        // Save and read without specifying file path
        original.addItem(INVENTORY_ITEM_9);
        original.addOrder(ORDER_9);
        jsonTrackOStorage.saveTrackO(original); // file path not specified
        readBack = jsonTrackOStorage.readTrackO().get(); // file path not specified
        assertEquals(original, new TrackO(readBack));
    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTrackO(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveTrackO(ReadOnlyTrackO trackO, String filePath) {
        try {
            new JsonTrackOStorage(Paths.get(filePath))
                    .saveTrackO(trackO, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTrackO_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTrackO(new TrackO(), null));
    }
}
