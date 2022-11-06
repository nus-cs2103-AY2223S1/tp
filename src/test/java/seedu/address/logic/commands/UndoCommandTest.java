package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_UNDO;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Lesson;
import seedu.address.model.person.Person;
import seedu.address.model.person.user.EmptyUser;
import seedu.address.model.person.user.User;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.UserBuilder;

public class UndoCommandTest {

    @Test
    public void execute_newState_failure() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        UndoCommand undoCommand = new UndoCommand();
        assertCommandFailure(undoCommand, model, MESSAGE_INVALID_UNDO);
    }

    @Test
    public void execute_addPersonUndo_success() throws Exception {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Person validPerson = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validPerson).execute(model);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        UndoCommand undoCommand = new UndoCommand();
        assertCommandSuccess(undoCommand, model, UndoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_deleteUserUndo_success() throws Exception {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        CommandResult deleteUserCommandResult = new DeleteUserCommand().execute(model);
        UndoCommand undoCommand = new UndoCommand();

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandSuccess(undoCommand, model, UndoCommand.MESSAGE_SUCCESS, expectedModel);
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
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasUser() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addUser(User user) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public User getUser() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteUser() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUser(User editedUser) {
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
        public void addLessonToUser(Lesson lesson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeLessonToUser(Lesson lesson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public boolean setTimetable(Set<Lesson> lessons) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Set<Lesson> getTimetable() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void nextSem() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitAddressBook() {
        }

        @Override
        public boolean canUndoAddressBook() {
            return false;
        }

        @Override
        public boolean canRedoAddressBook() {
            return false;
        }

        @Override
        public void undoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains an existing user.
     */
    private class ModelStubWithUser extends UndoCommandTest.ModelStub {

        final EmptyUser emptyUser = new EmptyUser();
        final User user;

        ModelStubWithUser(User user) {
            requireNonNull(user);
            this.user = user;
        }

        @Override
        public boolean hasUser() {
            return !(user.equals(emptyUser));
        }
    }

    /**
     * A Model stub that always accept the user being added.
     */
    private class ModelStubAcceptingUserAdded extends UndoCommandTest.ModelStub {

        final EmptyUser emptyUser = new EmptyUser();
        private User user = emptyUser;

        @Override
        public boolean hasUser() {
            return !(user.equals(emptyUser));
        }

        @Override
        public void addUser(User user) {
            requireNonNull(user);
            this.user = user;
        }

        @Override
        public User getUser() {
            return user;
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
