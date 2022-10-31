package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.question.Question;
import seedu.address.model.student.Student;
import seedu.address.model.tutorial.Tutorial;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s)y.";
    public static final String MESSAGE_DUPLICATE_QUESTION = "Questions list contains duplicate question(s).";
    public static final String MESSAGE_DUPLICATE_STUDENT = "Student list contains duplicate student(s).";
    public static final String MESSAGE_DUPLICATE_TUTORIAL = "Tutorials list contains duplicate tutorials(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedQuestion> questions = new ArrayList<>();
    private final List<JsonAdaptedStudent> students = new ArrayList<>();
    private final List<JsonAdaptedTutorial> tutorials = new ArrayList<>();


    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons questions tutorials.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                       @JsonProperty("students") List<JsonAdaptedStudent> students,
                                       @JsonProperty("questions") List<JsonAdaptedQuestion> questions,
                                       @JsonProperty("tutorials") List<JsonAdaptedTutorial> tutorials) {
        this.persons.addAll(persons);
        this.questions.addAll(questions);
        this.tutorials.addAll(tutorials);
        this.students.addAll(students);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        questions.addAll(source.getQuestionList().stream().map(JsonAdaptedQuestion::new).collect(Collectors.toList()));
        tutorials.addAll(source.getTutorialList().stream().map(JsonAdaptedTutorial::new).collect(Collectors.toList()));
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
        }
        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();
            if (addressBook.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            addressBook.addStudent(student);
        }
        for (JsonAdaptedQuestion jsonAdaptedQuestion : questions) {
            Question question = jsonAdaptedQuestion.toModelType();
            if (addressBook.hasQuestion(question)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_QUESTION);
            }
            addressBook.addQuestion(question);
        }
        for (JsonAdaptedTutorial jsonAdaptedTutorial : tutorials) {
            Tutorial tutorial = jsonAdaptedTutorial.toModelType();
            if (addressBook.hasTutorial(tutorial)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TUTORIAL);
            }
            addressBook.addTutorial(tutorial);
        }
        return addressBook;
    }
}
