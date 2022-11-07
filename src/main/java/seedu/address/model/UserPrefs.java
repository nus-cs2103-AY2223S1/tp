package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private static final int MAX_ADDRESS_BOOK_LIMIT = 5;
    private static final String DEFAULT_ADDRESS_BOOK_NAME = "addressbook";
    private GuiSettings guiSettings = new GuiSettings();
    private int addressBookIndex = 0;
    private Path addressBookFilePath = Paths.get("data" , DEFAULT_ADDRESS_BOOK_NAME + ".json");
    private ArrayList<Path> allAddressBookFilePath = new ArrayList<Path>();

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {
        allAddressBookFilePath.add(addressBookFilePath);
    }

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setAddressBookFilePath(newUserPrefs.getAddressBookFilePath());
        setAllAddressBookFilePath(newUserPrefs.getAllAddressBookFilePath());
        setStoredIndex(newUserPrefs.getStoredIndex() % newUserPrefs.getAllAddressBookFilePath().length);
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getAddressBookFilePath() {
        return addressBookFilePath;
    }

    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        this.addressBookFilePath = addressBookFilePath;
    }

    public Path[] getAllAddressBookFilePath() {
        return allAddressBookFilePath.toArray(new Path[allAddressBookFilePath.size()]);
    }

    public void setAllAddressBookFilePath(Path[] allAddressBookFilePath) {
        requireNonNull(allAddressBookFilePath);
        this.allAddressBookFilePath = new ArrayList<>(List.of(allAddressBookFilePath));
    }

    /**
     * Adds a brand new Address Book to the data directory
     *
     * @return boolean value indicating {@code true} = Success or {@code false} = Limit reached
     */
    public boolean addAddressBook() {
        if (allAddressBookFilePath.size() == MAX_ADDRESS_BOOK_LIMIT) {
            return false;
        } else {
            String newBookName;
            if (allAddressBookFilePath.size() != 0) {
                newBookName = DEFAULT_ADDRESS_BOOK_NAME + System.currentTimeMillis() + ".json";
            } else {
                newBookName = DEFAULT_ADDRESS_BOOK_NAME + ".json";
            }
            Path newBook = Paths.get("data" , newBookName);
            allAddressBookFilePath.add(newBook);
            return true;
        }
    }

    /**
     * Renames the current address book
     */
    public void renameFile(String name) throws IOException {
        Path newAddressBookFilePath = Paths.get("data" , name + ".json");
        Files.move(this.addressBookFilePath, newAddressBookFilePath);
        this.allAddressBookFilePath.set(addressBookIndex, newAddressBookFilePath);
    }


    public Path getNextAddressBookPath() {
        incrementIndex();
        Path nextAddressBook = allAddressBookFilePath.get(addressBookIndex);
        setAddressBookFilePath(nextAddressBook);
        return nextAddressBook;
    }

    public int getStoredIndex() {
        return addressBookIndex;
    }

    public void setStoredIndex(int index) {
        addressBookIndex = index;
    }

    private void incrementIndex() {
        addressBookIndex = (addressBookIndex + 1) % allAddressBookFilePath.size();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { //this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs) other;

        return guiSettings.equals(o.guiSettings)
                && addressBookFilePath.equals(o.addressBookFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, addressBookFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + addressBookFilePath);
        return sb.toString();
    }

}
