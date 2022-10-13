package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyMeetingList;

/**
 * Represents a storage for {@link seedu.address.model.meeting.Meeting}.
 */
public interface MeetingListStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getMeetingListFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyMeetingList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyMeetingList> readMeetingList() throws DataConversionException, IOException;

    /**
     * @see #getMeetingListFilePath()
     */
    Optional<ReadOnlyMeetingList> readMeetingList(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyMeetingList} to the storage.
     * @param meetingList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveMeetingList(ReadOnlyMeetingList meetingList) throws IOException;

    /**
     * @see #saveMeetingList(ReadOnlyMeetingList)
     */
    void saveMeetingList(ReadOnlyMeetingList meetingList, Path filePath) throws IOException;

}
