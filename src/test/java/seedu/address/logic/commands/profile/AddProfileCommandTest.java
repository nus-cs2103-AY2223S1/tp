package seedu.address.logic.commands.profile;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProfiles.AMY;
import static seedu.address.testutil.TypicalProfiles.BOB;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelStub;
import seedu.address.model.ModelStubWithProfile;
import seedu.address.model.NuScheduler;
import seedu.address.model.ReadOnlyNuScheduler;
import seedu.address.model.profile.Profile;
import seedu.address.testutil.ProfileBuilder;

public class AddProfileCommandTest {

    @Test
    public void constructor_nullProfile_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddProfileCommand(null));
    }

    @Test
    public void execute_profileAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingProfileAdded modelStub = new ModelStubAcceptingProfileAdded();
        Profile validProfile = new ProfileBuilder().build();

        CommandResult commandResult = new AddProfileCommand(validProfile).execute(modelStub);

        assertEquals(String.format(AddProfileCommand.MESSAGE_SUCCESS, validProfile), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validProfile), modelStub.profilesAdded);
    }

    @Test
    public void execute_similarEmail_throwsCommandException() {
        Profile validProfile = new ProfileBuilder(BOB).build();
        Profile onlySameEmailProfile = new ProfileBuilder(AMY).withEmail(VALID_EMAIL_BOB).build();
        AddProfileCommand addProfileCommand = new AddProfileCommand(validProfile);
        ModelStub modelStub = new ModelStubWithProfile(onlySameEmailProfile);

        assertThrows(CommandException.class,
                AddProfileCommand.MESSAGE_SIMILAR_EMAIL, () -> addProfileCommand.execute(modelStub)
        );
    }

    @Test
    public void execute_similarPhone_throwsCommandException() {
        Profile validProfile = new ProfileBuilder(BOB).build();
        Profile onlySamePhoneProfile = new ProfileBuilder(AMY).withPhone(VALID_PHONE_BOB).build();
        AddProfileCommand addProfileCommand = new AddProfileCommand(validProfile);
        ModelStub modelStub = new ModelStubWithProfile(onlySamePhoneProfile);

        assertThrows(CommandException.class,
                AddProfileCommand.MESSAGE_SIMILAR_PHONE, () -> addProfileCommand.execute(modelStub)
        );
    }

    @Test
    public void execute_similarTelegram_throwsCommandException() {
        Profile validProfile = new ProfileBuilder(BOB).build();
        Profile onlySameTelegramProfile = new ProfileBuilder(AMY).withTelegram(VALID_TELEGRAM_BOB).build();
        AddProfileCommand addProfileCommand = new AddProfileCommand(validProfile);
        ModelStub modelStub = new ModelStubWithProfile(onlySameTelegramProfile);

        assertThrows(CommandException.class,
                AddProfileCommand.MESSAGE_SIMILAR_TELEGRAM, () -> addProfileCommand.execute(modelStub)
        );
    }

    @Test
    public void equals() {
        Profile alice = new ProfileBuilder().withName("Alice").build();
        Profile bob = new ProfileBuilder().withName("Bob").build();
        AddProfileCommand addAliceCommand = new AddProfileCommand(alice);
        AddProfileCommand addBobCommand = new AddProfileCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddProfileCommand addAliceCommandCopy = new AddProfileCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different profile -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A Model stub that always accept the profile being added.
     */
    private class ModelStubAcceptingProfileAdded extends ModelStub {
        final ArrayList<Profile> profilesAdded = new ArrayList<>();

        @Override
        public boolean hasEmail(Profile profile) {
            requireNonNull(profile);
            return profilesAdded.stream().anyMatch(profile::isSameEmail);
        }

        @Override
        public boolean hasPhone(Profile profile) {
            requireNonNull(profile);
            return profilesAdded.stream().anyMatch(profile::isSamePhone);
        }

        @Override
        public boolean hasTelegram(Profile profile) {
            requireNonNull(profile);
            return profilesAdded.stream().anyMatch(profile::isSameTelegramNotEmpty);
        }

        @Override
        public void addProfile(Profile profile) {
            requireNonNull(profile);
            profilesAdded.add(profile);
        }

        @Override
        public ReadOnlyNuScheduler getNuScheduler() {
            return new NuScheduler();
        }
    }

}
