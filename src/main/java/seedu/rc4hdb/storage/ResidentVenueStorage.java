package seedu.rc4hdb.storage;

import seedu.rc4hdb.storage.residentbook.ResidentBookStorage;
import seedu.rc4hdb.storage.venuebook.VenueBookStorage;

/**
 * Represents a storage for {@link seedu.rc4hdb.model.ResidentBook} and {@link seedu.rc4hdb.model.VenueBook}.
 */
public interface ResidentVenueStorage extends ResidentBookStorage, VenueBookStorage {
}
