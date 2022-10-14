package seedu.address.model;

import java.nio.file.Path;
import java.util.Optional;

import seedu.address.storage.UserPrefsStorage;

/**
 * A default UserPrefsStorage stub that have all of its methods failing.
 */
public class UserPrefsStorageStub implements UserPrefsStorage {
    @Override
    public Path getUserPrefsFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }
}
