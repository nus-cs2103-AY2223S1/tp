package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.reminder.ReadOnlyReminderList;

/**
 * Represents a storage for {@link seedu.address.model.reminder.ReminderList}.
 */
public interface ReminderListStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getReminderListFilePath();

    /**
     * Returns Reminders data as a {@link ReadOnlyReminderList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyReminderList> readReminderList() throws DataConversionException, IOException;

    /**
     * @see #getReminderListFilePath()
     */
    Optional<ReadOnlyReminderList> readReminderList(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyReminderList} to the storage.
     * @param reminderList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveReminderList(ReadOnlyReminderList reminderList) throws IOException;

    /**
     * @see #saveReminderList(ReadOnlyReminderList)
     */
    void saveReminderList(ReadOnlyReminderList reminderList, Path filePath) throws IOException;

}
