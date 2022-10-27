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

    private final Path tutorFilePath;
    private final Path studentFilePath;
    private final Path tuitionClassFilePath;

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
     */
    @Override
    public Optional<ReadOnlyAddressBook> readAllAddressBook() {
        AddressBook addressBook = new AddressBook();
        Optional<ReadOnlyAddressBook> studentAddressBook = Optional.empty();
        Optional<ReadOnlyAddressBook> tutorAddressBook = Optional.empty();
        Optional<ReadOnlyAddressBook> tuitionClassAddressBook = Optional.empty();

        try {
            studentAddressBook = readStudentAddressBook(studentFilePath);
        } catch (DataConversionException e) {
            logger.info(String.format("An error occurred while reading %s", studentFilePath));
        }
        try {
            tutorAddressBook = readTutorAddressBook(tutorFilePath);
        } catch (DataConversionException e) {
            logger.info(String.format("An error occurred while reading %s", tutorFilePath));
        }
        try {
            tuitionClassAddressBook = readTuitionClassAddressBook(tuitionClassFilePath);
        } catch (DataConversionException e) {
            logger.info(String.format("An error occurred while reading %s", tuitionClassFilePath));
        }

        loadStudentAddressBook(addressBook, studentAddressBook);
        loadTutorAddressBook(addressBook, tutorAddressBook);
        loadTuitionClassesAddressBook(addressBook, tuitionClassAddressBook);
        return Optional.of(addressBook);
    }

    /**
     * Loads a student address book into a general address book.
     * @param main The general address book to load the student entries in.
     * @param studentAddressBook The student address book to be loaded.
     */
    public void loadStudentAddressBook(AddressBook main, Optional<ReadOnlyAddressBook> studentAddressBook) {
        if (studentAddressBook.isPresent()) {
            List<Student> studentList = studentAddressBook.get().getStudentList();
            for (Student s : studentList) {
                main.addPerson(s);
            }
        } else {
            try {
                FileUtil.createFile(studentFilePath);
                saveAddressBook(new AddressBook(), AddressBookCategories.STUDENTS);
                logger.info("Empty studentaddressbook.json created");
            } catch (IOException e) {
                logger.info(String.format("Failed to create missing file: %s", studentFilePath));
            }
        }
    }

    /**
     * Loads a tutor address book into a general address book.
     * @param main The general address book to load the tutor entries in.
     * @param tutorAddressBook The tutor address book to be loaded.
     */
    public void loadTutorAddressBook(AddressBook main, Optional<ReadOnlyAddressBook> tutorAddressBook) {
        if (tutorAddressBook.isPresent()) {
            List<Tutor> tutorList = tutorAddressBook.get().getTutorList();
            for (Tutor t : tutorList) {
                main.addPerson(t);
            }
        } else {
            try {
                FileUtil.createFile(tutorFilePath);
                saveAddressBook(new AddressBook(), AddressBookCategories.TUTORS);
                logger.info("Empty tutoraddressbook.json created");
            } catch (IOException e) {
                logger.info(String.format("Failed to create missing file: %s", tutorFilePath));
            }
        }
    }

    /**
     * Loads a tuition class address book into a general address book.
     * @param main The general address book to load the tuition class entries in.
     * @param tuitionClassAddressBook The tuition class address book to be loaded.
     */
    public void loadTuitionClassesAddressBook(AddressBook main,
                                               Optional<ReadOnlyAddressBook> tuitionClassAddressBook) {
        if (tuitionClassAddressBook.isPresent()) {
            List<TuitionClass> tuitionClassList = tuitionClassAddressBook.get().getTuitionClassList();
            for (TuitionClass tc : tuitionClassList) {
                main.addTuitionClass(tc);
            }
        } else {
            try {
                FileUtil.createFile(tuitionClassFilePath);
                saveAddressBook(new AddressBook(), AddressBookCategories.TUITIONCLASSES);
                logger.info("Empty tuitionclassaddressbook.json created");
            } catch (IOException e) {
                logger.info(String.format("Failed to create missing file: %s", tuitionClassFilePath));
            }
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
        if (jsonAddressBook.isEmpty()) {
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
        if (jsonAddressBook.isEmpty()) {
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
        if (jsonAddressBook.isEmpty()) {
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
