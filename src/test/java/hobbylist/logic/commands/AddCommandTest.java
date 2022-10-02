package hobbylist.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static hobbylist.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import hobbylist.model.HobbyList;
import hobbylist.model.activity.Activity;
import hobbylist.testutil.Assert;
import hobbylist.testutil.PersonBuilder;
import javafx.collections.ObservableList;
import hobbylist.commons.core.GuiSettings;
import hobbylist.logic.commands.exceptions.CommandException;
import hobbylist.model.Model;
import hobbylist.model.ReadOnlyHobbyList;
import hobbylist.model.ReadOnlyUserPrefs;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Activity validActivity = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validActivity).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validActivity), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validActivity), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Activity validActivity = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validActivity);
        ModelStub modelStub = new ModelStubWithPerson(validActivity);

        Assert.assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Activity alice = new PersonBuilder().withName("Alice").build();
        Activity bob = new PersonBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
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
        public void addPerson(Activity activity) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyHobbyList newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyHobbyList getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Activity activity) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Activity target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Activity target, Activity editedActivity) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Activity> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Activity> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Activity activity;

        ModelStubWithPerson(Activity activity) {
            requireNonNull(activity);
            this.activity = activity;
        }

        @Override
        public boolean hasPerson(Activity activity) {
            requireNonNull(activity);
            return this.activity.isSamePerson(activity);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Activity> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Activity activity) {
            requireNonNull(activity);
            return personsAdded.stream().anyMatch(activity::isSamePerson);
        }

        @Override
        public void addPerson(Activity activity) {
            requireNonNull(activity);
            personsAdded.add(activity);
        }

        @Override
        public ReadOnlyHobbyList getAddressBook() {
            return new HobbyList();
        }
    }

}
