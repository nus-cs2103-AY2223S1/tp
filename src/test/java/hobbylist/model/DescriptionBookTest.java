package hobbylist.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static hobbylist.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static hobbylist.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static hobbylist.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import hobbylist.testutil.Assert;
import hobbylist.testutil.PersonBuilder;
import hobbylist.testutil.TypicalPersons;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import hobbylist.model.activity.Activity;
import hobbylist.model.activity.exceptions.DuplicateActivityException;

public class DescriptionBookTest {

    private final HobbyList hobbyList = new HobbyList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), hobbyList.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> hobbyList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        HobbyList newData = TypicalPersons.getTypicalAddressBook();
        hobbyList.resetData(newData);
        assertEquals(newData, hobbyList);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Activity editedAlice = new PersonBuilder(TypicalPersons.ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Activity> newActivities = Arrays.asList(TypicalPersons.ALICE, editedAlice);
        HobbyListStub newData = new HobbyListStub(newActivities);

        Assert.assertThrows(DuplicateActivityException.class, () -> hobbyList.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> hobbyList.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(hobbyList.hasPerson(TypicalPersons.ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        hobbyList.addPerson(TypicalPersons.ALICE);
        assertTrue(hobbyList.hasPerson(TypicalPersons.ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        hobbyList.addPerson(TypicalPersons.ALICE);
        Activity editedAlice = new PersonBuilder(TypicalPersons.ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(hobbyList.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, () -> hobbyList.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class HobbyListStub implements ReadOnlyHobbyList {
        private final ObservableList<Activity> activities = FXCollections.observableArrayList();

        HobbyListStub(Collection<Activity> activities) {
            this.activities.setAll(activities);
        }

        @Override
        public ObservableList<Activity> getPersonList() {
            return activities;
        }
    }

}
