package seedu.rc4hdb.storage.csv;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.rc4hdb.commons.core.LogsCenter;
import seedu.rc4hdb.commons.exceptions.DataConversionException;
import seedu.rc4hdb.commons.util.FileUtil;
import seedu.rc4hdb.logic.parser.ParserUtil;
import seedu.rc4hdb.logic.parser.exceptions.ParseException;
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

/**
 * Reads CSV files into {@code ResidentBook} format.
 */
public class CsvReader {

    private static final Logger logger = LogsCenter.getLogger(CsvReader.class);

    private static final Pattern CSV_FORMAT = Pattern.compile("(?<Name>[^,]+),(?<Phone>[^,]+),(?<Email>[^,]+),"
            + "(?<Room>[^,]+),(?<Gender>[^,]+),(?<House>[^,]+),(?<Matric>[^,]+),(?<Tags>[^,]+)");

    /**
     * Reads resident data from CSV file into ResidentBook format.
     * @param filePath the file path of the CSV file to be read.
     * @return a resident book created from resident data from the CSV file to be read.
     * @throws IOException if something unexpected occurs while reading the file.
     * @throws DataConversionException if the CSV file is in an invalid format.
     */
    public Optional<ReadOnlyResidentBook> readCsvFile(Path filePath) throws IOException, DataConversionException {
        requireNonNull(filePath);
        assert(isCsvFilePath(filePath));
        if (!FileUtil.isFileExists(filePath)) {
            logger.warning("File does not exist: " + filePath);
            return Optional.empty();
        }

        String csvAsString = cleanBom(filePath);
        List<String> lines = splitLines(csvAsString);
        ResidentBook residentBook = new ResidentBook();

        for (String line : lines) {
            residentBook.addResident(readLine(line));
        }

        return Optional.of(residentBook);
    }

    private boolean isCsvFilePath(Path filePath) {
        return filePath.toString().endsWith(".csv");
    }

    /**
     * Splits the CSV string by lines and return as a list of lines.
     */
    private List<String> splitLines(String csvAsString) {
        Scanner sc = new Scanner(csvAsString);
        List<String> lines = new ArrayList<>();
        while (sc.hasNext()) {
            lines.add(sc.nextLine());
        }
        return lines;
    }

    /**
     * Cleans up the BOM that exists in some UTF-8 files. The BOM is a hidden character that appears in UTF-8 encoded
     * files, which signals that the file is encoded in UTF-8.
     * Code was adapted from https://mkyong.com/java/java-how-to-add-and-remove-bom-from-utf-8-file/.
     */
    private String cleanBom(Path filePath) throws IOException {
        byte[] bom = new byte[] {-17, -69, -65};

        String csvAsString = FileUtil.readFromFile(filePath);
        byte[] csvByteArray = csvAsString.getBytes(StandardCharsets.UTF_8);

        if (bom[0] == csvByteArray[0]
                && bom[1] == csvByteArray[1]
                && bom[2] == csvByteArray[2]) {
            return csvAsString.substring(1);
        }
        return csvAsString;
    }

    /**
     * Parses resident data from one line of the CSV file.
     * @param line the line of data from the CSV file.
     * @return a {@code Resident} corresponding to the line of data from the CSV file.
     * @throws DataConversionException if the line of resident data is in an invalid format.
     */
    private Resident readLine(String line) throws DataConversionException {
        Matcher matcher = CSV_FORMAT.matcher(line);
        if (!matcher.matches()) {
            throw new DataConversionException(new InvalidCsvFileFormatException());
        }

        try {
            final Name name = ParserUtil.parseName(matcher.group(Name.IDENTIFIER));
            final Phone phone = ParserUtil.parsePhone(matcher.group(Phone.IDENTIFIER));
            final Email email = ParserUtil.parseEmail(matcher.group(Email.IDENTIFIER));
            final Room room = ParserUtil.parseRoom(matcher.group(Room.IDENTIFIER));
            final Gender gender = ParserUtil.parseGender(matcher.group(Gender.IDENTIFIER));
            final House house = ParserUtil.parseHouse(matcher.group(House.IDENTIFIER));
            final MatricNumber matricNumber = ParserUtil.parseMatricNumber(matcher.group(MatricNumber.IDENTIFIER));
            final Set<Tag> tags = parseTags(matcher.group(Tag.IDENTIFIER));
            return new Resident(name, phone, email, room, gender, house, matricNumber, tags);
        } catch (ParseException e) {
            throw new DataConversionException(e);
        }
    }

    /**
     * Parses the tagsString, based off the format specified in the CSV Format section of the UG.
     */
    private Set<Tag> parseTags(String tagsString) throws ParseException {
        List<String> tagStrings = new ArrayList<>(List.of(tagsString.split(" ")));
        tagStrings.removeIf((tagString) -> tagString.equalsIgnoreCase("nil"));
        return ParserUtil.parseTags(tagStrings);
    }

}
