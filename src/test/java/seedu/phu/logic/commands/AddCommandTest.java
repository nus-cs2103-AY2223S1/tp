package seedu.phu.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.phu.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.phu.commons.core.GuiSettings;
import seedu.phu.logic.commands.exceptions.CommandException;
import seedu.phu.model.InternshipBook;
import seedu.phu.model.Model;
import seedu.phu.model.ReadOnlyInternshipBook;
import seedu.phu.model.ReadOnlyUserPrefs;
import seedu.phu.model.internship.ComparableCategory;
import seedu.phu.model.internship.Internship;
import seedu.phu.testutil.InternshipBuilder;

public class AddCommandTest {
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_internshipAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingInternshipAdded modelStub = new ModelStubAcceptingInternshipAdded();
        Internship validInternship = new InternshipBuilder().build();
        AddCommand addCommand = new AddCommand(validInternship);
        commandHistory.addCommand(addCommand.toString());

        CommandResult commandResult = addCommand.execute(modelStub, commandHistory);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validInternship), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validInternship), modelStub.internshipsAdded);
    }

    @Test
    public void execute_duplicateInternship_throwsCommandException() {
        Internship validInternship = new InternshipBuilder().build();
        AddCommand addCommand = new AddCommand(validInternship);
        ModelStub modelStub = new ModelStubWithInternship(validInternship);

        assertThrows(CommandException.class,
                AddCommand.MESSAGE_DUPLICATE_INTERNSHIP, () -> addCommand.execute(modelStub, commandHistory));
    }

    @Test
    public void equals() {
        Internship amazon = new InternshipBuilder().withName("Amazon").build();
        Internship blackrock = new InternshipBuilder().withName("Blackrock").build();
        AddCommand addAmazonCommand = new AddCommand(amazon);
        AddCommand addBlackrockCommand = new AddCommand(blackrock);

        // same object -> returns true
        assertTrue(addAmazonCommand.equals(addAmazonCommand));

        // same values -> returns true
        AddCommand addAmazonCommandCopy = new AddCommand(amazon);
        assertTrue(addAmazonCommand.equals(addAmazonCommandCopy));

        // different types -> returns false
        assertFalse(addAmazonCommand.equals(1));

        // null -> returns false
        assertFalse(addAmazonCommand.equals(null));

        // different internship -> returns false
        assertFalse(addAmazonCommand.equals(addBlackrockCommand));
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
        public Path getInternshipBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInternshipBookFilePath(Path internshipBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addInternship(Internship internship) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInternshipBook(ReadOnlyInternshipBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyInternshipBook getInternshipBook() {
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

        @Override
        public ObservableList<Internship> getViewItem() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateViewItem(Predicate<Internship> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortList(ComparableCategory category) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void reverseList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void viewInternship(Internship internshipToView) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canUndoInternshipBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedoInternshipBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoInternshipBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoInternshipBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitInternshipBookChange() {
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
        public ReadOnlyInternshipBook getInternshipBook() {
            return new InternshipBook();
        }

        @Override
        public void commitInternshipBookChange() {}
    }

}
