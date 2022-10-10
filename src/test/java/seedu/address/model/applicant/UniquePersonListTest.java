package seedu.address.model.applicant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHOLARSHIP_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplicants.ALICE;
import static seedu.address.testutil.TypicalApplicants.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.applicant.exceptions.DuplicateApplicantException;
import seedu.address.model.applicant.exceptions.ApplicantNotFoundException;
import seedu.address.testutil.ApplicantBuilder;

public class UniquePersonListTest {

    private final UniqueApplicantList uniqueApplicantList = new UniqueApplicantList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicantList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueApplicantList.contains(ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueApplicantList.add(ALICE);
        assertTrue(uniqueApplicantList.contains(ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueApplicantList.add(ALICE);
        Applicant editedAlice = new ApplicantBuilder(ALICE).withScholarship(VALID_SCHOLARSHIP_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueApplicantList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicantList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicateApplicantException() {
        uniqueApplicantList.add(ALICE);
        assertThrows(DuplicateApplicantException.class, () -> uniqueApplicantList.add(ALICE));
    }

    @Test
    public void setApplicant_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicantList.setApplicant(null, ALICE));
    }

    @Test
    public void setApplicant_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicantList.setApplicant(ALICE, null));
    }

    @Test
    public void setApplicant_targetPersonNotInList_throwsApplicantNotFoundException() {
        assertThrows(ApplicantNotFoundException.class, () -> uniqueApplicantList.setApplicant(ALICE, ALICE));
    }

    @Test
    public void setApplicant_editedPersonIsSamePerson_success() {
        uniqueApplicantList.add(ALICE);
        uniqueApplicantList.setApplicant(ALICE, ALICE);
        UniqueApplicantList expectedUniqueApplicantList = new UniqueApplicantList();
        expectedUniqueApplicantList.add(ALICE);
        assertEquals(expectedUniqueApplicantList, uniqueApplicantList);
    }

    @Test
    public void setApplicant_editedPersonHasSameIdentity_success() {
        uniqueApplicantList.add(ALICE);
        Applicant editedAlice = new ApplicantBuilder(ALICE).withScholarship(VALID_SCHOLARSHIP_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueApplicantList.setApplicant(ALICE, editedAlice);
        UniqueApplicantList expectedUniqueApplicantList = new UniqueApplicantList();
        expectedUniqueApplicantList.add(editedAlice);
        assertEquals(expectedUniqueApplicantList, uniqueApplicantList);
    }

    @Test
    public void setApplicant_editedPersonHasDifferentIdentity_success() {
        uniqueApplicantList.add(ALICE);
        uniqueApplicantList.setApplicant(ALICE, BOB);
        UniqueApplicantList expectedUniqueApplicantList = new UniqueApplicantList();
        expectedUniqueApplicantList.add(BOB);
        assertEquals(expectedUniqueApplicantList, uniqueApplicantList);
    }

    @Test
    public void setApplicant_editedPersonHasNonUniqueIdentity_throwsDuplicateApplicantException() {
        uniqueApplicantList.add(ALICE);
        uniqueApplicantList.add(BOB);
        assertThrows(DuplicateApplicantException.class, () -> uniqueApplicantList.setApplicant(ALICE, BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicantList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsApplicantNotFoundException() {
        assertThrows(ApplicantNotFoundException.class, () -> uniqueApplicantList.remove(ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueApplicantList.add(ALICE);
        uniqueApplicantList.remove(ALICE);
        UniqueApplicantList expectedUniqueApplicantList = new UniqueApplicantList();
        assertEquals(expectedUniqueApplicantList, uniqueApplicantList);
    }

    @Test
    public void setApplicants_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicantList.setApplicants((UniqueApplicantList) null));
    }

    @Test
    public void setApplicants_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
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
    public void setApplicants_listWithDuplicatePersons_throwsDuplicateApplicantException() {
        List<Applicant> listWithDuplicateApplicants = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateApplicantException.class, () -> uniqueApplicantList.setApplicants(listWithDuplicateApplicants));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueApplicantList.asUnmodifiableObservableList().remove(0));
    }
}
