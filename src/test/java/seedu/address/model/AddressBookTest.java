package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_AMY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProfiles.ALICE;
import static seedu.address.testutil.TypicalProfiles.AMY;
import static seedu.address.testutil.TypicalProfiles.BENSON;
import static seedu.address.testutil.TypicalProfiles.BOB;
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
    public void resetData_withSimilarEmails_throwsSimilarProfileException() {
        // Two profiles with the similar email
        Profile bobWithAmyEmail = new ProfileBuilder(BOB).withEmail(VALID_EMAIL_AMY).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Profile> newProfiles = Arrays.asList(AMY, bobWithAmyEmail);
        AddressBookStub newData = new AddressBookStub(newProfiles);

        assertThrows(SimilarProfileException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void resetData_withSimilarPhones_throwsSimilarProfileException() {
        // Two profiles with the similar phone
        Profile bobWithAmyPhone = new ProfileBuilder(BOB).withPhone(VALID_PHONE_AMY).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Profile> newProfiles = Arrays.asList(AMY, bobWithAmyPhone);
        AddressBookStub newData = new AddressBookStub(newProfiles);

        assertThrows(SimilarProfileException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void resetData_withSimilarTelegrams_throwsSimilarProfileException() {
        // Two profiles with the similar telegram
        Profile bobWithAmyTelegram = new ProfileBuilder(BOB).withTelegram(VALID_TELEGRAM_AMY).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Profile> newProfiles = Arrays.asList(AMY, bobWithAmyTelegram);
        AddressBookStub newData = new AddressBookStub(newProfiles);

        assertThrows(SimilarProfileException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasEmail_nullEmail_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasEmail(null));
    }

    @Test
    public void hasPhone_nullEmail_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPhone(null));
    }

    @Test
    public void hasTelegram_nullEmail_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasTelegram(null));
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
    public void hasPhone_phoneNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPhone(ALICE));
    }

    @Test
    public void hasPhone_phoneInAddressBook_returnsTrue() {
        addressBook.addProfile(ALICE);
        assertTrue(addressBook.hasPhone(ALICE));
    }

    @Test
    public void hasTelegram_telegramNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasTelegram(ALICE));
    }

    @Test
    public void hasTelegram_telegramInAddressBook_returnsTrue() {
        addressBook.addProfile(ALICE);
        assertTrue(addressBook.hasTelegram(ALICE));
    }

    @Test
    public void hasProfile_profileWithSameEmailFieldsInAddressBook_returnsTrue() {
        addressBook.addProfile(AMY);
        Profile editedBenson = new ProfileBuilder(BENSON).withEmail(VALID_EMAIL_AMY)
                .build();
        assertTrue(addressBook.hasEmail(editedBenson));
    }

    @Test
    public void hasProfile_profileWithSamePhoneFieldsInAddressBook_returnsTrue() {
        addressBook.addProfile(AMY);
        Profile editedBenson = new ProfileBuilder(BENSON).withPhone(VALID_PHONE_AMY)
                .build();
        assertTrue(addressBook.hasPhone(editedBenson));
    }

    @Test
    public void hasProfile_profileWithSameTelegramFieldsInAddressBook_returnsTrue() {
        addressBook.addProfile(AMY);
        Profile editedBenson = new ProfileBuilder(BENSON).withTelegram(VALID_TELEGRAM_AMY)
                .build();
        assertTrue(addressBook.hasTelegram(editedBenson));
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
