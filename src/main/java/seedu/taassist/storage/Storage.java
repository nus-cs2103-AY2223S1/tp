package seedu.taassist.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.taassist.commons.exceptions.DataConversionException;
import seedu.taassist.model.ReadOnlyTaAssist;
import seedu.taassist.model.ReadOnlyUserPrefs;
import seedu.taassist.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends TaAssistStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyTaAssist> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyTaAssist addressBook) throws IOException;

}
