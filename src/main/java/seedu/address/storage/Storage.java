package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, UserPrefsStorage {

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

    /**
     * Exports the given {@link ObservableList} as a JSON file to the storage at filePathString.
     * @param displayedList cannot be null.
     * @param filePathString cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    static void exportDisplayedList(ObservableList<Person> displayedList, String filePathString) throws IOException {
        requireNonNull(displayedList);
        requireNonNull(filePathString);

        Path filePath = Paths.get(filePathString);
        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(displayedList.stream().map(JsonAdaptedPerson::new)
                .collect(Collectors.toList()), filePath);
    }
}
