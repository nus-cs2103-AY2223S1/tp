package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.tutor.Tutor;
import seedu.address.model.tuitionclass.TuitionClass;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonAddressBookStorage implements AddressBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonAddressBookStorage.class);

    private Path tutorFilePath;
    private Path studentFilePath;
    private Path tuitionClassFilePath;

    /**
     * Constructor for JsonAddressBookStorage.
     *
     * @param tutorFilePath File path for tutor address book.
     * @param studentFilePath File path for student address book.
     * @param tuitionClassFilePath File path for tuition class address book.
     */
    public JsonAddressBookStorage(Path tutorFilePath, Path studentFilePath, Path tuitionClassFilePath) {
        this.tutorFilePath = tutorFilePath;
        this.studentFilePath = studentFilePath;
        this.tuitionClassFilePath = tuitionClassFilePath;
    }

    @Override
    public Path getAddressBookFilePath(AddressBookCategories cat) {
        switch (cat) {
        case TUTORS:
            return tutorFilePath;
        case STUDENTS:
            return studentFilePath;
        case TUITIONCLASSES:
            return tuitionClassFilePath;
        default:
            return null; // not sure what to put here cos there'll be an error without this line.
        }
    }

    /**
     * Returns a ReadOnlyAddressBook containing all Tutors, Students and TuitionClasses.
     *   Returns {@code Optional.empty()} if no save data at all.
     *
     * @throws DataConversionException if the file is not in the correct format.
     */
    @Override
    public Optional<ReadOnlyAddressBook> readAllAddressBook()
            throws DataConversionException, IllegalValueException, IOException {
        AddressBook addressBook = new AddressBook();
        Optional<JsonSerializableTutorAddressBook> jsonTutorAddressBook = JsonUtil.readJsonFile(
                tutorFilePath, JsonSerializableTutorAddressBook.class);
        Optional<JsonSerializableStudentAddressBook> jsonStudentAddressBook = JsonUtil.readJsonFile(
                studentFilePath, JsonSerializableStudentAddressBook.class);
        Optional<JsonSerializableTuitionClassAddressBook> jsonTuitionClassAddressBook = JsonUtil.readJsonFile(
                tuitionClassFilePath, JsonSerializableTuitionClassAddressBook.class);

        int isNew = 3;
        if (jsonTutorAddressBook.isPresent()) {
            List<Tutor> tutorList = jsonTutorAddressBook.get().getTutorsList();
            for (Tutor t : tutorList) {
                addressBook.addPerson(t);
            }
        } else {
            isNew--;
            FileUtil.createFile(tutorFilePath);
            logger.info("Empty tutoraddressbook.json created");
        }
        if (jsonStudentAddressBook.isPresent()) {
            List<Student> studentList = jsonStudentAddressBook.get().getStudentsList();
            for (Student s : studentList) {
                addressBook.addPerson(s);
            }
        } else {
            isNew--;
            FileUtil.createFile(studentFilePath);
            logger.info("Empty studentaddressbook.json created");
        }
        if (jsonTuitionClassAddressBook.isPresent()) {
            List<TuitionClass> tuitionClassList = jsonTuitionClassAddressBook.get().getTuitionClassesList();
            for (TuitionClass tc : tuitionClassList) {
                addressBook.addTuitionClass(tc);
            }
        } else {
            isNew--;
            FileUtil.createFile(tuitionClassFilePath);
            logger.info("Empty tuitionclassaddressbook.json created");
        }

        if (isNew == 0) {
            return Optional.empty();
        } else {
            return Optional.of(addressBook);
        }
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(AddressBookCategories cat) throws DataConversionException {
        switch (cat) {
        case TUTORS:
            return readTutorAddressBook(tutorFilePath);
        case STUDENTS:
            return readStudentAddressBook(studentFilePath);
        case TUITIONCLASSES:
            return readTuitionClassAddressBook(tuitionClassFilePath);
        default:
            return Optional.empty();
        }
    }

    /**
     * Similar to {@link #readAddressBook(AddressBookCategories)}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    @Override
    public Optional<ReadOnlyAddressBook> readTutorAddressBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTutorAddressBook> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableTutorAddressBook.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    /**
     * Similar to {@link #readAddressBook(AddressBookCategories)}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    @Override
    public Optional<ReadOnlyAddressBook> readStudentAddressBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableStudentAddressBook> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableStudentAddressBook.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    /**
     * Similar to {@link #readAddressBook(AddressBookCategories)}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    @Override
    public Optional<ReadOnlyAddressBook> readTuitionClassAddressBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTuitionClassAddressBook> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableTuitionClassAddressBook.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveAllAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        requireNonNull(tutorFilePath);
        requireNonNull(studentFilePath);
        requireNonNull(tuitionClassFilePath);

        JsonUtil.saveJsonFile(new JsonSerializableTutorAddressBook(addressBook), tutorFilePath);
        JsonUtil.saveJsonFile(new JsonSerializableStudentAddressBook(addressBook), studentFilePath);
        JsonUtil.saveJsonFile(new JsonSerializableTuitionClassAddressBook(addressBook), tuitionClassFilePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, AddressBookCategories cat) throws IOException {
        Path filePath = null;
        switch (cat) {
        case TUTORS:
            filePath = tutorFilePath;
            break;
        case STUDENTS:
            filePath = studentFilePath;
            break;
        case TUITIONCLASSES:
            filePath = tuitionClassFilePath;
            break;
        default: { }
        }
        saveAddressBook(addressBook, filePath, cat);
    }

    /**
     * Similar to {@link #saveAddressBook(ReadOnlyAddressBook, AddressBookCategories)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath, AddressBookCategories cat)
            throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);

        switch (cat) {
        case TUTORS:
            JsonUtil.saveJsonFile(new JsonSerializableTutorAddressBook(addressBook), filePath);
            break;
        case STUDENTS:
            JsonUtil.saveJsonFile(new JsonSerializableStudentAddressBook(addressBook), filePath);
            break;
        case TUITIONCLASSES:
            JsonUtil.saveJsonFile(new JsonSerializableTuitionClassAddressBook(addressBook), filePath);
            break;
        default: { }
        }
    }
}
