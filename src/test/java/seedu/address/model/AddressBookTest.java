package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProfiles.ALICE;
import static seedu.address.testutil.TypicalProfiles.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.profile.Profile;
import seedu.address.model.profile.exceptions.DuplicateProfileException;
import seedu.address.testutil.ProfileBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getProfileList());
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
    public void resetData_withDuplicateProfiles_throwsDuplicateProfileException() {
        // Two profiles with the same identity fields
        Profile editedAlice = new ProfileBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Profile> newProfiles = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newProfiles);

        assertThrows(DuplicateProfileException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasProfile_nullProfile_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasProfile(null));
    }

    @Test
    public void hasProfile_profileNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasProfile(ALICE));
    }

    @Test
    public void hasProfile_profileInAddressBook_returnsTrue() {
        addressBook.addProfile(ALICE);
        assertTrue(addressBook.hasProfile(ALICE));
    }

    @Test
    public void hasProfile_profileWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addProfile(ALICE);
        Profile editedAlice = new ProfileBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasProfile(editedAlice));
    }

    @Test
    public void getProfileList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getProfileList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose profiles list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Profile> profiles = FXCollections.observableArrayList();

        AddressBookStub(Collection<Profile> profiles) {
            this.profiles.setAll(profiles);
        }

        @Override
        public ObservableList<Profile> getProfileList() {
            return profiles;
        }
    }

}
