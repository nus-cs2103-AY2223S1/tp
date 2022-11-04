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
import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.PatientListTracker;
import seedu.uninurse.model.ReadOnlyUninurseBook;
import seedu.uninurse.model.ReadOnlyUserPrefs;
import seedu.uninurse.model.Schedule;
import seedu.uninurse.model.UninurseBook;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.task.DateTime;
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

        assertThrows(CommandException.class,
                AddPatientCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
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
        public PatientListTracker addPerson(Patient person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUninurseBook getUninurseBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUninurseBook(ReadOnlyUninurseBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Patient person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public PatientListTracker deletePerson(Patient target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public PatientListTracker clearPersons(List<Patient> targets) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public PatientListTracker setPerson(Patient target, Patient editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Patient> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Patient> predicate) {
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
        public void setDayOfInterest(DateTime dayOfInterest) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Schedule getSchedule() {
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

        @Override
        public void saveCurrentPatientListTracker() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public PatientListTracker getSavedPatientListTracker() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateRecurringTasks() {
            throw new AssertionError("This method should not be called");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Patient person;

        ModelStubWithPerson(Patient person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasPerson(Patient person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        private final ArrayList<Patient> personsAdded = new ArrayList<>();
        private Patient patientOfInterest;

        ModelStubAcceptingPersonAdded() {
            this.patientOfInterest = new PersonBuilder().build();
        }

        @Override
        public boolean hasPerson(Patient person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public PatientListTracker addPerson(Patient person) {
            requireNonNull(person);
            personsAdded.add(person);
            return new PatientListTracker();
        }

        @Override
        public ReadOnlyUninurseBook getUninurseBook() {
            return new UninurseBook();
        }

        @Override
        public void setPatientOfInterest(Patient patient) {
            this.patientOfInterest = patient;
        }
    }

}
