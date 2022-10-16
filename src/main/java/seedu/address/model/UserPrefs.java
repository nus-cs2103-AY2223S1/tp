package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.lang.reflect.Array;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.core.Config;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.storage.JsonUserPrefsStorage;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private static final String DEFAULT_ADDRESS_BOOK_NAME = "addressbook";
    private Path addressBookFilePath = Paths.get("data" , "addressbook.json");
    private static final int MAX_ADDRESS_BOOK_LIMIT = 5;
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
        Path[] asdf = allAddressBookFilePath.toArray(new Path[allAddressBookFilePath.size()]);
        for (Path a : asdf) {
            System.out.println(a);
        }
        return allAddressBookFilePath.toArray(new Path[allAddressBookFilePath.size()]);
    }

    public void setAllAddressBookFilePath(Path[] allAddressBookFilePath) {
        requireNonNull(allAddressBookFilePath);
        this.allAddressBookFilePath = new ArrayList<>(List.of(allAddressBookFilePath));
    }

    public boolean addAddressBook() {
        if (allAddressBookFilePath.size() == MAX_ADDRESS_BOOK_LIMIT) {
            return false;
        } else {
            String newBookName = DEFAULT_ADDRESS_BOOK_NAME + allAddressBookFilePath.size() + ".json";
            Path newBook = Paths.get("data" , newBookName);
            allAddressBookFilePath.add(newBook);
            return true;
        }
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
