package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.APPLE;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.testutil.FoodBuilder;

public class UniqueFoodListTest {

    private final UniqueFoodList uniqueFoodList = new UniqueFoodList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueFoodList.contains(APPLE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueFoodList.add(APPLE);
        assertTrue(uniqueFoodList.contains(APPLE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueFoodList.add(APPLE);
        Food editedAlice = new FoodBuilder(APPLE).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(uniqueFoodList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueFoodList.add(APPLE);
        assertThrows(DuplicatePersonException.class, () -> uniqueFoodList.add(APPLE));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.setPerson(null, APPLE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.setPerson(APPLE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueFoodList.setPerson(APPLE, APPLE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniqueFoodList.add(APPLE);
        uniqueFoodList.setPerson(APPLE, APPLE);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        expectedUniqueFoodList.add(APPLE);
        assertEquals(expectedUniqueFoodList, uniqueFoodList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniqueFoodList.add(APPLE);
        Food editedAlice = new FoodBuilder(APPLE).withTags(VALID_TAG_HUSBAND).build();
        uniqueFoodList.setPerson(APPLE, editedAlice);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        expectedUniqueFoodList.add(editedAlice);
        assertEquals(expectedUniqueFoodList, uniqueFoodList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniqueFoodList.add(APPLE);
        uniqueFoodList.setPerson(APPLE, BOB);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        expectedUniqueFoodList.add(BOB);
        assertEquals(expectedUniqueFoodList, uniqueFoodList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueFoodList.add(APPLE);
        uniqueFoodList.add(BOB);
        assertThrows(DuplicatePersonException.class, () -> uniqueFoodList.setPerson(APPLE, BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueFoodList.remove(APPLE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueFoodList.add(APPLE);
        uniqueFoodList.remove(APPLE);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        assertEquals(expectedUniqueFoodList, uniqueFoodList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.setPersons((UniqueFoodList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueFoodList.add(APPLE);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        expectedUniqueFoodList.add(BOB);
        uniqueFoodList.setPersons(expectedUniqueFoodList);
        assertEquals(expectedUniqueFoodList, uniqueFoodList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.setPersons((List<Food>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniqueFoodList.add(APPLE);
        List<Food> foodList = Collections.singletonList(BOB);
        uniqueFoodList.setPersons(foodList);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        expectedUniqueFoodList.add(BOB);
        assertEquals(expectedUniqueFoodList, uniqueFoodList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Food> listWithDuplicateFoods = Arrays.asList(APPLE, APPLE);
        assertThrows(DuplicatePersonException.class, () -> uniqueFoodList.setPersons(listWithDuplicateFoods));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueFoodList.asUnmodifiableObservableList().remove(0));
    }
}
