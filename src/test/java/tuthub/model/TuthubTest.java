package tuthub.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tuthub.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static tuthub.testutil.Assert.assertThrows;
import static tuthub.testutil.TypicalTutors.ALICE;
import static tuthub.testutil.TypicalTutors.getTypicalTuthub;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tuthub.model.tutor.Tutor;
import tuthub.model.tutor.exceptions.DuplicateTutorException;
import tuthub.testutil.TutorBuilder;

public class TuthubTest {

    private final Tuthub tuthub = new Tuthub();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), tuthub.getTutorList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tuthub.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTuthub_replacesData() {
        Tuthub newData = getTypicalTuthub();
        tuthub.resetData(newData);
        assertEquals(newData, tuthub);
    }

    @Test
    public void resetData_withDuplicateTutors_throwsDuplicateTutorException() {
        // Two tutors with the same identity fields
        Tutor editedAlice = new TutorBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Tutor> newTutors = Arrays.asList(ALICE, editedAlice);
        TuthubStub newData = new TuthubStub(newTutors);

        assertThrows(DuplicateTutorException.class, () -> tuthub.resetData(newData));
    }

    @Test
    public void hasTutor_nullTutor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tuthub.hasTutor(null));
    }

    @Test
    public void hasTutor_tutorNotInTuthub_returnsFalse() {
        assertFalse(tuthub.hasTutor(ALICE));
    }

    @Test
    public void hasTutor_tutorInTuthub_returnsTrue() {
        tuthub.addTutor(ALICE);
        assertTrue(tuthub.hasTutor(ALICE));
    }

    @Test
    public void hasTutor_tutorWithSameIdentityFieldsInTuthub_returnsTrue() {
        tuthub.addTutor(ALICE);
        Tutor editedAlice = new TutorBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(tuthub.hasTutor(editedAlice));
    }

    @Test
    public void getTutorList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> tuthub.getTutorList().remove(0));
    }

    /**
     * A stub ReadOnlyTuthub whose tutors list can violate interface constraints.
     */
    private static class TuthubStub implements ReadOnlyTuthub {
        private final ObservableList<Tutor> tutors = FXCollections.observableArrayList();

        TuthubStub(Collection<Tutor> tutors) {
            this.tutors.setAll(tutors);
        }

        @Override
        public ObservableList<Tutor> getTutorList() {
            return tutors;
        }
    }

}
