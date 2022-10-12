package seedu.workbook.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.workbook.testutil.Assert.assertThrows;
import static seedu.workbook.testutil.TypicalInternships.ALICE;
import static seedu.workbook.testutil.TypicalInternships.getTypicalWorkBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.workbook.model.internship.Internship;
import seedu.workbook.model.internship.exceptions.DuplicateInternshipException;
import seedu.workbook.testutil.InternshipBuilder;

public class WorkBookTest {

    private final WorkBook workBook = new WorkBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), workBook.getInternshipList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> workBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyWorkBook_replacesData() {
        WorkBook newData = getTypicalWorkBook();
        workBook.resetData(newData);
        assertEquals(newData, workBook);
    }

    @Test
    public void resetData_withDuplicateInternships_throwsDuplicateInternshipException() {
        // Two internships with the same identity fields
        Internship editedAlice = new InternshipBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Internship> newInternships = Arrays.asList(ALICE, editedAlice);
        WorkBookStub newData = new WorkBookStub(newInternships);

        assertThrows(DuplicateInternshipException.class, () -> workBook.resetData(newData));
    }

    @Test
    public void hasInternship_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> workBook.hasInternship(null));
    }

    @Test
    public void hasInternship_internshipNotInWorkBook_returnsFalse() {
        assertFalse(workBook.hasInternship(ALICE));
    }

    @Test
    public void hasInternship_internshipInWorkBook_returnsTrue() {
        workBook.addInternship(ALICE);
        assertTrue(workBook.hasInternship(ALICE));
    }

    @Test
    public void hasInternship_internshipWithSameIdentityFieldsInWorkBook_returnsTrue() {
        workBook.addInternship(ALICE);
        Internship editedAlice = new InternshipBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(workBook.hasInternship(editedAlice));
    }

    @Test
    public void getInternshipList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> workBook.getInternshipList().remove(0));
    }

    /**
     * A stub ReadOnlyWorkBook whose internships list can violate interface constraints.
     */
    private static class WorkBookStub implements ReadOnlyWorkBook {
        private final ObservableList<Internship> internships = FXCollections.observableArrayList();

        WorkBookStub(Collection<Internship> internships) {
            this.internships.setAll(internships);
        }

        @Override
        public ObservableList<Internship> getInternshipList() {
            return internships;
        }
    }

}
