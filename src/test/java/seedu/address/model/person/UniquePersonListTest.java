package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.testutil.PersonBuilder;

public class UniquePersonListTest {

    private final UniquePersonList uniquePersonList = new UniquePersonList();


    //=========== contains() Tests ============================================================================

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniquePersonList.contains(ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniquePersonList.add(ALICE);
        assertTrue(uniquePersonList.contains(ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniquePersonList.add(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertTrue(uniquePersonList.contains(editedAlice));
    }


    //=========== add() Tests =================================================================================

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniquePersonList.add(ALICE);
        assertThrows(DuplicatePersonException.class, () -> uniquePersonList.add(ALICE));
    }


    //=========== setPerson() Tests ===========================================================================

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.setPerson(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.setPerson(ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniquePersonList.setPerson(ALICE, ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniquePersonList.add(ALICE);
        uniquePersonList.setPerson(ALICE, ALICE);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(ALICE);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniquePersonList.add(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        uniquePersonList.setPerson(ALICE, editedAlice);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(editedAlice);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniquePersonList.add(ALICE);
        uniquePersonList.setPerson(ALICE, BOB);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(BOB);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniquePersonList.add(ALICE);
        uniquePersonList.add(BOB);
        assertThrows(DuplicatePersonException.class, () -> uniquePersonList.setPerson(ALICE, BOB));
    }


    //=========== remove() Tests ==============================================================================

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniquePersonList.remove(ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniquePersonList.add(ALICE);
        uniquePersonList.remove(ALICE);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }


    //=========== setPersons() Tests ==========================================================================

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.setPersons((UniquePersonList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniquePersonList.add(ALICE);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(BOB);
        uniquePersonList.setPersons(expectedUniquePersonList);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.setPersons((List<Person>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniquePersonList.add(ALICE);
        List<Person> personList = Collections.singletonList(BOB);
        uniquePersonList.setPersons(personList);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(BOB);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Person> listWithDuplicatePersons = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicatePersonException.class, () -> uniquePersonList.setPersons(listWithDuplicatePersons));
    }


    //=========== sort() Tests ================================================================================

    @Test
    public void sort_nullSortField_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.sort(null));
    }

    @Test
    public void sort_sortFieldNoField_success() {
        PersonSortField sortFieldNoField = PersonSortField.sortByNoField();

        // Add in persons in unsorted order
        uniquePersonList.add(BOB);
        uniquePersonList.add(ALICE);
        uniquePersonList.add(ELLE);
        uniquePersonList.add(DANIEL);
        uniquePersonList.add(CARL);
        uniquePersonList.add(FIONA);

        // Sorting by no field should not change the order
        uniquePersonList.sort(sortFieldNoField);

        // Expected list is the same as the actual list
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(BOB);
        expectedUniquePersonList.add(ALICE);
        expectedUniquePersonList.add(ELLE);
        expectedUniquePersonList.add(DANIEL);
        expectedUniquePersonList.add(CARL);
        expectedUniquePersonList.add(FIONA);

        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void sort_sortFieldName_success() {
        PersonSortField sortFieldName = PersonSortField.createSortField("n");

        // Add in persons in unsorted order by name
        uniquePersonList.add(BOB);
        uniquePersonList.add(ALICE);
        uniquePersonList.add(ELLE);
        uniquePersonList.add(DANIEL);
        uniquePersonList.add(FIONA);
        uniquePersonList.add(CARL);

        uniquePersonList.sort(sortFieldName);

        // Expected list contains persons sorted by name
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(ALICE);
        expectedUniquePersonList.add(BOB);
        expectedUniquePersonList.add(CARL);
        expectedUniquePersonList.add(DANIEL);
        expectedUniquePersonList.add(ELLE);
        expectedUniquePersonList.add(FIONA);

        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void sort_sortFieldGender_success() {
        PersonSortField sortFieldGender = PersonSortField.createSortField("g");

        // Add in persons in unsorted order by gender
        uniquePersonList.add(BOB);
        uniquePersonList.add(ALICE);
        uniquePersonList.add(ELLE);
        uniquePersonList.add(DANIEL);
        uniquePersonList.add(FIONA);
        uniquePersonList.add(CARL);

        uniquePersonList.sort(sortFieldGender);

        // Expected list contains persons sorted by gender
        // Sorting should be stable so relative order among males and females should remain
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(ALICE);
        expectedUniquePersonList.add(FIONA);
        expectedUniquePersonList.add(BOB);
        expectedUniquePersonList.add(ELLE);
        expectedUniquePersonList.add(DANIEL);
        expectedUniquePersonList.add(CARL);

        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void sort_sortFieldDob_success() {
        PersonSortField sortFieldDob = PersonSortField.createSortField("d");

        // Add in persons in unsorted order by DOB
        uniquePersonList.add(BOB);
        uniquePersonList.add(ALICE);
        uniquePersonList.add(ELLE);
        uniquePersonList.add(DANIEL);
        uniquePersonList.add(FIONA);
        uniquePersonList.add(CARL);

        uniquePersonList.sort(sortFieldDob);

        // Expected list contains persons sorted by DOB
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(ELLE); // 03/12/1953
        expectedUniquePersonList.add(ALICE); // 13/09/1960
        expectedUniquePersonList.add(CARL); // 20/06/1979
        expectedUniquePersonList.add(FIONA); // 31/10/1984
        expectedUniquePersonList.add(BOB); // 8/12/2000
        expectedUniquePersonList.add(DANIEL); // 16/07/2006

        assertEquals(expectedUniquePersonList, uniquePersonList);
    }


    //=========== asUnmodifiableObservableList() Tests ========================================================

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniquePersonList.asUnmodifiableObservableList().remove(0));
    }
}
