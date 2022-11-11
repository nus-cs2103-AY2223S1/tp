package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTeammates.ALICE;
import static seedu.address.testutil.TypicalTeammates.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.teammate.Teammate;
import seedu.address.model.teammate.exceptions.DuplicateTeammateException;
import seedu.address.testutil.TeammateBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getTeammateList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateTeammates_throwsDuplicateTeammateException() {
        // Two Teammates with the same identity fields
        Teammate editedAlice = new TeammateBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Teammate> newTeammates = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newTeammates);

        assertThrows(DuplicateTeammateException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasTeammate_nullTeammate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasTeammate(null));
    }

    @Test
    public void hasTeammate_teammateNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasTeammate(ALICE));
    }

    @Test
    public void hasTeammate_teammateInAddressBook_returnsTrue() {
        addressBook.addTeammate(ALICE);
        assertTrue(addressBook.hasTeammate(ALICE));
    }

    @Test
    public void hasTeammate_teammateWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addTeammate(ALICE);
        Teammate editedAlice = new TeammateBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasTeammate(editedAlice));
    }

    @Test
    public void getTeammateList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getTeammateList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose Teammates list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Teammate> teammates = FXCollections.observableArrayList();

        AddressBookStub(Collection<Teammate> teammates) {
            this.teammates.setAll(teammates);
        }

        @Override
        public ObservableList<Teammate> getTeammateList() {
            return teammates;
        }
    }

}
