package seedu.address.logic.commands.tutorial;

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
import seedu.address.model.person.Person;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.testutil.TutorialBuilder;

public class AddTutorialCommandTest {

    @Test
    public void constructor_nullTutorial_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTutorialCommand(null));
    }

    @Test
    public void execute_tutorialAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTutorialAdded modelStub = new ModelStubAcceptingTutorialAdded();
        Tutorial validTutorial = new TutorialBuilder().build();

        CommandResult commandResult = new AddTutorialCommand(validTutorial).execute(modelStub);

        assertEquals(String.format(
                AddTutorialCommand.MESSAGE_SUCCESS, validTutorial),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTutorial), modelStub.tutorialsAdded);
    }

    @Test
    public void execute_duplicateTutorial_throwsCommandException() {
        Tutorial validTutorial = new TutorialBuilder().build();
        AddTutorialCommand addCommand = new AddTutorialCommand(validTutorial);
        ModelStub modelStub = new ModelStubWithTutorial(validTutorial);

        assertThrows(CommandException.class,
                AddTutorialCommand.MESSAGE_DUPLICATE_TUTORIAL, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Tutorial w17 = new TutorialBuilder().withName("CS2103T W17").build();
        Tutorial f01 = new TutorialBuilder().withName("CS2103T F01").build();
        AddTutorialCommand addW17Command = new AddTutorialCommand(w17);
        AddTutorialCommand addF01Command = new AddTutorialCommand(f01);

        // same object -> returns true
        assertTrue(addW17Command.equals(addW17Command));

        // same values -> returns true
        AddTutorialCommand addAliceCommandCopy = new AddTutorialCommand(w17);
        assertTrue(addW17Command.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addW17Command.equals(1));

        // null -> returns false
        assertFalse(addW17Command.equals(null));

        // different tutorial -> returns false
        assertFalse(addW17Command.equals(addF01Command));
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
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            return;
        }

        @Override
        public boolean hasTutorial(Tutorial tutorial) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTutorialClashingWith(Tutorial tutorial) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTutorial(Tutorial tutorial) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTutorial(Tutorial target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Tutorial> getFilteredTutorialList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTutorialList(Predicate<Tutorial> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single tutorial.
     */
    private class ModelStubWithTutorial extends ModelStub {
        private final Tutorial tutorial;

        ModelStubWithTutorial(Tutorial tutorial) {
            requireNonNull(tutorial);
            this.tutorial = tutorial;
        }

        @Override
        public boolean hasTutorial(Tutorial tutorial) {
            requireNonNull(tutorial);
            return this.tutorial.isSameTutorial(tutorial);
        }
    }

    /**
     * A Model stub that always accept the tutorial being added.
     */
    private class ModelStubAcceptingTutorialAdded extends ModelStub {
        final ArrayList<Tutorial> tutorialsAdded = new ArrayList<>();

        @Override
        public boolean hasTutorial(Tutorial tutorial) {
            requireNonNull(tutorial);
            return tutorialsAdded.stream().anyMatch(tutorial::isSameTutorial);
        }

        @Override
        public boolean hasTutorialClashingWith(Tutorial tutorial) {
            requireNonNull(tutorial);
            return tutorialsAdded.stream().anyMatch(tutorial::isClashTutorial);
        }

        @Override
        public void addTutorial(Tutorial tutorial) {
            requireNonNull(tutorial);
            tutorialsAdded.add(tutorial);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
