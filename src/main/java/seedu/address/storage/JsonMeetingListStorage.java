package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyMeetingList;

/**
 * A class to access Meeting data stored as a json file on the hard disk.
 */
public class JsonMeetingListStorage implements MeetingListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonMeetingListStorage.class);

    private Path filePath;

    public JsonMeetingListStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getMeetingListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyMeetingList> readMeetingList() throws DataConversionException {
        return readMeetingList(filePath);
    }

    /**
     * Similar to {@link #readMeetingList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyMeetingList> readMeetingList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableMeetingList> jsonMeetingList = JsonUtil.readJsonFile(
                filePath, JsonSerializableMeetingList.class);
        if (!jsonMeetingList.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonMeetingList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void saveMeetingList(ReadOnlyMeetingList meetingList) throws IOException {
        saveMeetingList(meetingList, filePath);
    }

    /**
     * Similar to {@link #saveMeetingList(ReadOnlyMeetingList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveMeetingList(ReadOnlyMeetingList meetingList, Path filePath) throws IOException {
        requireNonNull(meetingList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableMeetingList(meetingList), filePath);
    }

}
