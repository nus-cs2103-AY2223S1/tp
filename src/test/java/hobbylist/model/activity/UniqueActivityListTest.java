package hobbylist.model.activity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static hobbylist.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static hobbylist.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static hobbylist.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import hobbylist.testutil.Assert;
import hobbylist.testutil.PersonBuilder;
import hobbylist.testutil.TypicalPersons;
import hobbylist.model.activity.exceptions.DuplicateActivityException;
import hobbylist.model.activity.exceptions.ActivityNotFoundException;

public class UniqueActivityListTest {

    private final UniqueActivityList uniqueActivityList = new UniqueActivityList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueActivityList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueActivityList.contains(TypicalPersons.ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueActivityList.add(TypicalPersons.ALICE);
        assertTrue(uniqueActivityList.contains(TypicalPersons.ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueActivityList.add(TypicalPersons.ALICE);
        Activity editedAlice = new PersonBuilder(TypicalPersons.ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueActivityList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueActivityList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueActivityList.add(TypicalPersons.ALICE);
        Assert.assertThrows(DuplicateActivityException.class, () -> uniqueActivityList.add(TypicalPersons.ALICE));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueActivityList.setPerson(null, TypicalPersons.ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueActivityList.setPerson(TypicalPersons.ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        Assert.assertThrows(ActivityNotFoundException.class, () -> uniqueActivityList.setPerson(TypicalPersons.ALICE, TypicalPersons.ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniqueActivityList.add(TypicalPersons.ALICE);
        uniqueActivityList.setPerson(TypicalPersons.ALICE, TypicalPersons.ALICE);
        UniqueActivityList expectedUniqueActivityList = new UniqueActivityList();
        expectedUniqueActivityList.add(TypicalPersons.ALICE);
        assertEquals(expectedUniqueActivityList, uniqueActivityList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniqueActivityList.add(TypicalPersons.ALICE);
        Activity editedAlice = new PersonBuilder(TypicalPersons.ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueActivityList.setPerson(TypicalPersons.ALICE, editedAlice);
        UniqueActivityList expectedUniqueActivityList = new UniqueActivityList();
        expectedUniqueActivityList.add(editedAlice);
        assertEquals(expectedUniqueActivityList, uniqueActivityList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniqueActivityList.add(TypicalPersons.ALICE);
        uniqueActivityList.setPerson(TypicalPersons.ALICE, TypicalPersons.BOB);
        UniqueActivityList expectedUniqueActivityList = new UniqueActivityList();
        expectedUniqueActivityList.add(TypicalPersons.BOB);
        assertEquals(expectedUniqueActivityList, uniqueActivityList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueActivityList.add(TypicalPersons.ALICE);
        uniqueActivityList.add(TypicalPersons.BOB);
        Assert.assertThrows(DuplicateActivityException.class, () -> uniqueActivityList.setPerson(TypicalPersons.ALICE, TypicalPersons.BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueActivityList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        Assert.assertThrows(ActivityNotFoundException.class, () -> uniqueActivityList.remove(TypicalPersons.ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueActivityList.add(TypicalPersons.ALICE);
        uniqueActivityList.remove(TypicalPersons.ALICE);
        UniqueActivityList expectedUniqueActivityList = new UniqueActivityList();
        assertEquals(expectedUniqueActivityList, uniqueActivityList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueActivityList.setPersons((UniqueActivityList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueActivityList.add(TypicalPersons.ALICE);
        UniqueActivityList expectedUniqueActivityList = new UniqueActivityList();
        expectedUniqueActivityList.add(TypicalPersons.BOB);
        uniqueActivityList.setPersons(expectedUniqueActivityList);
        assertEquals(expectedUniqueActivityList, uniqueActivityList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniqueActivityList.setPersons((List<Activity>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniqueActivityList.add(TypicalPersons.ALICE);
        List<Activity> activityList = Collections.singletonList(TypicalPersons.BOB);
        uniqueActivityList.setPersons(activityList);
        UniqueActivityList expectedUniqueActivityList = new UniqueActivityList();
        expectedUniqueActivityList.add(TypicalPersons.BOB);
        assertEquals(expectedUniqueActivityList, uniqueActivityList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Activity> listWithDuplicateActivities = Arrays.asList(TypicalPersons.ALICE, TypicalPersons.ALICE);
        Assert.assertThrows(DuplicateActivityException.class, () -> uniqueActivityList.setPersons(listWithDuplicateActivities));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, ()
            -> uniqueActivityList.asUnmodifiableObservableList().remove(0));
    }
}
