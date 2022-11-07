package seedu.codeConnect.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.codeConnect.commons.exceptions.DataConversionException;
import seedu.codeConnect.model.ReadOnlyAddressBook;
import seedu.codeConnect.model.ReadOnlyTaskList;
import seedu.codeConnect.model.ReadOnlyUserPrefs;
import seedu.codeConnect.model.UserPrefs;

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
