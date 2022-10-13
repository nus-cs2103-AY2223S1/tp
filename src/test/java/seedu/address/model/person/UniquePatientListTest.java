package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.testutil.PersonBuilder;

public class UniquePatientListTest {

    private final UniquePatientList uniquePatientList = new UniquePatientList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniquePatientList.contains(ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniquePatientList.add(ALICE);
        assertTrue(uniquePatientList.contains(ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniquePatientList.add(ALICE);
        Patient editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniquePatientList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniquePatientList.add(ALICE);
        assertThrows(DuplicatePersonException.class, () -> uniquePatientList.add(ALICE));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.setPerson(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.setPerson(ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniquePatientList.setPerson(ALICE, ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniquePatientList.add(ALICE);
        uniquePatientList.setPerson(ALICE, ALICE);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        expectedUniquePatientList.add(ALICE);
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniquePatientList.add(ALICE);
        Patient editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniquePatientList.setPerson(ALICE, editedAlice);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        expectedUniquePatientList.add(editedAlice);
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniquePatientList.add(ALICE);
        uniquePatientList.setPerson(ALICE, BOB);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        expectedUniquePatientList.add(BOB);
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniquePatientList.add(ALICE);
        uniquePatientList.add(BOB);
        assertThrows(DuplicatePersonException.class, () -> uniquePatientList.setPerson(ALICE, BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniquePatientList.remove(ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniquePatientList.add(ALICE);
        uniquePatientList.remove(ALICE);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.setPersons((UniquePatientList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniquePatientList.add(ALICE);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        expectedUniquePatientList.add(BOB);
        uniquePatientList.setPersons(expectedUniquePatientList);
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.setPersons((List<Patient>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniquePatientList.add(ALICE);
        List<Patient> patientList = Collections.singletonList(BOB);
        uniquePatientList.setPersons(patientList);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        expectedUniquePatientList.add(BOB);
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Patient> listWithDuplicatePatients = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicatePersonException.class, () -> uniquePatientList.setPersons(listWithDuplicatePatients));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniquePatientList.asUnmodifiableObservableList().remove(0));
    }
}
