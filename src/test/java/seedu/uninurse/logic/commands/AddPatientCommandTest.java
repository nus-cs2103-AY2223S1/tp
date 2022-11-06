package seedu.uninurse.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.uninurse.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.uninurse.commons.core.GuiSettings;
import seedu.uninurse.commons.core.Messages;
import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.PersonListTracker;
import seedu.uninurse.model.ReadOnlyUninurseBook;
import seedu.uninurse.model.ReadOnlyUserPrefs;
import seedu.uninurse.model.Schedule;
import seedu.uninurse.model.UninurseBook;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.person.Person;
import seedu.uninurse.testutil.PersonBuilder;

public class AddPatientCommandTest {
    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddPatientCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Patient validPerson = new PersonBuilder().build();

        CommandResult commandResult = new AddPatientCommand(validPerson).execute(modelStub);

        assertEquals(String.format(AddPatientCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());
        assertEquals(Collections.singletonList(validPerson), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Patient validPerson = new PersonBuilder().build();
        AddPatientCommand addCommand = new AddPatientCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class, Messages.MESSAGE_DUPLICATE_PATIENT, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Patient alice = new PersonBuilder().withName("Alice").build();
        Patient bob = new PersonBuilder().withName("Bob").build();
        AddPatientCommand addAliceCommand = new AddPatientCommand(alice);
        AddPatientCommand addBobCommand = new AddPatientCommand(bob);

        // same object -> returns true
        assertEquals(addAliceCommand, addAliceCommand);

        // same values -> returns true
        AddPatientCommand addAliceCommandCopy = new AddPatientCommand(alice);
        assertEquals(addAliceCommand, addAliceCommandCopy);

        // different types -> returns false
        assertNotEquals(1, addAliceCommand);

        // null -> returns false
        assertNotEquals(null, addAliceCommand);

        // different person -> returns false
        assertNotEquals(addAliceCommand, addBobCommand);
    }

    /**
     * A default model stub that has all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
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
        public Path getUninurseBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUninurseBookFilePath(Path uninurseBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUninurseBook getUninurseBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUninurseBook(ReadOnlyUninurseBook uninurseBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public PersonListTracker addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public PersonListTracker setPerson(Person person, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public PersonListTracker addPatient(Patient patient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public PersonListTracker setPatient(Patient patient, Patient editedPatient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public PersonListTracker deletePerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public PersonListTracker clearPersons(List<Person> persons) {
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
        public void updateFilteredPatientList(Predicate<Patient> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Patient getPatient(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Patient> getPatientList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPatientOfInterest(Patient patient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Patient getPatientOfInterest() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSchedule(Schedule schedule) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Schedule getSchedule() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void saveCurrentPersonListTracker() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public PersonListTracker getPersonListTracker() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updatePersons() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canUndo() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedo() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public CommandResult undo() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public CommandResult redo() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void makeSnapshot(CommandResult commandResult) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Person person;

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        private final ArrayList<Person> personsAdded = new ArrayList<>();

        ModelStubAcceptingPersonAdded() {
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public PersonListTracker addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
            return new PersonListTracker();
        }

        @Override
        public ReadOnlyUninurseBook getUninurseBook() {
            return new UninurseBook();
        }
    }
}
