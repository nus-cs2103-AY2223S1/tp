package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProfiles.AMY;
import static seedu.address.testutil.TypicalProfiles.BOB;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.profile.AddProfileCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
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
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addProfile(Profile profile) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEmail(Profile profile) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPhone(Profile profile) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTelegram(Profile profile) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteProfile(Profile target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setProfile(Profile target, Profile editedProfile) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Profile> getFilteredProfileList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredProfileList(Predicate<Profile> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single profile.
     */
    private class ModelStubWithProfile extends ModelStub {
        private final Profile profile;

        ModelStubWithProfile(Profile profile) {
            requireNonNull(profile);
            this.profile = profile;
        }

        @Override
        public boolean hasEmail(Profile profile) {
            requireNonNull(profile);
            return this.profile.isSameEmail(profile);
        }

        @Override
        public boolean hasPhone(Profile profile) {
            requireNonNull(profile);
            return this.profile.isSamePhone(profile);
        }
        @Override
        public boolean hasTelegram(Profile profile) {
            requireNonNull(profile);
            return this.profile.isSameTelegram(profile);
        }
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
            return profilesAdded.stream().anyMatch(profile::isSameTelegram);
        }

        @Override
        public void addProfile(Profile profile) {
            requireNonNull(profile);
            profilesAdded.add(profile);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
