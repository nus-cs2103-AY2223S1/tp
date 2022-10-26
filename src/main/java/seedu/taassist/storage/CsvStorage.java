package seedu.taassist.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Represents a storage for CSV files.
 */
public interface CsvStorage {

    /**
     * Saves the given list of strings as a CSV file with the given fileName at the default path.
     * @param fileName Name of CSV file to save as.
     * @param fileData Data to be saved in the CSV file.
     * @return Path of the file successfully saved.
     *
     * @throws IOException If there is an error when saving the data.
     */
    Path saveAsCsvFile(String fileName, List<List<String>> fileData) throws IOException;
}
