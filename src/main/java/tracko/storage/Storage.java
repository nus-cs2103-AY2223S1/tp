package tracko.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import tracko.commons.exceptions.DataConversionException;
import tracko.model.ReadOnlyAddressBook;
import tracko.model.ReadOnlyTrackO;
import tracko.model.ReadOnlyUserPrefs;
import tracko.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, TrackOStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

    // TrackO methods =================================================================================

    @Override
    Path getOrdersFilePath();

    @Override
    Optional<ReadOnlyTrackO> readTrackO() throws DataConversionException, IOException;

    @Override
    void saveTrackO(ReadOnlyTrackO trackO) throws IOException;

}
