package seedu.classify.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.classify.commons.exceptions.DataConversionException;
import seedu.classify.model.ReadOnlyStudentRecord;
import seedu.classify.model.ReadOnlyUserPrefs;
import seedu.classify.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends StudentRecordStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getStudentRecordFilePath();

    @Override
    Optional<ReadOnlyStudentRecord> readStudentRecord() throws DataConversionException, IOException;

    @Override
    void saveStudentRecord(ReadOnlyStudentRecord studentRecord) throws IOException;

}
