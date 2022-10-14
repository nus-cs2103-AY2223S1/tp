package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProfiles.ALICE;
import static seedu.address.testutil.TypicalProfiles.AMY;
import static seedu.address.testutil.TypicalProfiles.BENSON;
import static seedu.address.testutil.TypicalProfiles.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.profile.Profile;
import seedu.address.model.profile.exceptions.SimilarProfileException;
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
    public void resetData_withSimilarNames_throwsSimilarProfileException() {
        // Two profiles with similar name
        Profile editedAlice = new ProfileBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Profile> newProfiles = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newProfiles);

        assertThrows(SimilarProfileException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void resetData_withSimilarEmails_throwsSimilarProfileException() {
        // Two profiles with the similar email (different name)
        Profile editedAliceName = new ProfileBuilder(ALICE).withName(VALID_NAME_AMY).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Profile> newProfiles = Arrays.asList(ALICE, editedAliceName);
        AddressBookStub newData = new AddressBookStub(newProfiles);

        assertThrows(SimilarProfileException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasName_nullName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasName(null));
    }

    @Test
    public void hasEmail_nullEmail_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasEmail(null));
    }

    @Test
    public void hasName_nameNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasName(ALICE));
    }

    @Test
    public void hasName_nameInAddressBook_returnsTrue() {
        addressBook.addProfile(ALICE);
        assertTrue(addressBook.hasName(ALICE));
    }

    @Test
    public void hasEmail_emailNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasEmail(ALICE));
    }

    @Test
    public void hasEmail_emailInAddressBook_returnsTrue() {
        addressBook.addProfile(ALICE);
        assertTrue(addressBook.hasEmail(ALICE));
    }

    @Test
    public void hasProfile_profileWithSameNameFieldsInAddressBook_returnsTrue() {
        addressBook.addProfile(ALICE);
        Profile editedAlice = new ProfileBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasName(editedAlice));
    }

    @Test
    public void hasProfile_profileWithSameEmailFieldsInAddressBook_returnsTrue() {
        addressBook.addProfile(AMY);
        Profile editedBenson = new ProfileBuilder(BENSON).withEmail(VALID_EMAIL_AMY)
                .build();
        assertTrue(addressBook.hasEmail(editedBenson));
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
