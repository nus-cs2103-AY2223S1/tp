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
import seedu.address.model.exam.Exam;
import seedu.address.model.module.Module;
import seedu.address.model.task.Task;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_MODULE_NOT_PRESENT = "This module does not exist";
    public static final String MESSAGE_DUPLICATE_MODULE = "There are duplicate module(s) "
            + "present in the module list";
    public static final String INVALID_TASK_LINKED = "Invalid task is linked to the exam";

    public static final String INVALID_EXAM_LINKED = "Invalid exam is linked to the task";
    public static final String INVALID_EXAM_TASK_LINKED = "Invalid link is formed between the task and exam.";
    private final List<JsonAdaptedModule> modules = new ArrayList<>();
    private final List<JsonAdaptedTask> tasks = new ArrayList<>();
    private final List<JsonAdaptedExam> exams = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given tasks and modules.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("tasks") List<JsonAdaptedTask> tasks,
            @JsonProperty("modules") List<JsonAdaptedModule> modules,
                                       @JsonProperty("exams") List<JsonAdaptedExam> exams) {
        this.tasks.addAll(tasks);
        this.modules.addAll(modules);
        this.exams.addAll(exams);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        modules.addAll(source.getModuleList().stream().map(JsonAdaptedModule::new).collect(Collectors.toList()));
        tasks.addAll(source.getTaskList().stream().map(JsonAdaptedTask::new).collect(Collectors.toList()));
        exams.addAll(source.getExamList().stream().map(JsonAdaptedExam::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedModule jsonAdaptedModule: modules) {
            Module module = jsonAdaptedModule.toModelType();
            if (addressBook.hasModule(module)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MODULE);
            }
            addressBook.addModule(module);
        }
        for (JsonAdaptedTask jsonAdaptedTask: tasks) {
            Task task = jsonAdaptedTask.toModelType();
            if (!addressBook.hasModule(task.getModule())) {
                throw new IllegalValueException(MESSAGE_MODULE_NOT_PRESENT);
            }
            addressBook.addTask(task);
        }
        for (JsonAdaptedExam jsonAdaptedExam: exams) {
            Exam exam = jsonAdaptedExam.toModelType();
            if (!addressBook.hasModule(exam.getModule())) {
                throw new IllegalValueException(MESSAGE_MODULE_NOT_PRESENT);
            }
            for (Task task : exam.getTasksLinked()) {
                if (!addressBook.hasTask(task)) {
                    throw new IllegalValueException(INVALID_TASK_LINKED);
                }
            }
            addressBook.addExam(exam);
        }
        for (Task task : addressBook.getTaskList()) {
            if (task.getExam() != null && !addressBook.hasExam(task.getExam())) {
                throw new IllegalValueException(INVALID_EXAM_LINKED);
            }
        }
        if (!checkTaskExamLink(addressBook)) {
            throw new IllegalValueException(INVALID_EXAM_TASK_LINKED);
        }
        return addressBook;
    }

    private boolean checkTaskExamLink(AddressBook addressBook) {
        List<Exam> exams = addressBook.getExamList();
        List<Task> tasks = addressBook.getTaskList();
        for (Exam exam :exams) {
            List<Task> examTasksLinked = exam.getTasksLinked();
            for (Task task : tasks) {
                if (task.getExam() == null && examTasksLinked.contains(task)) {
                    return false;
                }
                if (task.getExam() != null
                        && examTasksLinked.contains(task)
                        && !task.getExam().isSameExam(exam)) {
                    return false;
                }
                if (task.getExam() != null && !examTasksLinked.contains(task)) {
                    return false;
                }
            }
        }
        return true;
    }

}
