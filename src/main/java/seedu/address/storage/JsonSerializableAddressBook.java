package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javafx.collections.ObservableMap;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.grade.Grade;
import seedu.address.model.grade.GradeKey;
import seedu.address.model.student.Student;
import seedu.address.model.student.TutorialGroup;
import seedu.address.model.task.Task;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Students list contains duplicate student(s).";

    private final List<JsonAdaptedStudent> students = new ArrayList<>();
    private final List<JsonAdaptedTask> tasks = new ArrayList<>();
    private final List<JsonAdaptedTutorialGroup> groups = new ArrayList<>();
    private final List<JsonAdaptedGradeTuple> grades = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given students.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("students") List<JsonAdaptedStudent> students,
                                       @JsonProperty("tasks") List<JsonAdaptedTask> tasks,
                                       @JsonProperty("groups") List<JsonAdaptedTutorialGroup> groups,
                                       @JsonProperty("grades") List<JsonAdaptedGradeTuple> grades) {
        this.students.addAll(students);
        this.tasks.addAll(tasks);
        this.groups.addAll(groups);
        this.grades.addAll(grades);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
        tasks.addAll(source.getTaskList().stream().map(JsonAdaptedTask::new).collect(Collectors.toList()));
        groups.addAll(source.getTutorialGroupList().stream().map(JsonAdaptedTutorialGroup::new)
                .collect(Collectors.toList()));
        ObservableMap<GradeKey, Grade> gradeMap = source.getGradeMap();
        for (GradeKey gradeKey : gradeMap.keySet()) {
            Grade grade = gradeMap.get(gradeKey);
            grades.add(new JsonAdaptedGradeTuple(gradeKey, grade));
        }
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
            if (addressBook.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addStudent(student);
        }

        for (JsonAdaptedTutorialGroup jsonAdaptedTutorialGroup : groups) {
            TutorialGroup tutorialGroup = jsonAdaptedTutorialGroup.toModelType();
            if (addressBook.hasTutorialGroup(tutorialGroup)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addTutorialGroup(tutorialGroup);
        }

        for (JsonAdaptedTask jsonAdaptedTask : tasks) {
            Task task = jsonAdaptedTask.toModelType();
            if (addressBook.hasTask(task)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addTask(task);
        }

        for (JsonAdaptedGradeTuple jsonAdaptedGradeTuple : grades) {
            GradeKey gradeKey = jsonAdaptedGradeTuple.getGradeKey().toModelType();
            Grade grade = jsonAdaptedGradeTuple.getGrade().toModelType();
            addressBook.addGrade(gradeKey, grade);
        }
        return addressBook;
    }
}
