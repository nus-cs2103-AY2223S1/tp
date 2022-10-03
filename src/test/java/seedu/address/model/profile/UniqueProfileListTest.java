package seedu.address.model.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProfiles.ALICE;
import static seedu.address.testutil.TypicalProfiles.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.profile.exceptions.DuplicateProfileException;
import seedu.address.model.profile.exceptions.ProfileNotFoundException;
import seedu.address.testutil.ProfileBuilder;

public class UniqueProfileListTest {

    private final UniqueProfileList uniqueProfileList = new UniqueProfileList();

    @Test
    public void contains_nullProfile_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProfileList.contains(null));
    }

    @Test
    public void contains_profileNotInList_returnsFalse() {
        assertFalse(uniqueProfileList.contains(ALICE));
    }

    @Test
    public void contains_profileInList_returnsTrue() {
        uniqueProfileList.add(ALICE);
        assertTrue(uniqueProfileList.contains(ALICE));
    }

    @Test
    public void contains_profileWithSameIdentityFieldsInList_returnsTrue() {
        uniqueProfileList.add(ALICE);
        Profile editedAlice = new ProfileBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .build();
        assertTrue(uniqueProfileList.contains(editedAlice));
    }

    @Test
    public void add_nullProfile_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProfileList.add(null));
    }

    @Test
    public void add_duplicateProfile_throwsDuplicateProfileException() {
        uniqueProfileList.add(ALICE);
        assertThrows(DuplicateProfileException.class, () -> uniqueProfileList.add(ALICE));
    }

    @Test
    public void setProfile_nullTargetProfile_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProfileList.setProfile(null, ALICE));
    }

    @Test
    public void setProfile_nullEditedProfile_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProfileList.setProfile(ALICE, null));
    }

    @Test
    public void setProfile_targetProfileNotInList_throwsProfileNotFoundException() {
        assertThrows(ProfileNotFoundException.class, () -> uniqueProfileList.setProfile(ALICE, ALICE));
    }

    @Test
    public void setProfile_editedProfileIsSameProfile_success() {
        uniqueProfileList.add(ALICE);
        uniqueProfileList.setProfile(ALICE, ALICE);
        UniqueProfileList expectedUniqueProfileList = new UniqueProfileList();
        expectedUniqueProfileList.add(ALICE);
        assertEquals(expectedUniqueProfileList, uniqueProfileList);
    }

    @Test
    public void setProfile_editedProfileHasSameIdentity_success() {
        uniqueProfileList.add(ALICE);
        Profile editedAlice = new ProfileBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .build();
        uniqueProfileList.setProfile(ALICE, editedAlice);
        UniqueProfileList expectedUniqueProfileList = new UniqueProfileList();
        expectedUniqueProfileList.add(editedAlice);
        assertEquals(expectedUniqueProfileList, uniqueProfileList);
    }

    @Test
    public void setProfile_editedProfileHasDifferentIdentity_success() {
        uniqueProfileList.add(ALICE);
        uniqueProfileList.setProfile(ALICE, BOB);
        UniqueProfileList expectedUniqueProfileList = new UniqueProfileList();
        expectedUniqueProfileList.add(BOB);
        assertEquals(expectedUniqueProfileList, uniqueProfileList);
    }

    @Test
    public void setProfile_editedProfileHasNonUniqueIdentity_throwsDuplicateProfileException() {
        uniqueProfileList.add(ALICE);
        uniqueProfileList.add(BOB);
        assertThrows(DuplicateProfileException.class, () -> uniqueProfileList.setProfile(ALICE, BOB));
    }

    @Test
    public void remove_nullProfile_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProfileList.remove(null));
    }

    @Test
    public void remove_profileDoesNotExist_throwsProfileNotFoundException() {
        assertThrows(ProfileNotFoundException.class, () -> uniqueProfileList.remove(ALICE));
    }

    @Test
    public void remove_existingProfile_removesProfile() {
        uniqueProfileList.add(ALICE);
        uniqueProfileList.remove(ALICE);
        UniqueProfileList expectedUniqueProfileList = new UniqueProfileList();
        assertEquals(expectedUniqueProfileList, uniqueProfileList);
    }

    @Test
    public void setProfiles_nullUniqueProfileList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProfileList.setProfiles((UniqueProfileList) null));
    }

    @Test
    public void setProfiles_uniqueProfileList_replacesOwnListWithProvidedUniqueProfileList() {
        uniqueProfileList.add(ALICE);
        UniqueProfileList expectedUniqueProfileList = new UniqueProfileList();
        expectedUniqueProfileList.add(BOB);
        uniqueProfileList.setProfiles(expectedUniqueProfileList);
        assertEquals(expectedUniqueProfileList, uniqueProfileList);
    }

    @Test
    public void setProfiles_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProfileList.setProfiles((List<Profile>) null));
    }

    @Test
    public void setProfiles_list_replacesOwnListWithProvidedList() {
        uniqueProfileList.add(ALICE);
        List<Profile> profileList = Collections.singletonList(BOB);
        uniqueProfileList.setProfiles(profileList);
        UniqueProfileList expectedUniqueProfileList = new UniqueProfileList();
        expectedUniqueProfileList.add(BOB);
        assertEquals(expectedUniqueProfileList, uniqueProfileList);
    }

    @Test
    public void setProfiles_listWithDuplicateProfiles_throwsDuplicateProfileException() {
        List<Profile> listWithDuplicateProfiles = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateProfileException.class, () -> uniqueProfileList.setProfiles(listWithDuplicateProfiles));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueProfileList.asUnmodifiableObservableList().remove(0));
    }
}
