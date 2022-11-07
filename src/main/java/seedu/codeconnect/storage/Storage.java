package seedu.codeconnect.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.codeconnect.commons.exceptions.DataConversionException;
import seedu.codeconnect.model.ReadOnlyAddressBook;
import seedu.codeconnect.model.ReadOnlyTaskList;
import seedu.codeconnect.model.ReadOnlyUserPrefs;
import seedu.codeconnect.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, TaskListStorage, UserPrefsStorage {

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

    @Override
    Path getTaskListFilePath();

    @Override
    Optional<ReadOnlyTaskList> readTaskList() throws DataConversionException, IOException;

    @Override
    void saveTaskList(ReadOnlyTaskList taskList) throws IOException;

}
