package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * A utility class to help with building Appointment objects.
 */
public class ModelBuilder {
    public static final AddressBook addressBook = new AddressBook();
    public static final UserPrefs userPrefs = new UserPrefs();
    public static final CommandHistory commandHistory = new CommandHistory();

    public Model build() {
        return new ModelManager(addressBook, userPrefs, commandHistory);
    }
}
