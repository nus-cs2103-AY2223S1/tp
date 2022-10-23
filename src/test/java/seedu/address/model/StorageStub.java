package seedu.address.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.storage.Storage;

/**
 * A default Storage stub that have all of its methods failing.
 */
public class StorageStub implements Storage {

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getUserPrefsFilePath() {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) {
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

    @Override
    public Path getAddressBookFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getBaseDirectoryPath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getRandomImagePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean isPathInBaseDirectory(Path path) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean isValidImage(Path path) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public BufferedImage getImage(Path path) throws IOException {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void saveImage(BufferedImage image, Path path) {
        throw new AssertionError("This method should not be called.");
    }
}
