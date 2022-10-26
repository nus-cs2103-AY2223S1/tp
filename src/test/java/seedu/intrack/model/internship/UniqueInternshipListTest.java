package seedu.intrack.model.internship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_TAG_URGENT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_WEBSITE_MSFT;
import static seedu.intrack.testutil.Assert.assertThrows;
import static seedu.intrack.testutil.TypicalInternships.GOOG;
import static seedu.intrack.testutil.TypicalInternships.META;
import static seedu.intrack.testutil.TypicalInternships.MSFT;
import static seedu.intrack.testutil.TypicalInternships.SSNLF;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.intrack.model.tag.exceptions.DuplicateInternshipException;
import seedu.intrack.model.tag.exceptions.InternshipNotFoundException;
import seedu.intrack.testutil.InternshipBuilder;

public class UniqueInternshipListTest {

    private final UniqueInternshipList uniqueInternshipList = new UniqueInternshipList();

    @Test
    public void contains_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueInternshipList.contains(null));
    }

    @Test
    public void contains_internshipNotInList_returnsFalse() {
        assertFalse(uniqueInternshipList.contains(GOOG));
    }

    @Test
    public void contains_internshipInList_returnsTrue() {
        uniqueInternshipList.add(GOOG);
        assertTrue(uniqueInternshipList.contains(GOOG));
    }

    @Test
    public void contains_internshipWithSameIdentityFieldsInList_returnsTrue() {
        uniqueInternshipList.add(GOOG);
        Internship editedGoogle = new InternshipBuilder(GOOG).withWebsite(VALID_WEBSITE_MSFT).withTags(VALID_TAG_URGENT)
                .build();
        assertTrue(uniqueInternshipList.contains(editedGoogle));
    }

    @Test
    public void add_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueInternshipList.add(null));
    }

    @Test
    public void add_duplicateInternship_throwsDuplicateInternshipException() {
        uniqueInternshipList.add(GOOG);
        assertThrows(DuplicateInternshipException.class, () -> uniqueInternshipList.add(GOOG));
    }

    @Test
    public void setInternship_nullTargetInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueInternshipList.setInternship(null, GOOG));
    }

    @Test
    public void setInternship_nullEditedInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueInternshipList.setInternship(GOOG, null));
    }

    @Test
    public void setInternship_targetInternshipNotInList_throwsInternshipNotFoundException() {
        assertThrows(InternshipNotFoundException.class, () -> uniqueInternshipList.setInternship(GOOG, GOOG));
    }

    @Test
    public void setInternship_editedInternshipIsSameInternship_success() {
        uniqueInternshipList.add(GOOG);
        uniqueInternshipList.setInternship(GOOG, GOOG);
        UniqueInternshipList expectedUniqueInternshipList = new UniqueInternshipList();
        expectedUniqueInternshipList.add(GOOG);
        assertEquals(expectedUniqueInternshipList, uniqueInternshipList);
    }

    @Test
    public void setInternship_editedInternshipHasSameIdentity_success() {
        uniqueInternshipList.add(GOOG);
        Internship editedGoogle = new InternshipBuilder(GOOG).withWebsite(VALID_WEBSITE_MSFT).withTags(VALID_TAG_URGENT)
                .build();
        uniqueInternshipList.setInternship(GOOG, editedGoogle);
        UniqueInternshipList expectedUniqueInternshipList = new UniqueInternshipList();
        expectedUniqueInternshipList.add(editedGoogle);
        assertEquals(expectedUniqueInternshipList, uniqueInternshipList);
    }

    @Test
    public void setInternship_editedInternshipHasDifferentIdentity_success() {
        uniqueInternshipList.add(GOOG);
        uniqueInternshipList.setInternship(GOOG, MSFT);
        UniqueInternshipList expectedUniqueInternshipList = new UniqueInternshipList();
        expectedUniqueInternshipList.add(MSFT);
        assertEquals(expectedUniqueInternshipList, uniqueInternshipList);
    }

    @Test
    public void setInternship_editedInternshipHasNonUniqueIdentity_throwsDuplicateInternshipException() {
        uniqueInternshipList.add(GOOG);
        uniqueInternshipList.add(MSFT);
        assertThrows(DuplicateInternshipException.class, () -> uniqueInternshipList.setInternship(GOOG, MSFT));
    }

    @Test
    public void remove_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueInternshipList.remove(null));
    }

    @Test
    public void remove_internshipDoesNotExist_throwsInternshipNotFoundException() {
        assertThrows(InternshipNotFoundException.class, () -> uniqueInternshipList.remove(GOOG));
    }

    @Test
    public void remove_existingInternship_removesInternship() {
        uniqueInternshipList.add(GOOG);
        uniqueInternshipList.remove(GOOG);
        UniqueInternshipList expectedUniqueInternshipList = new UniqueInternshipList();
        assertEquals(expectedUniqueInternshipList, uniqueInternshipList);
    }

    @Test
    public void setInternships_nullUniqueInternshipList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
                -> uniqueInternshipList.setInternships((UniqueInternshipList) null));
    }

    @Test
    public void setInternships_uniqueInternshipList_replacesOwnListWithProvidedUniqueInternshipList() {
        uniqueInternshipList.add(GOOG);
        UniqueInternshipList expectedUniqueInternshipList = new UniqueInternshipList();
        expectedUniqueInternshipList.add(MSFT);
        uniqueInternshipList.setInternships(expectedUniqueInternshipList);
        assertEquals(expectedUniqueInternshipList, uniqueInternshipList);
    }

    @Test
    public void setInternships_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueInternshipList.setInternships((List<Internship>) null));
    }

    @Test
    public void setInternships_list_replacesOwnListWithProvidedList() {
        uniqueInternshipList.add(GOOG);
        List<Internship> internshipList = Collections.singletonList(MSFT);
        uniqueInternshipList.setInternships(internshipList);
        UniqueInternshipList expectedUniqueInternshipList = new UniqueInternshipList();
        expectedUniqueInternshipList.add(MSFT);
        assertEquals(expectedUniqueInternshipList, uniqueInternshipList);
    }

    @Test
    public void setInternships_listWithDuplicateInternships_throwsDuplicateInternshipException() {
        List<Internship> listWithDuplicateInternships = Arrays.asList(GOOG, GOOG);
        assertThrows(DuplicateInternshipException.class, ()
                -> uniqueInternshipList.setInternships(listWithDuplicateInternships));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueInternshipList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void sortInternshipsAscending_list() {
        //proper order should be A, B ,E in ascending order
        uniqueInternshipList.add(GOOG);
        uniqueInternshipList.add(SSNLF);
        uniqueInternshipList.add(META);
        uniqueInternshipList.dateSortAscending();
        assertTrue(uniqueInternshipList.contains(GOOG));
        assertTrue(uniqueInternshipList.contains(META));
        assertTrue(uniqueInternshipList.contains(SSNLF));
    }

    @Test
    public void sortInternshipsDescending_list() {
        //proper order should be A, B ,E in ascending order
        uniqueInternshipList.add(GOOG);
        uniqueInternshipList.add(SSNLF);
        uniqueInternshipList.add(META);
        uniqueInternshipList.dateSortDescending();
        assertTrue(uniqueInternshipList.contains(GOOG));
        assertTrue(uniqueInternshipList.contains(META));
        assertTrue(uniqueInternshipList.contains(SSNLF));
    }
}
