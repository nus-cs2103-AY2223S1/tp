package seedu.phu.model.internship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_REMARK_BLACKROCK;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_TAG_TRANSPORT;
import static seedu.phu.testutil.Assert.assertThrows;
import static seedu.phu.testutil.TypicalInternships.AMAZON;
import static seedu.phu.testutil.TypicalInternships.BLACKROCK;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.phu.model.internship.exceptions.DuplicateInternshipException;
import seedu.phu.model.internship.exceptions.InternshipNotFoundException;
import seedu.phu.testutil.InternshipBuilder;

public class UniqueInternshipListTest {

    private final UniqueInternshipList uniqueInternshipList = new UniqueInternshipList();

    @Test
    public void contains_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueInternshipList.contains(null));
    }

    @Test
    public void contains_internshipNotInList_returnsFalse() {
        assertFalse(uniqueInternshipList.contains(AMAZON));
    }

    @Test
    public void contains_internshipInList_returnsTrue() {
        uniqueInternshipList.add(AMAZON);
        assertTrue(uniqueInternshipList.contains(AMAZON));
    }

    @Test
    public void contains_internshipWithSameIdentityFieldsInList_returnsTrue() {
        uniqueInternshipList.add(AMAZON);
        Internship editedAmazon = new InternshipBuilder(AMAZON).withRemark(VALID_REMARK_BLACKROCK)
                .withTags(VALID_TAG_TRANSPORT)
                .build();
        assertTrue(uniqueInternshipList.contains(editedAmazon));
    }

    @Test
    public void add_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueInternshipList.add(null));
    }

    @Test
    public void add_duplicateInternship_throwsDuplicateInternshipException() {
        uniqueInternshipList.add(AMAZON);
        assertThrows(DuplicateInternshipException.class, () -> uniqueInternshipList.add(AMAZON));
    }

    @Test
    public void setInternship_nullTargetInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueInternshipList.setInternship(null, AMAZON));
    }

    @Test
    public void setInternship_nullEditedInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueInternshipList.setInternship(AMAZON, null));
    }

    @Test
    public void setInternship_targetInternshipNotInList_throwsInternshipNotFoundException() {
        assertThrows(InternshipNotFoundException.class, () -> uniqueInternshipList.setInternship(AMAZON, AMAZON));
    }

    @Test
    public void setInternship_editedInternshipIsSameInternship_success() {
        uniqueInternshipList.add(AMAZON);
        uniqueInternshipList.setInternship(AMAZON, AMAZON);
        UniqueInternshipList expectedUniqueInternshipList = new UniqueInternshipList();
        expectedUniqueInternshipList.add(AMAZON);
        assertEquals(expectedUniqueInternshipList, uniqueInternshipList);
    }

    @Test
    public void setInternship_editedInternshipHasSameIdentity_success() {
        uniqueInternshipList.add(AMAZON);
        Internship editedAmazon = new InternshipBuilder(AMAZON).withRemark(VALID_REMARK_BLACKROCK)
                .withTags(VALID_TAG_TRANSPORT)
                .build();
        uniqueInternshipList.setInternship(AMAZON, editedAmazon);
        UniqueInternshipList expectedUniqueInternshipList = new UniqueInternshipList();
        expectedUniqueInternshipList.add(editedAmazon);
        assertEquals(expectedUniqueInternshipList, uniqueInternshipList);
    }

    @Test
    public void setInternship_editedInternshipHasDifferentIdentity_success() {
        uniqueInternshipList.add(AMAZON);
        uniqueInternshipList.setInternship(AMAZON, BLACKROCK);
        UniqueInternshipList expectedUniqueInternshipList = new UniqueInternshipList();
        expectedUniqueInternshipList.add(BLACKROCK);
        assertEquals(expectedUniqueInternshipList, uniqueInternshipList);
    }

    @Test
    public void setInternship_editedInternshipHasNonUniqueIdentity_throwsDuplicateInternshipException() {
        uniqueInternshipList.add(AMAZON);
        uniqueInternshipList.add(BLACKROCK);
        assertThrows(DuplicateInternshipException.class, () -> uniqueInternshipList.setInternship(AMAZON, BLACKROCK));
    }

    @Test
    public void remove_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueInternshipList.remove(null));
    }

    @Test
    public void remove_internshipDoesNotExist_throwsInternshipNotFoundException() {
        assertThrows(InternshipNotFoundException.class, () -> uniqueInternshipList.remove(AMAZON));
    }

    @Test
    public void remove_existingInternship_removesInternship() {
        uniqueInternshipList.add(AMAZON);
        uniqueInternshipList.remove(AMAZON);
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
        uniqueInternshipList.add(AMAZON);
        UniqueInternshipList expectedUniqueInternshipList = new UniqueInternshipList();
        expectedUniqueInternshipList.add(BLACKROCK);
        uniqueInternshipList.setInternships(expectedUniqueInternshipList);
        assertEquals(expectedUniqueInternshipList, uniqueInternshipList);
    }

    @Test
    public void setInternships_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueInternshipList.setInternships((List<Internship>) null));
    }

    @Test
    public void setInternships_list_replacesOwnListWithProvidedList() {
        uniqueInternshipList.add(AMAZON);
        List<Internship> internshipList = Collections.singletonList(BLACKROCK);
        uniqueInternshipList.setInternships(internshipList);
        UniqueInternshipList expectedUniqueInternshipList = new UniqueInternshipList();
        expectedUniqueInternshipList.add(BLACKROCK);
        assertEquals(expectedUniqueInternshipList, uniqueInternshipList);
    }

    @Test
    public void setInternships_listWithDuplicateInternships_throwsDuplicateInternshipException() {
        List<Internship> listWithDuplicateInternships = Arrays.asList(AMAZON, AMAZON);
        assertThrows(DuplicateInternshipException.class, ()
                -> uniqueInternshipList.setInternships(listWithDuplicateInternships));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueInternshipList.asUnmodifiableObservableList().remove(0));
    }
}
