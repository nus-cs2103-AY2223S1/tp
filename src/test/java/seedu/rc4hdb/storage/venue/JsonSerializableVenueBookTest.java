package seedu.rc4hdb.storage.venue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.rc4hdb.testutil.Assert.assertThrows;
import static seedu.rc4hdb.testutil.TypicalVenues.getTypicalVenueBook;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.rc4hdb.commons.exceptions.IllegalValueException;
import seedu.rc4hdb.commons.util.JsonUtil;
import seedu.rc4hdb.model.VenueBook;
import seedu.rc4hdb.storage.venuebook.JsonSerializableVenueBook;

/**
 * Unit tests for {@link JsonSerializableVenueBook}.
 */
public class JsonSerializableVenueBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableVenueBookTest");
    private static final Path TYPICAL_VENUES_FILE = TEST_DATA_FOLDER.resolve("typicalVenuesVenueBook.json");
    private static final Path INVALID_VENUE_FILE = TEST_DATA_FOLDER.resolve("invalidVenueVenueBook.json");
    private static final Path DUPLICATE_VENUE_FILE = TEST_DATA_FOLDER.resolve("duplicateVenueVenueBook.json");

    @Test
    public void toModelType_typicalVenuesFile_success() throws Exception {
        JsonSerializableVenueBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_VENUES_FILE,
                JsonSerializableVenueBook.class).get();
        VenueBook venueBookFromFile = dataFromFile.toModelType();
        VenueBook typicalVenuesVenueBook = getTypicalVenueBook();
        assertEquals(venueBookFromFile, typicalVenuesVenueBook);
    }

    @Test
    public void toModelType_invalidVenueFile_throwsIllegalValueException() throws Exception {
        JsonSerializableVenueBook dataFromFile = JsonUtil.readJsonFile(INVALID_VENUE_FILE,
                JsonSerializableVenueBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateVenues_throwsIllegalValueException() throws Exception {
        JsonSerializableVenueBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_VENUE_FILE,
                JsonSerializableVenueBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableVenueBook.MESSAGE_DUPLICATE_VENUE,
                dataFromFile::toModelType);
    }
}
