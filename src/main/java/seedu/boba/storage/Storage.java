package seedu.boba.storage;

import seedu.boba.commons.exceptions.DataConversionException;
import seedu.boba.model.ReadOnlyBobaBot;
import seedu.boba.model.ReadOnlyUserPrefs;
import seedu.boba.model.UserPrefs;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

/**
 * API of the Storage component
 */
public interface Storage extends BobaBotStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getBobaBotFilePath();

    @Override
    Optional<ReadOnlyBobaBot> readBobaBot() throws DataConversionException, IOException;

    @Override
    void saveBobaBot(ReadOnlyBobaBot addressBook) throws IOException;

}
