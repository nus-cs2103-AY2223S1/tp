package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.module.Lesson;
import seedu.address.model.person.Person;
import seedu.address.model.person.user.EmptyUser;
import seedu.address.model.person.user.User;
import seedu.address.testutil.UserBuilder;

public class UserCommandTest {

    @Test
    public void constructor_nullUser_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new UserCommand(null));
    }

    @Test
    public void execute_userAcceptedByModel_addSuccessful() throws Exception {
        UserCommandTest.ModelStubAcceptingUserAdded modelStub = new UserCommandTest.ModelStubAcceptingUserAdded();
        User validUser = new UserBuilder().build();

        CommandResult commandResult = new UserCommand(validUser).execute(modelStub);

        assertEquals(String.format(UserCommand.MESSAGE_SUCCESS, validUser), commandResult.getFeedbackToUser());
        assertEquals(validUser, modelStub.getUser());
    }

    @Test
    public void execute_hasUser_throwsCommandException() {
        User validUser = new UserBuilder().build();
        UserCommand userCommand = new UserCommand(validUser);
        UserCommandTest.ModelStub modelStub = new UserCommandTest.ModelStubWithUser(validUser);

        assertThrows(CommandException.class, UserCommand.MESSAGE_USER_EXISTS, () -> userCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        User alice = new UserBuilder().withName("Alice").build();
        User bob = new UserBuilder().withName("Bob").build();
        UserCommand userAliceCommand = new UserCommand(alice);
        UserCommand userBobCommand = new UserCommand(bob);

        // same object -> returns true
        assertTrue(userAliceCommand.equals(userAliceCommand));

        // same values -> returns true
        UserCommand addAliceCommandCopy = new UserCommand(alice);
        assertTrue(userAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(userAliceCommand.equals(1));

        // null -> returns false
        assertFalse(userAliceCommand.equals(null));

        // different user -> returns false
        assertFalse(userAliceCommand.equals(userBobCommand));
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
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains an existing user.
     */
    private class ModelStubWithUser extends UserCommandTest.ModelStub {

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
    private class ModelStubAcceptingUserAdded extends UserCommandTest.ModelStub {

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
