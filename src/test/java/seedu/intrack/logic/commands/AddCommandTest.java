package seedu.intrack.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intrack.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.intrack.commons.core.GuiSettings;
import seedu.intrack.logic.commands.exceptions.CommandException;
import seedu.intrack.model.InTrack;
import seedu.intrack.model.Model;
import seedu.intrack.model.ReadOnlyInTrack;
import seedu.intrack.model.ReadOnlyUserPrefs;
import seedu.intrack.model.internship.Internship;
import seedu.intrack.testutil.InternshipBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_internshipAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingInternshipAdded modelStub = new ModelStubAcceptingInternshipAdded();
        Internship validInternship = new InternshipBuilder().build();

        CommandResult commandResult = new AddCommand(validInternship).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validInternship), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validInternship), modelStub.internshipsAdded);
    }

    @Test
    public void execute_duplicateInternship_throwsCommandException() {
        Internship validInternship = new InternshipBuilder().build();
        AddCommand addCommand = new AddCommand(validInternship);
        ModelStub modelStub = new ModelStubWithInternship(validInternship);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_INTERNSHIP, ()
                -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Internship alice = new InternshipBuilder().withName("Alice").build();
        Internship bob = new InternshipBuilder().withName("Bob").build();
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

        // different internship -> returns false
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
        public Path getInTrackFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInTrackFilePath(Path inTrackFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addInternship(Internship internship) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInTrack(ReadOnlyInTrack newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyInTrack getInTrack() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasInternship(Internship internship) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteInternship(Internship target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInternship(Internship target, Internship editedInternship) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Internship> getFilteredInternshipList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredInternshipList(Predicate<Internship> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single internship.
     */
    private class ModelStubWithInternship extends ModelStub {
        private final Internship internship;

        ModelStubWithInternship(Internship internship) {
            requireNonNull(internship);
            this.internship = internship;
        }

        @Override
        public boolean hasInternship(Internship internship) {
            requireNonNull(internship);
            return this.internship.isSameInternship(internship);
        }
    }

    /**
     * A Model stub that always accept the internship being added.
     */
    private class ModelStubAcceptingInternshipAdded extends ModelStub {
        final ArrayList<Internship> internshipsAdded = new ArrayList<>();

        @Override
        public boolean hasInternship(Internship internship) {
            requireNonNull(internship);
            return internshipsAdded.stream().anyMatch(internship::isSameInternship);
        }

        @Override
        public void addInternship(Internship internship) {
            requireNonNull(internship);
            internshipsAdded.add(internship);
        }

        @Override
        public ReadOnlyInTrack getInTrack() {
            return new InTrack();
        }
    }

}
