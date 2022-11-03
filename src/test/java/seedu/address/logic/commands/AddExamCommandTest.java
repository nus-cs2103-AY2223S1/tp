package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_EXAM;
import static seedu.address.commons.core.Messages.MESSAGE_MODULE_NOT_FOUND;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Criteria;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.exam.Exam;
import seedu.address.model.exam.exceptions.DuplicateExamException;
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.testutil.ExamBuilder;

public class AddExamCommandTest {
    @Test
    public void constructor_nullExam_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddExamCommand(null));
    }
    @Test
    public void execute_examAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingExamAdded modelStub = new ModelStubAcceptingExamAdded();
        Exam validExam = new ExamBuilder().build();
        CommandResult commandResult = new AddExamCommand(validExam).execute(modelStub);
        assertEquals(String.format(AddExamCommand.MESSAGE_SUCCESS, validExam), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validExam), modelStub.examsAdded);
    }
    @Test
    public void execute_duplicateExam_throwsCommandException() {
        Exam validExam = new ExamBuilder().build();
        AddExamCommand addExamCommand = new AddExamCommand(validExam);
        ModelStub modelStub = new ModelStubWithExam(validExam);
        assertThrows(CommandException.class, MESSAGE_DUPLICATE_EXAM, () -> addExamCommand.execute(modelStub));
    }

    @Test
    public void execute_moduleNotFound_throwsCommandException() {
        Exam validExam = new ExamBuilder().build();
        AddExamCommand addExamCommand = new AddExamCommand(validExam);
        ModelStubWithNoModules modelStub = new ModelStubWithNoModules();
        assertThrows(CommandException.class, MESSAGE_MODULE_NOT_FOUND, () -> addExamCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Exam examOne = new ExamBuilder().withModule("CS2030s")
                .withDate("02-02-2024").withDescription("exam one").build();
        Exam examTwo = new ExamBuilder().withModule("CS2040s")
                .withDate("02-06-2026").withDescription("exam two").build();
        Exam examOneWithDiffDate = new ExamBuilder().withModule("CS2030s")
                .withDate("02-06-2026").withDescription("exam one").build();
        Exam examOneWithDiffModule = new ExamBuilder().withModule("CS2040s")
                .withDate("02-02-2024").withDescription("exam one").build();
        Exam examOneWithDiffDescription = new ExamBuilder().withModule("CS2030s")
                .withDate("02-02-2024").withDescription("exam one one").build();

        AddExamCommand addExamOneCommand = new AddExamCommand(examOne);
        AddExamCommand addExamTwoCommand = new AddExamCommand(examTwo);
        AddExamCommand addExamOneWithDiffDateCommand = new AddExamCommand(examOneWithDiffDate);
        AddExamCommand addExamOneWithDiffModCommand = new AddExamCommand(examOneWithDiffModule);
        AddExamCommand addExamOneWithDiffDescCommand = new AddExamCommand(examOneWithDiffDescription);
        assertTrue(addExamOneCommand.equals(addExamOneCommand));
        AddExamCommand addExamOneCommandCopy = new AddExamCommand(examOne);
        assertTrue(addExamOneCommand.equals(addExamOneCommandCopy));
        assertFalse(addExamOneCommand.equals(1));
        assertFalse(addExamOneCommand.equals(null));
        assertFalse(addExamOneCommand.equals(addExamTwoCommand));
        assertFalse(addExamOneCommand.equals(addExamOneWithDiffDateCommand));
        assertFalse(addExamOneCommand.equals(addExamOneWithDiffDescCommand));
        assertFalse(addExamOneCommand.equals(addExamOneWithDiffModCommand));
    }
    /**
     * A default model stub that have all of the methods failing.
     */

    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void addModule(Module module) {
            throw new AssertionError("This method should not even be called.");
        }
        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public boolean hasModule(Module module) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void updateFilteredModuleList(Predicate<Module> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTaskList(Predicate<Task> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortTaskList(Criteria criteria) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void unlinkTasksFromExam(Exam exam) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasExam(Exam exam) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteExam(Exam target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasExamWithModule(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addExam(Exam exam) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void replaceExam(Exam target, Exam editedExam, boolean isSameExam) throws DuplicateExamException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Exam> getFilteredExamList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredExamList(Predicate<Exam> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        public void updateExamFieldForTask(Exam previousExam, Exam newExam) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isExamLinkedToTask(Exam examToEdit) {
            throw new AssertionError("This method should not be called.");
        }

        public void updateModuleFieldForTask(Module previousModule, Module newModule) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateModuleFieldForExam(Module previousModule, Module newModule) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTasksWithModule(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteExamsWithModule(Module module) {

            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Module> getFilteredModuleList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTask(Task target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteModule(Module target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTaskWithModule(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void replaceTask(Task task, Task editedTask, boolean isSameTask) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void replaceModule(Module module, Module editedModule) {
            throw new AssertionError("This method should not be called.");
        }
    }
    /**
     * A Model stub that contains a single exam.
     */
    private class ModelStubWithExam extends ModelStub {
        private final Exam exam;
        ModelStubWithExam(Exam exam) {
            requireNonNull(exam);
            this.exam = exam;
        }
        @Override
        public boolean hasExam(Exam exam) {
            requireNonNull(exam);
            return this.exam.isSameExam(exam);
        }
        @Override
        public boolean hasModule(Module module) {
            return true;
        }
    }
    /**
     * A Model stub that always accept the exam being added.
     */
    private class ModelStubAcceptingExamAdded extends ModelStub {
        final ArrayList<Exam> examsAdded = new ArrayList<>();
        @Override
        public boolean hasExam(Exam exam) {
            requireNonNull(exam);
            return examsAdded.stream().anyMatch(exam::isSameExam);
        }
        @Override
        public boolean hasModule(Module module) {
            return true;
        }
        @Override
        public void addExam(Exam exam) {
            requireNonNull(exam);
            examsAdded.add(exam);
        }
    }

    private class ModelStubWithNoModules extends ModelStub {
        final ArrayList<Exam> examsAdded = new ArrayList<>();

        @Override
        public boolean hasExam(Exam exam) {
            requireNonNull(exam);
            return examsAdded.stream().anyMatch(exam::isSameExam);
        }

        @Override
        public boolean hasModule(Module module) {
            return false;
        }
    }
}
