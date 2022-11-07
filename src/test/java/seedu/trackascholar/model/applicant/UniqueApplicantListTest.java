package seedu.trackascholar.model.applicant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackascholar.logic.commands.CommandTestUtil.VALID_MAJOR_COMPUTER_SCIENCE;
import static seedu.trackascholar.logic.commands.CommandTestUtil.VALID_SCHOLARSHIP_BOB;
import static seedu.trackascholar.testutil.Assert.assertThrows;
import static seedu.trackascholar.testutil.TypicalApplicants.ALICE;
import static seedu.trackascholar.testutil.TypicalApplicants.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.trackascholar.model.applicant.exceptions.ApplicantNotFoundException;
import seedu.trackascholar.model.applicant.exceptions.DuplicateApplicantException;
import seedu.trackascholar.testutil.ApplicantBuilder;

public class UniqueApplicantListTest {

    private final UniqueApplicantList uniqueApplicantList = new UniqueApplicantList();

    @Test
    public void contains_nullApplicant_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicantList.contains(null));
    }

    @Test
    public void contains_applicantNotInList_returnsFalse() {
        assertFalse(uniqueApplicantList.contains(ALICE));
    }

    @Test
    public void contains_applicantInList_returnsTrue() {
        uniqueApplicantList.add(ALICE);
        assertTrue(uniqueApplicantList.contains(ALICE));
    }

    @Test
    public void contains_applicantWithSameIdentityFieldsInList_returnsTrue() {
        uniqueApplicantList.add(ALICE);
        Applicant editedAlice = new ApplicantBuilder(ALICE).withScholarship(VALID_SCHOLARSHIP_BOB)
                .withMajors(VALID_MAJOR_COMPUTER_SCIENCE).build();
        assertTrue(uniqueApplicantList.contains(editedAlice));
    }

    @Test
    public void add_nullApplicant_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicantList.add(null));
    }

    @Test
    public void add_duplicateApplicant_throwsDuplicateApplicantException() {
        uniqueApplicantList.add(ALICE);
        assertThrows(DuplicateApplicantException.class, () -> uniqueApplicantList.add(ALICE));
    }

    @Test
    public void setApplicant_nullTargetApplicant_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicantList.setApplicant(null, ALICE));
    }

    @Test
    public void setApplicant_nullEditedApplicant_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicantList.setApplicant(ALICE, null));
    }

    @Test
    public void setApplicant_targetApplicantNotInList_throwsApplicantNotFoundException() {
        assertThrows(ApplicantNotFoundException.class, () -> uniqueApplicantList.setApplicant(ALICE, ALICE));
    }

    @Test
    public void setApplicant_editedApplicantIsSameApplicant_success() {
        uniqueApplicantList.add(ALICE);
        uniqueApplicantList.setApplicant(ALICE, ALICE);
        UniqueApplicantList expectedUniqueApplicantList = new UniqueApplicantList();
        expectedUniqueApplicantList.add(ALICE);
        assertEquals(expectedUniqueApplicantList, uniqueApplicantList);
    }

    @Test
    public void setApplicant_editedApplicantHasSameIdentity_success() {
        uniqueApplicantList.add(ALICE);
        Applicant editedAlice = new ApplicantBuilder(ALICE).withScholarship(VALID_SCHOLARSHIP_BOB)
                .withMajors(VALID_MAJOR_COMPUTER_SCIENCE).build();
        uniqueApplicantList.setApplicant(ALICE, editedAlice);
        UniqueApplicantList expectedUniqueApplicantList = new UniqueApplicantList();
        expectedUniqueApplicantList.add(editedAlice);
        assertEquals(expectedUniqueApplicantList, uniqueApplicantList);
    }

    @Test
    public void setApplicant_editedApplicantHasDifferentIdentity_success() {
        uniqueApplicantList.add(ALICE);
        uniqueApplicantList.setApplicant(ALICE, BOB);
        UniqueApplicantList expectedUniqueApplicantList = new UniqueApplicantList();
        expectedUniqueApplicantList.add(BOB);
        assertEquals(expectedUniqueApplicantList, uniqueApplicantList);
    }

    @Test
    public void setApplicant_editedApplicantHasNonUniqueIdentity_throwsDuplicateApplicantException() {
        uniqueApplicantList.add(ALICE);
        uniqueApplicantList.add(BOB);
        assertThrows(DuplicateApplicantException.class, () -> uniqueApplicantList.setApplicant(ALICE, BOB));
    }

    @Test
    public void remove_nullApplicant_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicantList.remove(null));
    }

    @Test
    public void remove_applicantDoesNotExist_throwsApplicantNotFoundException() {
        assertThrows(ApplicantNotFoundException.class, () -> uniqueApplicantList.remove(ALICE));
    }

    @Test
    public void remove_existingApplicant_removesApplicant() {
        uniqueApplicantList.add(ALICE);
        uniqueApplicantList.remove(ALICE);
        UniqueApplicantList expectedUniqueApplicantList = new UniqueApplicantList();
        assertEquals(expectedUniqueApplicantList, uniqueApplicantList);
    }

    @Test
    public void setApplicants_nullUniqueApplicantList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicantList.setApplicants((UniqueApplicantList) null));
    }

    @Test
    public void setApplicants_uniqueApplicantList_replacesOwnListWithProvidedUniqueApplicantList() {
        uniqueApplicantList.add(ALICE);
        UniqueApplicantList expectedUniqueApplicantList = new UniqueApplicantList();
        expectedUniqueApplicantList.add(BOB);
        uniqueApplicantList.setApplicants(expectedUniqueApplicantList);
        assertEquals(expectedUniqueApplicantList, uniqueApplicantList);
    }

    @Test
    public void setApplicants_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicantList.setApplicants((List<Applicant>) null));
    }

    @Test
    public void setApplicants_list_replacesOwnListWithProvidedList() {
        uniqueApplicantList.add(ALICE);
        List<Applicant> applicantList = Collections.singletonList(BOB);
        uniqueApplicantList.setApplicants(applicantList);
        UniqueApplicantList expectedUniqueApplicantList = new UniqueApplicantList();
        expectedUniqueApplicantList.add(BOB);
        assertEquals(expectedUniqueApplicantList, uniqueApplicantList);
    }

    @Test
    public void setApplicants_listWithDuplicateApplicants_throwsDuplicateApplicantException() {
        List<Applicant> listWithDuplicateApplicants = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateApplicantException.class, () ->
                uniqueApplicantList.setApplicants(listWithDuplicateApplicants));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueApplicantList.asUnmodifiableObservableList().remove(0));
    }
}
