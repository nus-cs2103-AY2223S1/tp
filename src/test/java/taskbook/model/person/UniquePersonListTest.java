package taskbook.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static taskbook.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static taskbook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import taskbook.model.person.exceptions.DuplicatePersonException;
import taskbook.model.person.exceptions.PersonNotFoundException;
import taskbook.testutil.Assert;
import taskbook.testutil.PersonBuilder;
import taskbook.testutil.TypicalTaskBook;

public class UniquePersonListTest {

    private final UniquePersonList uniquePersonList = new UniquePersonList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniquePersonList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniquePersonList.contains(TypicalTaskBook.ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniquePersonList.add(TypicalTaskBook.ALICE);
        assertTrue(uniquePersonList.contains(TypicalTaskBook.ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniquePersonList.add(TypicalTaskBook.ALICE);
        Person editedAlice = new PersonBuilder(TypicalTaskBook.ALICE)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniquePersonList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniquePersonList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniquePersonList.add(TypicalTaskBook.ALICE);
        Assert.assertThrows(DuplicatePersonException.class, () -> uniquePersonList.add(TypicalTaskBook.ALICE));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, ()
                -> uniquePersonList.setPerson(null, TypicalTaskBook.ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, ()
                -> uniquePersonList.setPerson(TypicalTaskBook.ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        Assert.assertThrows(PersonNotFoundException.class, ()
                -> uniquePersonList.setPerson(TypicalTaskBook.ALICE, TypicalTaskBook.ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniquePersonList.add(TypicalTaskBook.ALICE);
        uniquePersonList.setPerson(TypicalTaskBook.ALICE, TypicalTaskBook.ALICE);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(TypicalTaskBook.ALICE);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniquePersonList.add(TypicalTaskBook.ALICE);
        Person editedAlice = new PersonBuilder(TypicalTaskBook.ALICE)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniquePersonList.setPerson(TypicalTaskBook.ALICE, editedAlice);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(editedAlice);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniquePersonList.add(TypicalTaskBook.ALICE);
        uniquePersonList.setPerson(TypicalTaskBook.ALICE, TypicalTaskBook.BOB);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(TypicalTaskBook.BOB);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniquePersonList.add(TypicalTaskBook.ALICE);
        uniquePersonList.add(TypicalTaskBook.BOB);
        Assert.assertThrows(DuplicatePersonException.class, ()
                -> uniquePersonList.setPerson(TypicalTaskBook.ALICE, TypicalTaskBook.BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniquePersonList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        Assert.assertThrows(PersonNotFoundException.class, () -> uniquePersonList.remove(TypicalTaskBook.ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniquePersonList.add(TypicalTaskBook.ALICE);
        uniquePersonList.remove(TypicalTaskBook.ALICE);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniquePersonList.setPersons((UniquePersonList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniquePersonList.add(TypicalTaskBook.ALICE);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(TypicalTaskBook.BOB);
        uniquePersonList.setPersons(expectedUniquePersonList);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniquePersonList.setPersons((List<Person>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniquePersonList.add(TypicalTaskBook.ALICE);
        List<Person> personList = Collections.singletonList(TypicalTaskBook.BOB);
        uniquePersonList.setPersons(personList);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(TypicalTaskBook.BOB);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Person> listWithDuplicatePersons = Arrays.asList(TypicalTaskBook.ALICE, TypicalTaskBook.ALICE);
        Assert.assertThrows(DuplicatePersonException.class, ()
                -> uniquePersonList.setPersons(listWithDuplicatePersons));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, ()
            -> uniquePersonList.asUnmodifiableObservableList().remove(0));
    }
}
