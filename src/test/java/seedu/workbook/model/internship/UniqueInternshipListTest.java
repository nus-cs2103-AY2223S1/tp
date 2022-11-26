package seedu.workbook.model.internship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.workbook.testutil.Assert.assertThrows;
import static seedu.workbook.testutil.TypicalInternships.ALICE;
import static seedu.workbook.testutil.TypicalInternships.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.workbook.model.internship.exceptions.DuplicateInternshipException;
import seedu.workbook.model.internship.exceptions.InternshipNotFoundException;
import seedu.workbook.testutil.InternshipBuilder;

public class UniqueInternshipListTest {

    private final UniqueInternshipList uniqueInternshipList = new UniqueInternshipList();

    @Test
    public void contains_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueInternshipList.contains(null));
    }

    @Test
    public void contains_internshipNotInList_returnsFalse() {
        assertFalse(uniqueInternshipList.contains(ALICE));
    }

    @Test
    public void contains_internshipInList_returnsTrue() {
        uniqueInternshipList.add(ALICE);
        assertTrue(uniqueInternshipList.contains(ALICE));
    }

    @Test
    public void contains_internshipWithSameIdentityFieldsInList_returnsTrue() {
        uniqueInternshipList.add(ALICE);
        Internship editedAlice = new InternshipBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueInternshipList.contains(editedAlice));
    }

    @Test
    public void add_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueInternshipList.add(null));
    }

    @Test
    public void add_duplicateInternship_throwsDuplicateInternshipException() {
        uniqueInternshipList.add(ALICE);
        assertThrows(DuplicateInternshipException.class, () -> uniqueInternshipList.add(ALICE));
    }

    @Test
    public void setInternship_nullTargetInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueInternshipList.setInternship(null, ALICE));
    }

    @Test
    public void setInternship_nullEditedInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueInternshipList.setInternship(ALICE, null));
    }

    @Test
    public void setInternship_targetInternshipNotInList_throwsInternshipNotFoundException() {
        assertThrows(InternshipNotFoundException.class, () -> uniqueInternshipList.setInternship(ALICE, ALICE));
    }

    @Test
    public void setInternship_editedInternshipIsSameInternship_success() {
        uniqueInternshipList.add(ALICE);
        uniqueInternshipList.setInternship(ALICE, ALICE);
        UniqueInternshipList expectedUniqueInternshipList = new UniqueInternshipList();
        expectedUniqueInternshipList.add(ALICE);
        assertEquals(expectedUniqueInternshipList, uniqueInternshipList);
    }

    @Test
    public void setInternship_editedInternshipHasSameIdentity_success() {
        uniqueInternshipList.add(ALICE);
        Internship editedAlice = new InternshipBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueInternshipList.setInternship(ALICE, editedAlice);
        UniqueInternshipList expectedUniqueInternshipList = new UniqueInternshipList();
        expectedUniqueInternshipList.add(editedAlice);
        assertEquals(expectedUniqueInternshipList, uniqueInternshipList);
    }

    @Test
    public void setInternship_editedInternshipHasDifferentIdentity_success() {
        uniqueInternshipList.add(ALICE);
        uniqueInternshipList.setInternship(ALICE, BOB);
        UniqueInternshipList expectedUniqueInternshipList = new UniqueInternshipList();
        expectedUniqueInternshipList.add(BOB);
        assertEquals(expectedUniqueInternshipList, uniqueInternshipList);
    }

    @Test
    public void setInternship_editedInternshipHasNonUniqueIdentity_throwsDuplicateInternshipException() {
        uniqueInternshipList.add(ALICE);
        uniqueInternshipList.add(BOB);
        assertThrows(DuplicateInternshipException.class, () -> uniqueInternshipList.setInternship(ALICE, BOB));
    }

    @Test
    public void remove_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueInternshipList.remove(null));
    }

    @Test
    public void remove_internshipDoesNotExist_throwsInternshipNotFoundException() {
        assertThrows(InternshipNotFoundException.class, () -> uniqueInternshipList.remove(ALICE));
    }

    @Test
    public void remove_existingInternship_removesInternship() {
        uniqueInternshipList.add(ALICE);
        uniqueInternshipList.remove(ALICE);
        UniqueInternshipList expectedUniqueInternshipList = new UniqueInternshipList();
        assertEquals(expectedUniqueInternshipList, uniqueInternshipList);
    }

    // CHECKSTYLE.OFF: SeparatorWrap
    @Test
    public void setInternships_nullUniqueInternshipList_throwsNullPointerException() {
        assertThrows(
            NullPointerException.class,
            () -> uniqueInternshipList.setInternships((UniqueInternshipList) null)
        );
    }
    // CHECKSTYLE.ON: SeparatorWrap

    @Test
    public void setInternships_uniqueInternshipList_replacesOwnListWithProvidedUniqueInternshipList() {
        uniqueInternshipList.add(ALICE);
        UniqueInternshipList expectedUniqueInternshipList = new UniqueInternshipList();
        expectedUniqueInternshipList.add(BOB);
        uniqueInternshipList.setInternships(expectedUniqueInternshipList);
        assertEquals(expectedUniqueInternshipList, uniqueInternshipList);
    }

    @Test
    public void setInternships_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueInternshipList.setInternships((List<Internship>) null));
    }

    @Test
    public void setInternships_list_replacesOwnListWithProvidedList() {
        uniqueInternshipList.add(ALICE);
        List<Internship> internshipList = Collections.singletonList(BOB);
        uniqueInternshipList.setInternships(internshipList);
        UniqueInternshipList expectedUniqueInternshipList = new UniqueInternshipList();
        expectedUniqueInternshipList.add(BOB);
        assertEquals(expectedUniqueInternshipList, uniqueInternshipList);
    }

    // CHECKSTYLE.OFF: SeparatorWrap
    @Test
    public void setInternships_listWithDuplicateInternships_throwsDuplicateInternshipException() {
        List<Internship> listWithDuplicateInternships = Arrays.asList(ALICE, ALICE);
        assertThrows(
            DuplicateInternshipException.class,
            () -> uniqueInternshipList.setInternships(listWithDuplicateInternships)
        );
    }
    // CHECKSTYLE.ON: SeparatorWrap

    // CHECKSTYLE.OFF: SeparatorWrap
    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(
            UnsupportedOperationException.class,
            () -> uniqueInternshipList.asUnmodifiableObservableList().remove(0)
        );
    }
    // CHECKSTYLE.ON: SeparatorWrap
}
