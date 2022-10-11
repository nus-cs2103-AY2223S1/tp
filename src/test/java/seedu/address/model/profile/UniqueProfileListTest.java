package seedu.address.model.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProfiles.ALICE;
import static seedu.address.testutil.TypicalProfiles.AMY;
import static seedu.address.testutil.TypicalProfiles.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.profile.exceptions.ProfileNotFoundException;
import seedu.address.model.profile.exceptions.SimilarProfileException;
import seedu.address.testutil.ProfileBuilder;

public class UniqueProfileListTest {

    private final UniqueProfileList uniqueProfileList = new UniqueProfileList();

    @Test
    public void contains_nullName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProfileList.containsName(null));
    }

    @Test
    public void contains_nullEmail_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProfileList.containsEmail(null));
    }

    @Test
    public void contains_nameNotInList_returnsFalse() {
        assertFalse(uniqueProfileList.containsName(ALICE));
    }

    @Test
    public void contains_emailNotInList_returnsFalse() {
        assertFalse(uniqueProfileList.containsEmail(ALICE));
    }

    @Test
    public void contains_nameInList_returnsTrue() {
        uniqueProfileList.add(ALICE);
        assertTrue(uniqueProfileList.containsName(ALICE));
    }

    @Test
    public void contains_emailInList_returnsTrue() {
        uniqueProfileList.add(AMY);
        Profile editedBob = new ProfileBuilder(BOB).withEmail(VALID_EMAIL_AMY)
                .build();
        assertTrue(uniqueProfileList.containsEmail(editedBob));
    }

    @Test
    public void contains_profileWithSameNameFieldsInList_returnsTrue() {
        uniqueProfileList.add(ALICE);
        Profile editedAliceSameName = new ProfileBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        Profile editedAliceSameEmail = new ProfileBuilder(ALICE).withName(VALID_NAME_BOB)
                .build();
        assertTrue(uniqueProfileList.containsName(editedAliceSameName));
    }

    @Test
    public void contains_profileWithSameEmailFieldsInList_returnsTrue() {
        uniqueProfileList.add(ALICE);
        Profile editedAliceSameEmail = new ProfileBuilder(ALICE).withName(VALID_NAME_BOB)
                .build();
        assertTrue(uniqueProfileList.containsEmail(editedAliceSameEmail));
    }

    @Test
    public void add_nullProfile_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProfileList.add(null));
    }

    @Test
    public void add_duplicateName_throwsSimilarProfileException() {
        uniqueProfileList.add(ALICE);
        assertThrows(SimilarProfileException.class, () -> uniqueProfileList.add(ALICE));
    }

    @Test
    public void add_duplicateEmail_throwsSimilarProfileException() {
        uniqueProfileList.add(AMY);
        Profile editedBob = new ProfileBuilder(BOB).withEmail(VALID_EMAIL_AMY)
                .build();
        assertThrows(SimilarProfileException.class, () -> uniqueProfileList.add(editedBob));
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
        Profile editedAlice = new ProfileBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
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
    public void setProfile_editedProfileHasNonUniqueName_throwsSimilarProfileException() {
        uniqueProfileList.add(ALICE);
        uniqueProfileList.add(BOB);
        assertThrows(SimilarProfileException.class, () -> uniqueProfileList.setProfile(ALICE, BOB));
    }

    @Test
    public void setProfile_editedProfileHasNonUniqueEmail_throwsSimilarProfileException() {
        uniqueProfileList.add(ALICE);
        uniqueProfileList.add(BOB);
        Profile editedAlice = new ProfileBuilder(ALICE).withEmail(VALID_EMAIL_BOB)
                .build();
        assertThrows(SimilarProfileException.class, () -> uniqueProfileList.setProfile(ALICE, editedAlice));
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
    public void setProfiles_listWithDuplicateNames_throwsSimilarProfileException() {
        List<Profile> listWithDuplicateNames = Arrays.asList(ALICE, ALICE);
        assertThrows(SimilarProfileException.class, () -> uniqueProfileList.setProfiles(listWithDuplicateNames));
    }

    @Test
    public void setProfiles_listWithDuplicateEmails_throwsSimilarProfileException() {
        Profile editedBob = new ProfileBuilder(BOB).withEmail(VALID_EMAIL_AMY)
                .build();
        List<Profile> listWithDuplicateEmails = Arrays.asList(AMY, editedBob);
        assertThrows(SimilarProfileException.class, () -> uniqueProfileList.setProfiles(listWithDuplicateEmails));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueProfileList.asUnmodifiableObservableList().remove(0));
    }
}
