package seedu.trackascholar.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackascholar.logic.commands.CommandTestUtil.VALID_MAJOR_COMPUTER_SCIENCE;
import static seedu.trackascholar.logic.commands.CommandTestUtil.VALID_SCHOLARSHIP_BOB;
import static seedu.trackascholar.testutil.Assert.assertThrows;
import static seedu.trackascholar.testutil.TypicalApplicants.ALICE;
import static seedu.trackascholar.testutil.TypicalApplicants.getTypicalTrackAScholar;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.trackascholar.model.applicant.Applicant;
import seedu.trackascholar.model.applicant.exceptions.DuplicateApplicantException;
import seedu.trackascholar.testutil.ApplicantBuilder;

public class TrackAScholarTest {

    private final TrackAScholar trackAScholar = new TrackAScholar();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), trackAScholar.getApplicantList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> trackAScholar.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTrackAScholar_replacesData() {
        TrackAScholar newData = getTypicalTrackAScholar();
        trackAScholar.resetData(newData);
        assertEquals(newData, trackAScholar);
    }

    @Test
    public void resetData_withDuplicateApplicants_throwsDuplicateApplicantException() {
        // Two applicants with the same identity fields
        Applicant editedAlice = new ApplicantBuilder(ALICE).withScholarship(VALID_SCHOLARSHIP_BOB)
                .withMajors(VALID_MAJOR_COMPUTER_SCIENCE).build();
        List<Applicant> newApplicants = Arrays.asList(ALICE, editedAlice);
        TrackAScholarStub newData = new TrackAScholarStub(newApplicants);

        assertThrows(DuplicateApplicantException.class, () -> trackAScholar.resetData(newData));
    }

    @Test
    public void hasApplicant_nullApplicant_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> trackAScholar.hasApplicant(null));
    }

    @Test
    public void hasApplicant_applicantNotInTrackAScholar_returnsFalse() {
        assertFalse(trackAScholar.hasApplicant(ALICE));
    }

    @Test
    public void hasApplicant_applicantInTrackAScholar_returnsTrue() {
        trackAScholar.addApplicant(ALICE);
        assertTrue(trackAScholar.hasApplicant(ALICE));
    }

    @Test
    public void hasApplicant_applicantWithSameIdentityFieldsInTrackAScholar_returnsTrue() {
        trackAScholar.addApplicant(ALICE);
        Applicant editedAlice = new ApplicantBuilder(ALICE).withScholarship(VALID_SCHOLARSHIP_BOB)
                .withMajors(VALID_MAJOR_COMPUTER_SCIENCE).build();
        assertTrue(trackAScholar.hasApplicant(editedAlice));
    }

    @Test
    public void getApplicantList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> trackAScholar.getApplicantList().remove(0));
    }

    /**
     * A stub ReadOnlyTrackAScholar whose applicants list can violate interface constraints.
     */
    private static class TrackAScholarStub implements ReadOnlyTrackAScholar {
        private final ObservableList<Applicant> applicants = FXCollections.observableArrayList();

        TrackAScholarStub(Collection<Applicant> applicants) {
            this.applicants.setAll(applicants);
        }

        @Override
        public ObservableList<Applicant> getApplicantList() {
            return applicants;
        }
    }

}
