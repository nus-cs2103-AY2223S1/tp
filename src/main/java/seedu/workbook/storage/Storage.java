package seedu.workbook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.workbook.commons.exceptions.DataConversionException;
import seedu.workbook.model.ReadOnlyUserPrefs;
import seedu.workbook.model.ReadOnlyWorkBook;
import seedu.workbook.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends WorkBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getWorkBookFilePath();

    @Override
    Optional<ReadOnlyWorkBook> readWorkBook() throws DataConversionException, IOException;

    @Override
    void saveWorkBook(ReadOnlyWorkBook workBook) throws IOException;

}
