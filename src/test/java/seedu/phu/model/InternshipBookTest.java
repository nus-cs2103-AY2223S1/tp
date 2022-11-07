package seedu.phu.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_REMARK_BLACKROCK;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_TAG_TRANSPORT;
import static seedu.phu.testutil.Assert.assertThrows;
import static seedu.phu.testutil.TypicalInternships.AMAZON;
import static seedu.phu.testutil.TypicalInternships.getTypicalInternshipBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.phu.model.internship.Internship;
import seedu.phu.model.internship.exceptions.DuplicateInternshipException;
import seedu.phu.testutil.InternshipBuilder;


public class InternshipBookTest {

    private final InternshipBook internshipBook = new InternshipBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), internshipBook.getInternshipList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> internshipBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyInternshipBook_replacesData() {
        InternshipBook newData = getTypicalInternshipBook();
        internshipBook.resetData(newData);
        assertEquals(newData, internshipBook);
    }

    @Test
    public void resetData_withDuplicateInternships_throwsDuplicateInternshipException() {
        // Two internships with the same identity fields
        Internship editedAmazon = new InternshipBuilder(AMAZON).withRemark(VALID_REMARK_BLACKROCK)
                .withTags(VALID_TAG_TRANSPORT)
                .build();
        List<Internship> newInternships = Arrays.asList(AMAZON, editedAmazon);
        InternshipBookStub newData = new InternshipBookStub(newInternships);

        assertThrows(DuplicateInternshipException.class, () -> internshipBook.resetData(newData));
    }

    @Test
    public void hasInternship_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> internshipBook.hasInternship(null));
    }

    @Test
    public void hasInternship_internshipNotInInternshipBook_returnsFalse() {
        assertFalse(internshipBook.hasInternship(AMAZON));
    }

    @Test
    public void hasInternship_internshipInInternshipBook_returnsTrue() {
        internshipBook.addInternship(AMAZON);
        assertTrue(internshipBook.hasInternship(AMAZON));
    }

    @Test
    public void hasInternship_internshipWithSameIdentityFieldsInInternshipBook_returnsTrue() {
        internshipBook.addInternship(AMAZON);
        Internship editedAmazon = new InternshipBuilder(AMAZON).withRemark(VALID_REMARK_BLACKROCK)
                .withTags(VALID_TAG_TRANSPORT)
                .build();
        assertTrue(internshipBook.hasInternship(editedAmazon));
    }

    @Test
    public void getInternshipList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> internshipBook.getInternshipList().remove(0));
    }

    /**
     * A stub ReadOnlyInternshipBook whose internships list can violate interface constraints.
     */
    private static class InternshipBookStub implements ReadOnlyInternshipBook {
        private final ObservableList<Internship> internships = FXCollections.observableArrayList();

        InternshipBookStub(Collection<Internship> internships) {
            this.internships.setAll(internships);
        }

        @Override
        public ObservableList<Internship> getInternshipList() {
            return internships;
        }

        @Override
        public void sortInternshipList(Comparator<Internship> comparator) {
            internships.sort(comparator);
        }

        @Override
        public void reverseList() {
            Collections.reverse(internships);
        }
    }

}
