package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COUNTRY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GAME_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MINECRAFT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MINECRAFT_SERVER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SOCIAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_INTERVAL;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Country;
import seedu.address.model.person.Email;
import seedu.address.model.person.GameType;
import seedu.address.model.person.ITimesAvailable;
import seedu.address.model.person.MinecraftName;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Server;
import seedu.address.model.person.Social;
import seedu.address.model.person.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";
    public static final String DESCRIPTION = "Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.";
    public static final String PARAMETERS = "INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_COUNTRY + "COUNTRY] "
            + "[" + PREFIX_MINECRAFT_NAME + "MINECRAFT_NAME] "
            + "[" + PREFIX_MINECRAFT_SERVER + "MINECRAFT_SERVER]* "
            + "[" + PREFIX_GAME_TYPE + "GAME_TYPE]* "
            + "[" + PREFIX_SOCIAL + "SOCIAL]* "
            + "[" + PREFIX_TAG + "TAG]* "
            + "[" + PREFIX_TIME_INTERVAL + "TIME_INTERVAL]*";
    public static final String EXAMPLES = COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com "
            + PREFIX_SOCIAL + "fb@John Doe ";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": " + DESCRIPTION + "\n\n"
            + "Parameters: \n"
            + PARAMETERS + "\n"
            + "Example: " + EXAMPLES;

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited friend: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * An empty constructor for EditCommand.
     */
    public EditCommand() {
        this.index = null;
        this.editPersonDescriptor = null;
    }

    /**
     * Constructs a {@code EditCommand}.
     *
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public String getParameters() {
        return PARAMETERS;
    }

    @Override
    public String getExamples() {
        return EXAMPLES;
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        MinecraftName updatedMinecraftName = editPersonDescriptor.getMinecraftName()
                .orElse(personToEdit.getMinecraftName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Set<Social> updatedSocials = editPersonDescriptor.getSocials().orElse(personToEdit.getSocials());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());
        Set<Server> updatedServers = editPersonDescriptor.getServers().orElse(personToEdit.getServers());
        Country updatedCountry = editPersonDescriptor.getCountry().orElse(personToEdit.getCountry());
        Set<GameType> updatedGameType = editPersonDescriptor.getGameTypes().orElse(personToEdit.getGameType());
        Set<ITimesAvailable> updatedTimeIntervals = editPersonDescriptor.getTimeIntervals()
                .orElse(personToEdit.getTimesAvailable());

        return new Person(updatedName, updatedMinecraftName, updatedPhone, updatedEmail, updatedAddress,
                updatedSocials, updatedTags, updatedServers, updatedCountry, updatedGameType, updatedTimeIntervals);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {

        private Name name;
        private MinecraftName minecraftName;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Social> socials;
        private Set<Tag> tags;
        private Set<Server> servers;
        private Country country;
        private Set<GameType> gameTypes;
        private Set<ITimesAvailable> timeIntervals;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setMinecraftName(toCopy.minecraftName);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setSocials(toCopy.socials);
            setTags(toCopy.tags);
            setServers(toCopy.servers);
            setCountry(toCopy.country);
            setGameTypes(toCopy.gameTypes);
            setTimeIntervals(toCopy.timeIntervals);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, minecraftName, phone, email, address,
                    socials, tags, servers, country, gameTypes, timeIntervals);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setMinecraftName(MinecraftName name) {
            this.minecraftName = name;
        }

        public Optional<MinecraftName> getMinecraftName() {
            return Optional.ofNullable(minecraftName);
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

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        /**
         * Sets {@code socials} to this object's {@code socials}.
         * A defensive copy of {@code socials} is used internally.
         */
        public void setSocials(Set<Social> socials) {
            this.socials = (socials != null) ? new HashSet<>(socials) : null;
        }

        /**
         * Returns an unmodifiable socials set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code socials} is null.
         */
        public Optional<Set<Social>> getSocials() {
            return (socials != null)
                    ? Optional.of(Collections.unmodifiableSet(socials))
                    : Optional.empty();
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

        public void setServers(Set<Server> servers) {
            this.servers = (servers != null) ? new HashSet<>(servers) : null;
        }

        public Optional<Set<Server>> getServers() {
            return (servers != null) ? Optional.of(Collections.unmodifiableSet(servers)) : Optional.empty();
        }

        public void setCountry(Country country) {
            this.country = country;
        }

        public Optional<Country> getCountry() {
            return Optional.ofNullable(country);
        }

        public void setGameTypes(Set<GameType> gameTypes) {
            this.gameTypes = (gameTypes != null) ? new HashSet<>(gameTypes) : null;
        }

        public Optional<Set<GameType>> getGameTypes() {
            return (gameTypes != null) ? Optional.of(Collections.unmodifiableSet(gameTypes)) : Optional.empty();
        }

        public void setTimeIntervals(Set<ITimesAvailable> timeIntervals) {
            this.timeIntervals = (timeIntervals != null) ? new HashSet<>(timeIntervals) : null;
        }

        public Optional<Set<ITimesAvailable>> getTimeIntervals() {
            return (timeIntervals != null) ? Optional.of(Collections.unmodifiableSet(timeIntervals)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {

            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditPersonDescriptor e = (EditPersonDescriptor) other;

            return getName().equals(e.getName())
                    && getMinecraftName().equals(e.getMinecraftName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getSocials().equals(e.getSocials())
                    && getTags().equals(e.getTags())
                    && getServers().equals(e.getServers())
                    && getCountry().equals(e.getCountry())
                    && getGameTypes().equals(e.getGameTypes())
                    && getTimeIntervals().equals(e.getTimeIntervals());
        }
    }
}
