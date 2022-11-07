package seedu.trackascholar.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackascholar.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.trackascholar.commons.core.GuiSettings;
import seedu.trackascholar.logic.commands.exceptions.CommandException;
import seedu.trackascholar.model.Model;
import seedu.trackascholar.model.ReadOnlyTrackAScholar;
import seedu.trackascholar.model.ReadOnlyUserPrefs;
import seedu.trackascholar.model.TrackAScholar;
import seedu.trackascholar.model.applicant.Applicant;
import seedu.trackascholar.model.applicant.ApplicationStatus;
import seedu.trackascholar.testutil.ApplicantBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullApplicant_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_applicantAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingApplicantAdded modelStub = new ModelStubAcceptingApplicantAdded();
        Applicant validApplicant = new ApplicantBuilder().build();

        CommandResult commandResult = new AddCommand(validApplicant).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validApplicant), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validApplicant), modelStub.applicantsAdded);
    }

    @Test
    public void execute_duplicateApplicant_throwsCommandException() {
        Applicant validApplicant = new ApplicantBuilder().build();
        AddCommand addCommand = new AddCommand(validApplicant);
        ModelStub modelStub = new ModelStubWithApplicant(validApplicant);

        assertThrows(CommandException.class,
                AddCommand.MESSAGE_DUPLICATE_APPLICANT, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Applicant alice = new ApplicantBuilder().withName("Alice").build();
        Applicant bob = new ApplicantBuilder().withName("Bob").build();
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

        // different applicant -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        private static final String MESSAGE_RESTRICTED_METHOD = "This method should not be called.";

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError(MESSAGE_RESTRICTED_METHOD);
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError(MESSAGE_RESTRICTED_METHOD);
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError(MESSAGE_RESTRICTED_METHOD);
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError(MESSAGE_RESTRICTED_METHOD);
        }

        @Override
        public Path getTrackAScholarFilePath() {
            throw new AssertionError(MESSAGE_RESTRICTED_METHOD);
        }

        @Override
        public void setTrackAScholarFilePath(Path trackAScholarFilePath) {
            throw new AssertionError(MESSAGE_RESTRICTED_METHOD);
        }

        @Override
        public void addApplicant(Applicant applicant) {
            throw new AssertionError(MESSAGE_RESTRICTED_METHOD);
        }

        @Override
        public void setTrackAScholar(ReadOnlyTrackAScholar newData) {
            throw new AssertionError(MESSAGE_RESTRICTED_METHOD);
        }

        @Override
        public ReadOnlyTrackAScholar getTrackAScholar() {
            throw new AssertionError(MESSAGE_RESTRICTED_METHOD);
        }

        @Override
        public boolean hasApplicant(Applicant applicant) {
            throw new AssertionError(MESSAGE_RESTRICTED_METHOD);
        }

        @Override
        public void deleteApplicant(Applicant target) {
            throw new AssertionError(MESSAGE_RESTRICTED_METHOD);
        }

        @Override
        public void removeApplicant(ApplicationStatus applicationStatus) {
            throw new AssertionError(MESSAGE_RESTRICTED_METHOD);
        }

        @Override
        public void setApplicant(Applicant target, Applicant editedApplicant) {
            throw new AssertionError(MESSAGE_RESTRICTED_METHOD);
        }

        @Override
        public void sortApplicants(Comparator<Applicant> comparator) {
            throw new AssertionError(MESSAGE_RESTRICTED_METHOD);
        }

        @Override
        public ObservableList<Applicant> getFilteredApplicantList() {
            throw new AssertionError(MESSAGE_RESTRICTED_METHOD);
        }

        @Override
        public ObservableList<Applicant> getPinnedApplicantList() {
            throw new AssertionError(MESSAGE_RESTRICTED_METHOD);
        }

        @Override
        public ObservableList<Applicant> getAllApplicants() {
            throw new AssertionError(MESSAGE_RESTRICTED_METHOD);
        }

        @Override
        public void updateFilteredApplicantList(Predicate<Applicant> predicate) {
            throw new AssertionError(MESSAGE_RESTRICTED_METHOD);
        }

        @Override
        public Applicant findSimilarApplicant(Applicant applicant) {
            throw new AssertionError(MESSAGE_RESTRICTED_METHOD);
        }

        @Override
        public void importWithReplace(ObservableList<Applicant> applicantList) {
            throw new AssertionError(MESSAGE_RESTRICTED_METHOD);
        }

        @Override
        public void importWithoutReplace(ObservableList<Applicant> applicantList) {
            throw new AssertionError(MESSAGE_RESTRICTED_METHOD);
        }
    }

    /**
     * A Model stub that contains a single applicant.
     */
    private class ModelStubWithApplicant extends ModelStub {
        private final Applicant applicant;

        ModelStubWithApplicant(Applicant applicant) {
            requireNonNull(applicant);
            this.applicant = applicant;
        }

        @Override
        public boolean hasApplicant(Applicant applicant) {
            requireNonNull(applicant);
            return this.applicant.isSameApplicant(applicant);
        }
    }

    /**
     * A Model stub that always accept the applicant being added.
     */
    private class ModelStubAcceptingApplicantAdded extends ModelStub {
        final ArrayList<Applicant> applicantsAdded = new ArrayList<>();

        @Override
        public boolean hasApplicant(Applicant applicant) {
            requireNonNull(applicant);
            return applicantsAdded.stream().anyMatch(applicant::isSameApplicant);
        }

        @Override
        public void addApplicant(Applicant applicant) {
            requireNonNull(applicant);
            applicantsAdded.add(applicant);
        }

        @Override
        public ReadOnlyTrackAScholar getTrackAScholar() {
            return new TrackAScholar();
        }
    }

}
