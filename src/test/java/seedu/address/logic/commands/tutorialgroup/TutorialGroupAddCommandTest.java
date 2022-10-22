package seedu.address.logic.commands.tutorialgroup;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.student.Student;
import seedu.address.model.student.TutorialGroup;
import seedu.address.model.task.Task;
import seedu.address.testutil.TutorialGroupBuilder;







public class TutorialGroupAddCommandTest {

    @Test
    public void constructor_nullTutorialGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TutorialGroupAddCommand(null));
    }

    @Test
    public void execute_tutorialGroupAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTutorialGroupAdded modelStub = new ModelStubAcceptingTutorialGroupAdded();
        TutorialGroup validTutorialGroup = new TutorialGroupBuilder().build();

        CommandResult commandResult = new TutorialGroupAddCommand(validTutorialGroup).execute(modelStub);

        assertEquals(String.format(TutorialGroupAddCommand.MESSAGE_SUCCESS, validTutorialGroup),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTutorialGroup), modelStub.tutorialsAdded);
    }

    @Test
    public void execute_duplicateTutorialGroup_throwsCommandException() {
        TutorialGroup validTutorialGroup = new TutorialGroupBuilder().build();

        TutorialGroupAddCommand tutorialGroupAddCommand = new TutorialGroupAddCommand(validTutorialGroup);
        ModelStub modelStub = new ModelStubWithTutorialGroup(validTutorialGroup);

        assertThrows(CommandException.class, TutorialGroupAddCommand.MESSAGE_DUPLICATE_TUTORIAL_GROUP, ()
                -> tutorialGroupAddCommand.execute(modelStub));
    }

    @Test
    public void equals() {

        TutorialGroup groupA = new TutorialGroupBuilder().withName("T02").build();
        TutorialGroup groupB = new TutorialGroupBuilder().withName("T03").build();
        TutorialGroupAddCommand addTutorialGroupACommand = new TutorialGroupAddCommand(groupA);
        TutorialGroupAddCommand addTutorialGroupBCommand = new TutorialGroupAddCommand(groupB);

        // same object -> returns true
        assertTrue(addTutorialGroupACommand.equals(addTutorialGroupACommand));

        // same values -> returns true
        TutorialGroupAddCommand addTutorialGroupACommandCopy = new TutorialGroupAddCommand(groupA);
        assertTrue(addTutorialGroupACommand.equals(addTutorialGroupACommandCopy));

        // different types -> returns false
        assertFalse(addTutorialGroupACommand.equals(1));

        // null -> returns false
        assertFalse(addTutorialGroupACommand.equals(null));

        // different person -> returns false
        assertFalse(addTutorialGroupACommand.equals(addTutorialGroupBCommand));
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
        public void addStudent(Student student) {
            throw new AssertionError("This method should not be called.");
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
        public boolean hasStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteStudent(Student target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setStudent(Student target, Student editedStudent) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Student findStudent(String student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Student> getFilteredStudentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredStudentList(Predicate<Student> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredStudentListByTg(TutorialGroup tutorialGroup) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTask(Task target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTask(Task target, Task editedTask) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTaskList(Predicate<Task> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<TutorialGroup> getFilteredTutorialGroupList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTutorialGroupList(Predicate<TutorialGroup> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTutorialGroup(TutorialGroup tutorialGroup) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTutorialGroup(TutorialGroup target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTutorialGroup(TutorialGroup tutorialGroup) {
            throw new AssertionError("This method should not be called.");
        }

    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithTutorialGroup extends ModelStub {
        private final TutorialGroup tutorialGroup;

        ModelStubWithTutorialGroup(TutorialGroup tutorialGroup) {
            requireNonNull(tutorialGroup);
            this.tutorialGroup = tutorialGroup;
        }

        @Override
        public boolean hasTutorialGroup(TutorialGroup tutorialGroup) {
            requireNonNull(tutorialGroup);
            return this.tutorialGroup.isSameTutorialGroup(tutorialGroup);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingTutorialGroupAdded extends ModelStub {
        final ArrayList<TutorialGroup> tutorialsAdded = new ArrayList<>();

        @Override
        public boolean hasTutorialGroup(TutorialGroup tutorialGroup) {
            requireNonNull(tutorialGroup);
            return tutorialsAdded.stream().anyMatch(tutorialGroup::isSameTutorialGroup);
        }

        @Override
        public void addTutorialGroup(TutorialGroup tutorialGroup) {
            requireNonNull(tutorialGroup);
            tutorialsAdded.add(tutorialGroup);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
