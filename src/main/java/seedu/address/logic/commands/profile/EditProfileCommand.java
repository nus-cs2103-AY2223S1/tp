package seedu.address.logic.commands.profile;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EVENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROFILES;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.profile.Email;
import seedu.address.model.profile.EventsAttending;
import seedu.address.model.profile.Name;
import seedu.address.model.profile.Phone;
import seedu.address.model.profile.Profile;
import seedu.address.model.profile.Telegram;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing profile in the NUScheduler.
 */
public class EditProfileCommand extends ProfileCommand {

    public static final String COMMAND_OPTION = "e";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + PREFIX_OPTION + COMMAND_OPTION
            + ": Edits the details of the profile identified "
            + "by the index number used in the displayed profile list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer less than 10000) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_TELEGRAM + "TELEGRAM USERNAME] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_OPTION + COMMAND_OPTION + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@u.nus.edu "
            + PREFIX_TELEGRAM + "johndoe "
            + PREFIX_TAG + "CS2103T";

    public static final String MESSAGE_EDIT_PROFILE_SUCCESS = "Edited Profile:\n%1$s";
    public static final String MESSAGE_HELP = "Edits an existing profile in NUScheduler.\n"
            + "Format: " + COMMAND_WORD + " " + PREFIX_OPTION + COMMAND_OPTION + " INDEX "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_TELEGRAM + "TELEGRAM USERNAME] "
            + "[" + PREFIX_TAG + "TAG]...\n";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    private final Index index;
    private final EditProfileDescriptor editProfileDescriptor;

    /**
     * @param index of the profile in the filtered profile list to edit
     * @param editProfileDescriptor details to edit the profile with
     */
    public EditProfileCommand(Index index, EditProfileDescriptor editProfileDescriptor) {
        requireNonNull(index);
        requireNonNull(editProfileDescriptor);

        this.index = index;
        this.editProfileDescriptor = new EditProfileDescriptor(editProfileDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Profile> lastShownList = model.getFilteredProfileList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROFILE_DISPLAYED_INDEX);
        }

        Profile profileToEdit = lastShownList.get(index.getZeroBased());
        Profile editedProfile = createEditedProfile(profileToEdit, editProfileDescriptor);

        if (!profileToEdit.isSameEmail(editedProfile) && model.hasEmail(editedProfile)) {
            throw new CommandException(MESSAGE_SIMILAR_EMAIL);
        }

        if (!profileToEdit.isSamePhone(editedProfile) && model.hasPhone(editedProfile)) {
            throw new CommandException(MESSAGE_SIMILAR_PHONE);
        }

        if (!profileToEdit.isSameTelegramNotEmpty(editedProfile) && model.hasTelegram(editedProfile)) {
            throw new CommandException(MESSAGE_SIMILAR_TELEGRAM);
        }

        EventsAttending eventsToRefresh = profileToEdit.getEventsToAttend();

        model.setProfile(profileToEdit, editedProfile);
        model.refreshEvents(eventsToRefresh);
        model.updateFilteredProfileList(PREDICATE_SHOW_ALL_PROFILES);
        model.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_PROFILE_SUCCESS, editedProfile));
    }

    /**
     * Creates and returns a {@code Profile} with the details of {@code profileToEdit}
     * edited with {@code editProfileDescriptor}.
     */
    private static Profile createEditedProfile(Profile profileToEdit, EditProfileDescriptor editProfileDescriptor) {
        assert profileToEdit != null;

        Name updatedName = editProfileDescriptor.getName().orElse(profileToEdit.getName());
        Phone updatedPhone = editProfileDescriptor.getPhone().orElse(profileToEdit.getPhone());
        Email updatedEmail = editProfileDescriptor.getEmail().orElse(profileToEdit.getEmail());
        Telegram updatedTelegram = editProfileDescriptor.getTelegram().orElse(profileToEdit.getTelegram());
        Set<Tag> updatedTags = editProfileDescriptor.getTags().orElse(profileToEdit.getTags());
        EventsAttending updatedEventsToAttend = editProfileDescriptor.getEventsToAttend()
                .orElse(profileToEdit.getEventsToAttend());

        return new Profile(updatedName, updatedPhone, updatedEmail, updatedTelegram, updatedTags,
                updatedEventsToAttend);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditProfileCommand)) {
            return false;
        }

        // state check
        EditProfileCommand e = (EditProfileCommand) other;
        return index.equals(e.index)
                && editProfileDescriptor.equals(e.editProfileDescriptor);
    }

    /**
     * Stores the details to edit the profile with. Each non-empty field value will replace the
     * corresponding field value of the profile.
     */
    public static class EditProfileDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Telegram telegram;
        private Set<Tag> tags;
        private EventsAttending eventsToAttend;

        public EditProfileDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditProfileDescriptor(EditProfileDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setTelegram(toCopy.telegram);
            setTags(toCopy.tags);
            setEventsToAttend(toCopy.eventsToAttend);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, telegram, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setTelegram(Telegram telegram) {
            this.telegram = telegram;
        }

        public Optional<Telegram> getTelegram() {
            return Optional.ofNullable(telegram);
        }

        public void setEventsToAttend(EventsAttending eventsToAttend) {
            this.eventsToAttend = eventsToAttend;
        }

        public Optional<EventsAttending> getEventsToAttend() {
            return Optional.ofNullable(eventsToAttend);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditProfileDescriptor)) {
                return false;
            }

            // state check
            EditProfileDescriptor e = (EditProfileDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getTelegram().equals(e.getTelegram())
                    && getEventsToAttend().equals(e.getEventsToAttend())
                    && getTags().equals(e.getTags());
        }
    }
}
