package seedu.address.logic.commands.consultation;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.consultation.AddConsultationCommand;
import seedu.address.logic.commands.consultation.AddConsultationCommandTest;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.consultation.Consultation;
import seedu.address.testutil.consultationBuilder;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;

public class AddConsultationCommandTest {
    @Test
    public void constructor_nullConsultation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddConsultationCommand(null));
    }

    @Test
    public void execute_ConsultationAcceptedByModel_addSuccessful() throws Exception {
        AddConsultationCommandTest.ModelStubAcceptingConsultationAdded modelStub = new AddConsultationCommandTest.ModelStubAcceptingConsultationAdded();
        Consultation validConsultation = new ConsultationBuilder().build();

        CommandResult commandResult = new AddConsultationCommand(validConsultation).execute(modelStub);

        assertEquals(String.format(
                        AddConsultationCommand.MESSAGE_SUCCESS, validConsultation),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validConsultation), modelStub.ConsultationsAdded);
    }

    @Test
    public void execute_duplicateConsultation_throwsCommandException() {
        Consultation validConsultation = new ConsultationBuilder().build();
        AddConsultationCommand addCommand = new AddConsultationCommand(validConsultation);
        AddConsultationCommandTest.ModelStub modelStub = new AddConsultationCommandTest.ModelStubWithConsultation(validConsultation);

        assertThrows(CommandException.class,
                AddConsultationCommand.MESSAGE_DUPLICATE_Consultation, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Consultation w17 = new ConsultationBuilder().withName("CS2103T W17").build();
        Consultation f01 = new ConsultationBuilder().withName("CS2103T F01").build();
        AddConsultationCommand addW17Command = new AddConsultationCommand(w17);
        AddConsultationCommand addF01Command = new AddConsultationCommand(f01);

        // same object -> returns true
        assertTrue(addW17Command.equals(addW17Command));

        // same values -> returns true
        AddConsultationCommand addAliceCommandCopy = new AddConsultationCommand(w17);
        assertTrue(addW17Command.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addW17Command.equals(1));

        // null -> returns false
        assertFalse(addW17Command.equals(null));

        // different Consultation -> returns false
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
        public boolean hasConsultation(Consultation Consultation) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasConsultationClashingWith(Consultation Consultation) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addConsultation(Consultation Consultation) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Consultation> getFilteredConsultationList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredConsultationList(Predicate<Consultation> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single Consultation.
     */
    private class ModelStubWithConsultation extends AddConsultationCommandTest.ModelStub {
        private final Consultation Consultation;

        ModelStubWithConsultation(Consultation Consultation) {
            requireNonNull(Consultation);
            this.Consultation = Consultation;
        }

        @Override
        public boolean hasConsultation(Consultation Consultation) {
            requireNonNull(Consultation);
            return this.Consultation.isSameConsultation(Consultation);
        }
    }

    /**
     * A Model stub that always accept the Consultation being added.
     */
    private class ModelStubAcceptingConsultationAdded extends AddConsultationCommandTest.ModelStub {
        final ArrayList<Consultation> ConsultationsAdded = new ArrayList<>();

        @Override
        public boolean hasConsultation(Consultation Consultation) {
            requireNonNull(Consultation);
            return ConsultationsAdded.stream().anyMatch(Consultation::isSameConsultation);
        }

        @Override
        public boolean hasConsultationClashingWith(Consultation Consultation) {
            requireNonNull(Consultation);
            return ConsultationsAdded.stream().anyMatch(Consultation::isClashConsultation);
        }

        @Override
        public void addConsultation(Consultation Consultation) {
            requireNonNull(Consultation);
            ConsultationsAdded.add(Consultation);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
