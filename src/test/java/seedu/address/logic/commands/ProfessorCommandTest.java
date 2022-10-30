package seedu.address.logic.commands;

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
import seedu.address.commons.core.order.Order;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Professor;
import seedu.address.testutil.ProfessorBuilder;
import seedu.address.ui.PersonPieChart;

public class ProfessorCommandTest {

    @Test
    public void constructor_nullProfessor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ProfCommand(null));
    }

    @Test
    public void execute_professorAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingProfessorAdded modelStub = new ModelStubAcceptingProfessorAdded();
        Professor validPerson = new ProfessorBuilder().build();

        CommandResult commandResult = new ProfCommand(validPerson).execute(modelStub);

        assertEquals(String.format(ProfCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Professor validPerson = new ProfessorBuilder().build();
        ProfCommand profCommand = new ProfCommand(validPerson);
        ModelStub modelStub = new ModelStubWithProf(validPerson);

        assertThrows(CommandException.class,
            ProfCommand.MESSAGE_DUPLICATE_PERSON, () -> profCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Person alice = new ProfessorBuilder().withName("Alice").build();
        Person bob = new ProfessorBuilder().withName("Bob").build();
        ProfCommand addAliceCommand = new ProfCommand((Professor) alice);
        ProfCommand addBobCommand = new ProfCommand((Professor) bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        ProfCommand addAliceCommandCopy = new ProfCommand((Professor) alice);
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
        public int getAddressBookSize() {
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
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sort(Order order, boolean hasName, boolean hasModuleCode) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void openGithub(Person target) throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public PersonPieChart getPieChart() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPieChart(PersonPieChart personPieChart) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updatePieChart() {}
    }

    /**
     * A Model stub that contains a single Student.
     */
    private class ModelStubWithProf extends ModelStub {
        private final Professor prof;

        ModelStubWithProf(Professor prof) {
            requireNonNull(prof);
            this.prof = prof;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.prof.isSamePerson(person);
        }
    }

    /**
     * A Model stub that always accept the student being added.
     */
    private class ModelStubAcceptingProfessorAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
