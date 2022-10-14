package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDeliverers.ALICE;
import static seedu.address.testutil.TypicalDeliverers.BENSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.testutil.PersonBuilder;

public class UniqueDelivererListTest {
    private final UniqueDelivererList uniqueDelivererList = new UniqueDelivererList();

    @Test
    public void contains_nullDeliverer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDelivererList.contains(null));
    }

    @Test
    public void contains_DelivererNotInList_returnsFalse() {
        assertFalse(uniqueDelivererList.contains(ALICE));
    }

    @Test
    public void contains_delivererInList_returnsTrue() {
        uniqueDelivererList.add(ALICE);
        assertTrue(uniqueDelivererList.contains(ALICE));
    }

    @Test
    public void contains_delivererWithSameIdentityFieldsInList_returnsTrue() {
        uniqueDelivererList.add(ALICE);
        Deliverer editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .buildDeliverer();
        assertTrue(uniqueDelivererList.contains(editedAlice));
    }

    @Test
    public void add_nullDeliverer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDelivererList.add(null));
    }

    @Test
    public void add_duplicateDeliverer_throwsDuplicatePersonException() {
        uniqueDelivererList.add(ALICE);
        assertThrows(DuplicatePersonException.class, () -> uniqueDelivererList.add(ALICE));
    }

    @Test
    public void setDeliverer_nullTargetDeliverer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDelivererList.setPerson(null, ALICE));
    }

    @Test
    public void setDeliverer_nullEditedDeliverer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDelivererList.setPerson(ALICE, null));
    }

    @Test
    public void setDeliverer_targetDelivererNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueDelivererList.setPerson(ALICE, ALICE));
    }

    @Test
    public void setDeliverer_editedDelivererIsSameDeliverer_success() {
        uniqueDelivererList.add(ALICE);
        uniqueDelivererList.setPerson(ALICE, ALICE);
        UniqueDelivererList expectedUniqueDelivererList = new UniqueDelivererList();
        expectedUniqueDelivererList.add(ALICE);
        assertEquals(expectedUniqueDelivererList, uniqueDelivererList);
    }

    @Test
    public void setDeliverer_editedDelivererHasSameIdentity_success() {
        uniqueDelivererList.add(ALICE);
        Deliverer editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .buildDeliverer();
        uniqueDelivererList.setPerson(ALICE, editedAlice);
        UniqueDelivererList expectedUniqueDelivererList = new UniqueDelivererList();
        expectedUniqueDelivererList.add(editedAlice);
        assertEquals(expectedUniqueDelivererList, uniqueDelivererList);
    }

    @Test
    public void setDeliverer_editedPDelivererHasDifferentIdentity_success() {
        uniqueDelivererList.add(ALICE);
        uniqueDelivererList.setPerson(ALICE, BENSON);
        UniqueDelivererList expectedUniqueDelivererList = new UniqueDelivererList();
        expectedUniqueDelivererList.add(BENSON);
        assertEquals(expectedUniqueDelivererList, uniqueDelivererList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueDelivererList.add(ALICE);
        uniqueDelivererList.add(BENSON);
        assertThrows(DuplicatePersonException.class, () -> uniqueDelivererList.setPerson(ALICE, BENSON));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDelivererList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueDelivererList.remove(ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueDelivererList.add(ALICE);
        uniqueDelivererList.remove(ALICE);
        UniqueDelivererList expectedUniqueDelivererList = new UniqueDelivererList();
        assertEquals(expectedUniqueDelivererList, uniqueDelivererList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDelivererList.setPersons((UniquePersonList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueDelivererList.add(ALICE);
        UniqueDelivererList expectedUniqueDelivererList = new UniqueDelivererList();
        expectedUniqueDelivererList.add(BENSON);
        uniqueDelivererList.setPersons(expectedUniqueDelivererList);
        assertEquals(expectedUniqueDelivererList, uniqueDelivererList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDelivererList.setPersons((List<Deliverer>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniqueDelivererList.add(ALICE);
        List<Deliverer> personList = Collections.singletonList(BENSON);
        uniqueDelivererList.setPersons(personList);
        UniqueDelivererList expectedUniqueDelivererList = new UniqueDelivererList();
        expectedUniqueDelivererList.add(BENSON);
        assertEquals(expectedUniqueDelivererList, uniqueDelivererList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Deliverer> listWithDuplicatePersons = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicatePersonException.class, () -> uniqueDelivererList.setPersons(listWithDuplicatePersons));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueDelivererList.asUnmodifiableObservableList().remove(0));
    }
}
