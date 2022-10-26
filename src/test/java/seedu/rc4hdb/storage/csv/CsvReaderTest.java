package seedu.rc4hdb.storage.csv;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.rc4hdb.testutil.Assert.assertThrows;
import static seedu.rc4hdb.testutil.TypicalResidents.ALICE;
import static seedu.rc4hdb.testutil.TypicalResidents.BENSON;
import static seedu.rc4hdb.testutil.TypicalResidents.CARL;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.rc4hdb.commons.exceptions.DataConversionException;
import seedu.rc4hdb.model.ReadOnlyResidentBook;
import seedu.rc4hdb.model.ResidentBook;
import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.resident.fields.Email;
import seedu.rc4hdb.model.resident.fields.Gender;
import seedu.rc4hdb.model.resident.fields.House;
import seedu.rc4hdb.model.resident.fields.MatricNumber;
import seedu.rc4hdb.model.resident.fields.Name;
import seedu.rc4hdb.model.resident.fields.Phone;
import seedu.rc4hdb.model.resident.fields.Room;
import seedu.rc4hdb.model.resident.fields.Tag;
import seedu.rc4hdb.storage.csv.exceptions.InvalidCsvFileFormatException;
import seedu.rc4hdb.testutil.ResidentBuilder;

/**
 * Unit tests for {@link CsvReader}.
 */
public class CsvReaderTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "CsvReaderTest");

    @TempDir
    public Path testFolder;

    private final CsvReader csvReader = new CsvReader();

    @Test
    public void readCsvFile_csvFileDoesNotExist_returnEmptyOptional() throws Exception {
        Path csvFilePath = testFolder.resolve("doesNotExist.csv");
        assertEquals(Optional.empty(), csvReader.readCsvFile(csvFilePath));
    }

    @Test
    public void readCsvFile_validCsvFileSingleRow_returnOptionalReadOnlyResidentBook() throws Exception {
        Path csvFilePath = TEST_DATA_FOLDER.resolve("validSingleRow.csv");
        ResidentBook expectedResidentBook = new ResidentBook();
        expectedResidentBook.addResident(ALICE);
        ReadOnlyResidentBook actualResidentBook = csvReader.readCsvFile(csvFilePath).get();

        assertEquals(expectedResidentBook, actualResidentBook);
    }

    @Test
    public void readCsvFile_validCsvFileWithBom_returnOptionalReadOnlyResidentBook() throws Exception {
        Path csvFilePath = TEST_DATA_FOLDER.resolve("validCsvWithBom.csv");
        ResidentBook expectedResidentBook = new ResidentBook();
        expectedResidentBook.addResident(ALICE);
        ReadOnlyResidentBook actualResidentBook = csvReader.readCsvFile(csvFilePath).get();

        assertEquals(expectedResidentBook, actualResidentBook);
    }

    @Test
    public void readCsvFile_validCsvFileWithMultipleResidents_returnOptionalReadOnlyResidentBook() throws Exception {
        Path csvFilePath = TEST_DATA_FOLDER.resolve("validMultiResident.csv");
        ResidentBook expectedResidentBook = new ResidentBook();
        expectedResidentBook.setResidents(List.of(ALICE, BENSON, CARL));
        ReadOnlyResidentBook actualResidentBook = csvReader.readCsvFile(csvFilePath).get();

        assertEquals(expectedResidentBook, actualResidentBook);
    }

    @Test
    public void readCsvFile_validCsvFileWithMultipleTag_returnOptionalReadOnlyResidentBook() throws Exception {
        Path csvFilePath = TEST_DATA_FOLDER.resolve("validMultiTag.csv");
        ResidentBook expectedResidentBook = new ResidentBook();
        Resident aliceMoreTag = new ResidentBuilder(ALICE).withTags("friends", "captain", "helper").build();
        expectedResidentBook.addResident(aliceMoreTag);
        ReadOnlyResidentBook actualResidentBook = csvReader.readCsvFile(csvFilePath).get();

        assertEquals(expectedResidentBook, actualResidentBook);
    }

    @Test
    public void readCsvFile_invalidCsvFileFormat_throwsDataConversionException() {
        Path csvFilePath = TEST_DATA_FOLDER.resolve("invalidCsvFormat.csv");
        assertThrows(DataConversionException.class, InvalidCsvFileFormatException.DEFAULT_ERROR_MESSAGE, ()
                -> csvReader.readCsvFile(csvFilePath));
    }

    @Test
    public void readCsvFile_csvWithTooManyCommas_throwsDataConversionException() {
        Path csvFilePath = TEST_DATA_FOLDER.resolve("tooManyCommas.csv");
        assertThrows(DataConversionException.class, InvalidCsvFileFormatException.DEFAULT_ERROR_MESSAGE, ()
                -> csvReader.readCsvFile(csvFilePath));
    }

    @Test
    public void readCsvFile_csvWithInvalidName_throwsDataConversionException() {
        Path csvFilePath = TEST_DATA_FOLDER.resolve("invalidName.csv");
        assertThrows(DataConversionException.class, Name.MESSAGE_CONSTRAINTS, ()
                -> csvReader.readCsvFile(csvFilePath));
    }

    @Test
    public void readCsvFile_csvWithInvalidPhone_throwsDataConversionException() {
        Path csvFilePath = TEST_DATA_FOLDER.resolve("invalidPhone.csv");
        assertThrows(DataConversionException.class, Phone.MESSAGE_CONSTRAINTS, ()
                -> csvReader.readCsvFile(csvFilePath));
    }

    @Test
    public void readCsvFile_csvWithInvalidEmail_throwsDataConversionException() {
        Path csvFilePath = TEST_DATA_FOLDER.resolve("invalidEmail.csv");
        assertThrows(DataConversionException.class, Email.MESSAGE_CONSTRAINTS, ()
                -> csvReader.readCsvFile(csvFilePath));
    }

    @Test
    public void readCsvFile_csvWithInvalidRoom_throwsDataConversionException() {
        Path csvFilePath = TEST_DATA_FOLDER.resolve("invalidRoom.csv");
        assertThrows(DataConversionException.class, Room.MESSAGE_CONSTRAINTS, ()
                -> csvReader.readCsvFile(csvFilePath));
    }

    @Test
    public void readCsvFile_csvWithInvalidGender_throwsDataConversionException() {
        Path csvFilePath = TEST_DATA_FOLDER.resolve("invalidGender.csv");
        assertThrows(DataConversionException.class, Gender.MESSAGE_CONSTRAINTS, ()
                -> csvReader.readCsvFile(csvFilePath));
    }

    @Test
    public void readCsvFile_csvWithInvalidHouse_throwsDataConversionException() {
        Path csvFilePath = TEST_DATA_FOLDER.resolve("invalidHouse.csv");
        assertThrows(DataConversionException.class, House.MESSAGE_CONSTRAINTS, ()
                -> csvReader.readCsvFile(csvFilePath));
    }

    @Test
    public void readCsvFile_csvWithInvalidMatricNumber_throwsDataConversionException() {
        Path csvFilePath = TEST_DATA_FOLDER.resolve("invalidMatricNumber.csv");
        assertThrows(DataConversionException.class, MatricNumber.MESSAGE_CONSTRAINTS, ()
                -> csvReader.readCsvFile(csvFilePath));
    }

    @Test
    public void readCsvFile_csvWithInvalidTags_throwsDataConversionException() {
        Path csvFilePath = TEST_DATA_FOLDER.resolve("invalidTags.csv");
        assertThrows(DataConversionException.class, Tag.MESSAGE_CONSTRAINTS, ()
                -> csvReader.readCsvFile(csvFilePath));
    }

}
