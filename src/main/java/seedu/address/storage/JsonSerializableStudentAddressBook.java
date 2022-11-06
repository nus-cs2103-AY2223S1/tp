package seedu.address.storage;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.student.Student;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "studentaddressbook")
class JsonSerializableStudentAddressBook {

    public static final String MESSAGE_DUPLICATE_STUDENT = "Students list contains duplicate student(s).";

    private final List<JsonAdaptedStudent> students = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given students.
     */
    @JsonCreator
    public JsonSerializableStudentAddressBook(@JsonProperty("students") List<JsonAdaptedStudent> students) {
        this.students.addAll(students);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableStudentAddressBook(ReadOnlyAddressBook source) {
        students.addAll(source.getStudentList().sorted((first, second) -> {
            HashMap<Integer, Object> a = first.getUniqueId();
            HashMap<Integer, Object> b = second.getUniqueId();
            Instant t = (Instant) a.get(0);
            int result = t.compareTo((Instant) b.get(0));
            if (result == 0) {
                return ((int) a.get(1)) - ((int) b.get(1));
            }
            return result;
        })
                .stream()
                .map(JsonAdaptedStudent::new)
                .collect(Collectors.toList()));
    }

    /**
     * Returns the list of JsonAdaptedStudents.
     */
    public List<Student> getStudentsList() throws IllegalValueException {
        List<Student> studentList = new ArrayList<>();
        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();
            studentList.add(student);
        }
        return studentList;
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();
            if (addressBook.hasPerson(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            addressBook.addPerson(student);
        }
        return addressBook;
    }
}
