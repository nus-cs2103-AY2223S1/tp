package seedu.rc4hdb.storage.csv;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.rc4hdb.testutil.Assert.assertThrows;
import static seedu.rc4hdb.testutil.TypicalResidents.ALICE;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.rc4hdb.commons.exceptions.DataConversionException;
import seedu.rc4hdb.model.ReadOnlyResidentBook;
import seedu.rc4hdb.testutil.ResidentBookBuilder;

/**
 * Unit tests for {@link CsvFileManager}.
 */
public class CsvFileManagerTest {

    public static final Path VALID_FILE_PATH = Path.of("valid");
    public static final ReadOnlyResidentBook VALID_CSV_READ_RESULT =
            new ResidentBookBuilder().withResident(ALICE).build();

    public static final Path INVALID_FILE_PATH = Path.of("invalid");

    @Test
    public void readCsvFile_validCsvFile_returnOptionalReadOnlyResidentBook() throws Exception {
        CsvFileManager csvFileManager = new CsvFileManager(new CsvReaderStub());
        Optional<ReadOnlyResidentBook> actualResult = csvFileManager.readCsvFile(VALID_FILE_PATH);
        assertEquals(Optional.of(VALID_CSV_READ_RESULT), actualResult);
    }

    @Test
    public void readCsvFile_csvFileDoesNotExist_returnEmptyOptional() throws Exception {
        CsvFileManager csvFileManager = new CsvFileManager(new CsvReaderStub());
        Optional<ReadOnlyResidentBook> actualResult = csvFileManager.readCsvFile(INVALID_FILE_PATH);
        assertEquals(Optional.empty(), actualResult);
    }

    @Test
    public void readCsvFile_invalidCsvFileFormat_throwDataConversionException() {
        CsvFileManager csvFileManager = new CsvFileManager(new CsvReaderStubCsvFileInvalidFormat());
        assertThrows(DataConversionException.class, "", () -> csvFileManager.readCsvFile(INVALID_FILE_PATH));
    }

    @Test
    public void readCsvFile_csvReaderThrowsIoException_throwIoException() {
        CsvFileManager csvFileManager = new CsvFileManager(new CsvReaderStubIoException());
        assertThrows(IOException.class, new IOException().getMessage(), ()
                -> csvFileManager.readCsvFile(INVALID_FILE_PATH));
    }

    /**
     * A CsvReaderStub that represents the {@code CsvReader} when it only has one available valid CSV file to read in
     * the data file.
     */
    private static class CsvReaderStub extends CsvReader {
        @Override
        public Optional<ReadOnlyResidentBook> readCsvFile(Path filePath) {
            if (filePath == VALID_FILE_PATH) {
                return Optional.of(VALID_CSV_READ_RESULT);
            }
            return Optional.empty();
        }
    }

    /**
     * A CsvReaderStub that reads a CSV file with an invalid format.
     */
    private static class CsvReaderStubCsvFileInvalidFormat extends CsvReader {
        @Override
        public Optional<ReadOnlyResidentBook> readCsvFile(Path filePath) throws DataConversionException {
            throw new DataConversionException(new Exception());
        }
    }

    /**
     * A CsvReaderStub that encounters an IOException while reading a CSV file.
     */
    private static class CsvReaderStubIoException extends CsvReader {
        @Override
        public Optional<ReadOnlyResidentBook> readCsvFile(Path filePath) throws IOException {
            throw new IOException();
        }
    }
}
