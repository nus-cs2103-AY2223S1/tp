package seedu.address.logic.commands.profile;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showProfileAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROFILE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PROFILE;
import static seedu.address.testutil.TypicalNuScheduler.getTypicalNuScheduler;
import static seedu.address.testutil.TypicalProfiles.SECOND_INDEX_TELEGRAM;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.profile.EditProfileCommand.EditProfileDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.NuScheduler;
import seedu.address.model.UserPrefs;
import seedu.address.model.profile.Profile;
import seedu.address.testutil.EditProfileDescriptorBuilder;
import seedu.address.testutil.ProfileBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditProfileCommand.
 */
public class EditProfileCommandTest {

    private Model model = new ModelManager(getTypicalNuScheduler(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Profile editedProfile = new ProfileBuilder().build();
        EditProfileDescriptor descriptor = new EditProfileDescriptorBuilder(editedProfile).build();
        EditProfileCommand editProfileCommand = new EditProfileCommand(INDEX_FIRST_PROFILE, descriptor);

        String expectedMessage = String.format(EditProfileCommand.MESSAGE_EDIT_PROFILE_SUCCESS, editedProfile);

        Model expectedModel = new ModelManager(new NuScheduler(model.getNuScheduler()), new UserPrefs());
        expectedModel.setProfile(model.getFilteredProfileList().get(0), editedProfile);

        assertCommandSuccess(editProfileCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastProfile = Index.fromOneBased(model.getFilteredProfileList().size());
        Profile lastProfile = model.getFilteredProfileList().get(indexLastProfile.getZeroBased());

        ProfileBuilder profileInList = new ProfileBuilder(lastProfile);
        Profile editedProfile = profileInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditProfileDescriptor descriptor = new EditProfileDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditProfileCommand editProfileCommand = new EditProfileCommand(indexLastProfile, descriptor);

        String expectedMessage = String.format(EditProfileCommand.MESSAGE_EDIT_PROFILE_SUCCESS, editedProfile);

        Model expectedModel = new ModelManager(new NuScheduler(model.getNuScheduler()), new UserPrefs());
        expectedModel.setProfile(lastProfile, editedProfile);

        assertCommandSuccess(editProfileCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditProfileCommand editProfileCommand = new EditProfileCommand(INDEX_FIRST_PROFILE,
                new EditProfileDescriptor());
        Profile editedProfile = model.getFilteredProfileList().get(INDEX_FIRST_PROFILE.getZeroBased());

        String expectedMessage = String.format(EditProfileCommand.MESSAGE_EDIT_PROFILE_SUCCESS, editedProfile);

        Model expectedModel = new ModelManager(new NuScheduler(model.getNuScheduler()), new UserPrefs());

        assertCommandSuccess(editProfileCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showProfileAtIndex(model, INDEX_FIRST_PROFILE);

        Profile profileInFilteredList = model.getFilteredProfileList().get(INDEX_FIRST_PROFILE.getZeroBased());
        Profile editedProfile = new ProfileBuilder(profileInFilteredList).withName(VALID_NAME_BOB).build();
        EditProfileCommand editProfileCommand = new EditProfileCommand(INDEX_FIRST_PROFILE,
                new EditProfileDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditProfileCommand.MESSAGE_EDIT_PROFILE_SUCCESS, editedProfile);

        Model expectedModel = new ModelManager(new NuScheduler(model.getNuScheduler()), new UserPrefs());
        expectedModel.setProfile(model.getFilteredProfileList().get(0), editedProfile);

        assertCommandSuccess(editProfileCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_similarEmailUnfilteredList_failure() {
        Profile firstProfile = model.getFilteredProfileList().get(INDEX_FIRST_PROFILE.getZeroBased());
        Profile secondProfile = model.getFilteredProfileList().get(INDEX_SECOND_PROFILE.getZeroBased());
        EditProfileDescriptor descriptor = new EditProfileDescriptorBuilder(firstProfile)
                .withEmail(secondProfile.getEmail().toString()).build();
        EditProfileCommand editProfileCommand = new EditProfileCommand(INDEX_FIRST_PROFILE, descriptor);

        assertCommandFailure(editProfileCommand, model, EditProfileCommand.MESSAGE_SIMILAR_EMAIL);
    }

    @Test
    public void execute_similarEmailFilteredList_failure() {
        showProfileAtIndex(model, INDEX_FIRST_PROFILE);

        // edit profile in filtered list into a profile with similar email in NUScheduler
        Profile profileShown = model.getNuScheduler().getProfileList().get(INDEX_FIRST_PROFILE.getZeroBased());
        Profile profileInList = model.getNuScheduler().getProfileList().get(INDEX_SECOND_PROFILE.getZeroBased());
        EditProfileCommand editProfileCommand = new EditProfileCommand(INDEX_FIRST_PROFILE,
                new EditProfileDescriptorBuilder(profileShown).withEmail(profileInList.getEmail().toString()).build());

        assertCommandFailure(editProfileCommand, model, EditProfileCommand.MESSAGE_SIMILAR_EMAIL);
    }

    @Test
    public void execute_similarPhoneUnfilteredList_failure() {
        Profile firstProfile = model.getFilteredProfileList().get(INDEX_FIRST_PROFILE.getZeroBased());
        Profile secondProfile = model.getFilteredProfileList().get(INDEX_SECOND_PROFILE.getZeroBased());
        EditProfileDescriptor descriptor = new EditProfileDescriptorBuilder(firstProfile)
                .withPhone(secondProfile.getPhone().toString()).build();
        EditProfileCommand editProfileCommand = new EditProfileCommand(INDEX_FIRST_PROFILE, descriptor);

        assertCommandFailure(editProfileCommand, model, EditProfileCommand.MESSAGE_SIMILAR_PHONE);
    }

    @Test
    public void execute_similarPhoneFilteredList_failure() {
        showProfileAtIndex(model, INDEX_FIRST_PROFILE);

        // edit profile in filtered list into a profile with similar phone in NUScheduler
        Profile profileShown = model.getNuScheduler().getProfileList().get(INDEX_FIRST_PROFILE.getZeroBased());
        Profile profileInList = model.getNuScheduler().getProfileList().get(INDEX_SECOND_PROFILE.getZeroBased());
        EditProfileCommand editProfileCommand = new EditProfileCommand(INDEX_FIRST_PROFILE,
                new EditProfileDescriptorBuilder(profileShown).withPhone(profileInList.getPhone().toString()).build());

        assertCommandFailure(editProfileCommand, model, EditProfileCommand.MESSAGE_SIMILAR_PHONE);
    }

    @Test
    public void execute_similarTelegramUnfilteredList_failure() {
        Profile firstProfile = model.getFilteredProfileList().get(INDEX_FIRST_PROFILE.getZeroBased());
        Profile secondProfile = model.getFilteredProfileList().get(INDEX_SECOND_PROFILE.getZeroBased());
        EditProfileDescriptor descriptor = new EditProfileDescriptorBuilder(firstProfile)
                .withTelegram(SECOND_INDEX_TELEGRAM).build();
        EditProfileCommand editProfileCommand = new EditProfileCommand(INDEX_FIRST_PROFILE, descriptor);

        assertCommandFailure(editProfileCommand, model, EditProfileCommand.MESSAGE_SIMILAR_TELEGRAM);
    }

    @Test
    public void execute_similarTelegramFilteredList_failure() {
        showProfileAtIndex(model, INDEX_FIRST_PROFILE);

        // edit profile in filtered list into a profile with similar telegram in NUScheduler
        Profile profileShown = model.getNuScheduler().getProfileList().get(INDEX_FIRST_PROFILE.getZeroBased());
        Profile profileInList = model.getNuScheduler().getProfileList().get(INDEX_SECOND_PROFILE.getZeroBased());
        EditProfileCommand editProfileCommand = new EditProfileCommand(INDEX_FIRST_PROFILE,
                new EditProfileDescriptorBuilder(profileShown).withTelegram(SECOND_INDEX_TELEGRAM).build());

        assertCommandFailure(editProfileCommand, model, EditProfileCommand.MESSAGE_SIMILAR_TELEGRAM);
    }

    @Test
    public void execute_invalidProfileIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredProfileList().size() + 1);
        EditProfileDescriptor descriptor = new EditProfileDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditProfileCommand editProfileCommand = new EditProfileCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editProfileCommand, model, Messages.MESSAGE_INVALID_PROFILE_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of NUScheduler
     */
    @Test
    public void execute_invalidProfileIndexFilteredList_failure() {
        showProfileAtIndex(model, INDEX_FIRST_PROFILE);
        Index outOfBoundIndex = INDEX_SECOND_PROFILE;
        // ensures that outOfBoundIndex is still in bounds of NUScheduler list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getNuScheduler().getProfileList().size());

        EditProfileCommand editProfileCommand = new EditProfileCommand(outOfBoundIndex,
                new EditProfileDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editProfileCommand, model, Messages.MESSAGE_INVALID_PROFILE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditProfileCommand standardCommand = new EditProfileCommand(INDEX_FIRST_PROFILE, DESC_AMY);

        // same values -> returns true
        EditProfileDescriptor copyDescriptor = new EditProfileDescriptor(DESC_AMY);
        EditProfileCommand commandWithSameValues = new EditProfileCommand(INDEX_FIRST_PROFILE, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditProfileCommand(INDEX_SECOND_PROFILE, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditProfileCommand(INDEX_FIRST_PROFILE, DESC_BOB)));
    }

}
