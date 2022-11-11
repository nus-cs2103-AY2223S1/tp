package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDateTimes.EARLIER_VALID_DATE;
import static seedu.address.testutil.TypicalDateTimes.FIRST_VALID_TIME;
import static seedu.address.testutil.TypicalDateTimes.LATER_VALID_DATE;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.FindMyIntern;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyFindMyIntern;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.internship.Internship;
import seedu.address.testutil.InternshipBuilder;

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
    public void execute_interviewDateTimeBeforeAppliedDate_throwsCommandException() {
        Internship invalidInternship = new InternshipBuilder().withAppliedDate(LATER_VALID_DATE)
                .withInterviewDateTime(EARLIER_VALID_DATE + " " + FIRST_VALID_TIME).build();
        AddCommand addCommand = new AddCommand(invalidInternship);
        ModelStub modelStub = new ModelStubWithInternship(invalidInternship);

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_INTERVIEW_DATE, ()
                -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_duplicateInternship_throwsCommandException() {
        Internship validInternship = new InternshipBuilder().build();
        AddCommand addCommand = new AddCommand(validInternship);
        ModelStub modelStub = new ModelStubWithInternship(validInternship);

        assertThrows(CommandException.class, Messages.MESSAGE_DUPLICATE_INTERNSHIP, ()
                -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Internship google = new InternshipBuilder().withCompany("Google").build();
        Internship tiktok = new InternshipBuilder().withCompany("Tiktok").build();
        AddCommand addGoogleCommand = new AddCommand(google);
        AddCommand addTiktokCommand = new AddCommand(tiktok);

        // same object -> returns true
        assertTrue(addGoogleCommand.equals(addGoogleCommand));

        // same values -> returns true
        AddCommand addGoogleCommandCopy = new AddCommand(google);
        assertTrue(addGoogleCommand.equals(addGoogleCommandCopy));

        // different types -> returns false
        assertFalse(addGoogleCommand.equals(1));

        // null -> returns false
        assertFalse(addGoogleCommand.equals(null));

        // different internship -> returns false
        assertFalse(addGoogleCommand.equals(addTiktokCommand));
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
        public Path getFindMyInternFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFindMyInternFilePath(Path findMyInternFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addInternship(Internship internship) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFindMyIntern(ReadOnlyFindMyIntern newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyFindMyIntern getFindMyIntern() {
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
        public void markInternship(Internship target, Internship markedInternship) {
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
        public void updateSortedInternshipList(Comparator<Internship> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Predicate<Internship> getCurrentPredicate() {
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
        public ReadOnlyFindMyIntern getFindMyIntern() {
            return new FindMyIntern();
        }
    }

}
