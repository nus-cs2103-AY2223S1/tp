package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLIED_DATE_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ECOMMERCE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInternships.ALIBABA;
import static seedu.address.testutil.TypicalInternships.getTypicalFindMyIntern;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.exceptions.DuplicateInternshipException;
import seedu.address.testutil.InternshipBuilder;

public class FindMyInternTest {

    private final FindMyIntern findMyIntern = new FindMyIntern();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), findMyIntern.getInternshipList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> findMyIntern.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyFindMyIntern_replacesData() {
        FindMyIntern newData = getTypicalFindMyIntern();
        findMyIntern.resetData(newData);
        assertEquals(newData, findMyIntern);
    }

    @Test
    public void resetData_withDuplicateInternships_throwsDuplicateInternshipException() {
        // Two internships with the same identity fields
        Internship editedAlibaba =
                new InternshipBuilder(ALIBABA).withAppliedDate(VALID_APPLIED_DATE_TIKTOK)
                        .withTags(VALID_TAG_ECOMMERCE).build();
        List<Internship> newInternships = Arrays.asList(ALIBABA, editedAlibaba);
        FindMyInternStub newData = new FindMyInternStub(newInternships);

        assertThrows(DuplicateInternshipException.class, () -> findMyIntern.resetData(newData));
    }

    @Test
    public void hasInternship_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> findMyIntern.hasInternship(null));
    }

    @Test
    public void hasInternship_internshipNotInFindMyIntern_returnsFalse() {
        assertFalse(findMyIntern.hasInternship(ALIBABA));
    }

    @Test
    public void hasInternship_internshipInFindMyIntern_returnsTrue() {
        findMyIntern.addInternship(ALIBABA);
        assertTrue(findMyIntern.hasInternship(ALIBABA));
    }

    @Test
    public void hasInternship_internshipWithSameFieldsInFindMyIntern_returnsTrue() {
        findMyIntern.addInternship(ALIBABA);
        Internship editedAlibaba = new InternshipBuilder(ALIBABA)
                .withAppliedDate(VALID_APPLIED_DATE_TIKTOK).withTags(VALID_TAG_ECOMMERCE).build();
        assertTrue(findMyIntern.hasInternship(editedAlibaba));
    }

    @Test
    public void getInternshipList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> findMyIntern.getInternshipList().remove(0));
    }

    /**
     * A stub ReadOnlyFindMyIntern whose internships list can violate interface constraints.
     */
    private static class FindMyInternStub implements ReadOnlyFindMyIntern {
        private final ObservableList<Internship> internships = FXCollections.observableArrayList();

        FindMyInternStub(Collection<Internship> internships) {
            this.internships.setAll(internships);
        }

        @Override
        public ObservableList<Internship> getInternshipList() {
            return internships;
        }
    }

}
