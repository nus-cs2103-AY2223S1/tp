package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTaskList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, ArchivedTaskListStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Path getArchivedTaskListFilePath();

    @Override
    Optional<ReadOnlyTaskList> readAddressBook() throws DataConversionException, IOException;

    @Override
    Optional<ReadOnlyTaskList> readArchivedTaskList() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyTaskList addressBook) throws IOException;

    @Override
    void saveArchivedTaskList(ReadOnlyTaskList addressBook) throws IOException;
}
