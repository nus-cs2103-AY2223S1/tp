package seedu.rc4hdb.storage.venuebook;

import java.nio.file.Path;
import java.util.logging.Logger;

import seedu.rc4hdb.commons.core.LogsCenter;
import seedu.rc4hdb.ui.ObservableItem;

/**
 * A class to access VenueBook data stored as a json file on the hard disk.
 */
public class JsonVenueBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonVenueBookStorage.class);

    // Todo make sure this observable item is the same as in json resident book storage
    private ObservableItem<Path> filePath;

    public JsonVenueBookStorage(Path filePath) {
        this.filePath = new ObservableItem<>(filePath);
    }





}
