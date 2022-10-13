package seedu.intrack.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.intrack.testutil.Assert.assertThrows;
import static seedu.intrack.testutil.TypicalInternships.ALICE;
import static seedu.intrack.testutil.TypicalInternships.getTypicalInTrack;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.intrack.model.internship.Internship;
import seedu.intrack.model.tag.exceptions.DuplicateInternshipException;
import seedu.intrack.testutil.InternshipBuilder;

public class InTrackTest {

    private final InTrack inTrack = new InTrack();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), inTrack.getInternshipList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> inTrack.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyInTrack_replacesData() {
        InTrack newData = getTypicalInTrack();
        inTrack.resetData(newData);
        assertEquals(newData, inTrack);
    }

    @Test
    public void resetData_withDuplicateInternships_throwsDuplicateInternshipException() {
        // Two internships with the same identity fields
        Internship editedAlice = new InternshipBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Internship> newInternships = Arrays.asList(ALICE, editedAlice);
        InTrackStub newData = new InTrackStub(newInternships);

        assertThrows(DuplicateInternshipException.class, () -> inTrack.resetData(newData));
    }

    @Test
    public void hasInternship_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> inTrack.hasInternship(null));
    }

    @Test
    public void hasInternship_internshipNotInInTrack_returnsFalse() {
        assertFalse(inTrack.hasInternship(ALICE));
    }

    @Test
    public void hasInternship_internshipInInTrack_returnsTrue() {
        inTrack.addInternship(ALICE);
        assertTrue(inTrack.hasInternship(ALICE));
    }

    @Test
    public void hasInternship_internshipWithSameIdentityFieldsInInTrack_returnsTrue() {
        inTrack.addInternship(ALICE);
        Internship editedAlice = new InternshipBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(inTrack.hasInternship(editedAlice));
    }

    @Test
    public void getInternshipList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> inTrack.getInternshipList().remove(0));
    }

    /**
     * A stub ReadOnlyInTrack whose internships list can violate interface constraints.
     */
    private static class InTrackStub implements ReadOnlyInTrack {
        private final ObservableList<Internship> internships = FXCollections.observableArrayList();

        InTrackStub(Collection<Internship> internships) {
            this.internships.setAll(internships);
        }

        @Override
        public ObservableList<Internship> getInternshipList() {
            return internships;
        }
    }

}
