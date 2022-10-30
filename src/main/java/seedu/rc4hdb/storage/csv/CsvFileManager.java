package seedu.rc4hdb.storage.csv;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.rc4hdb.commons.exceptions.DataConversionException;
import seedu.rc4hdb.model.ReadOnlyResidentBook;

/**
 * CSV file storage manager.
 */
public class CsvFileManager {

    private CsvReader csvReader;

    public CsvFileManager() {
        this.csvReader = new CsvReader();
    }

    public CsvFileManager(CsvReader csvReader) {
        this.csvReader = csvReader;
    }

    public Optional<ReadOnlyResidentBook> readCsvFile(Path filePath) throws IOException, DataConversionException {
        return csvReader.readCsvFile(filePath);
    }

}
