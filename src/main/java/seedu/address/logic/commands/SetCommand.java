package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SLACK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMEZONE;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Role;
import seedu.address.model.person.Timezone;
import seedu.address.model.person.contact.Contact;
import seedu.address.model.person.contact.ContactType;
import seedu.address.model.person.github.User;
import seedu.address.model.tag.Tag;
import seedu.address.ui.MainPanelName;

/**
 * Sets a Contact of a Person.
 */
public class SetCommand extends Command {
    public static final String COMMAND_WORD = "set";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets a person's Contacts. \n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_ROLE + "ROLE "
            + PREFIX_TIMEZONE + "TIMEZONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_SLACK + "SLACK "
            + PREFIX_TELEGRAM + "TELEGRAM "
            + PREFIX_GITHUB + "GITHUB "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_ADDRESS + "3755 W Cortaro Farms Rd "
            + PREFIX_ROLE + "Frontend Engineer "
            + PREFIX_TIMEZONE + "+8 "
            + PREFIX_EMAIL + "e0123456@u.nus.edu "
            + PREFIX_PHONE + "87654321 "
            + PREFIX_SLACK + "johndoe "
            + PREFIX_TELEGRAM + "@johndoe"
            + PREFIX_GITHUB + "john-doe";

    public static final String MESSAGE_UPDATE_SUCCESS = "Contact updated.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final SetPersonDescriptor setPersonDescriptor;

    /**
     * Instantiates a SetCommand object.
     *
     * @param setPersonDescriptor Container for the Contacts to be updated
     */
    public SetCommand(SetPersonDescriptor setPersonDescriptor) {
        this.setPersonDescriptor = setPersonDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        // get the selected person
        Person toUpdate = model.getSelectedPerson().get();
        assert toUpdate != null;

        // Create the updated person
        Person updatedPerson = createUpdatedPerson(toUpdate, setPersonDescriptor);

        if (!toUpdate.isSamePerson(updatedPerson) && model.hasPerson(updatedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }
        // Updates the current person
        model.setPerson(toUpdate, updatedPerson);

        // Display the success message
        return new CommandResult(MESSAGE_UPDATE_SUCCESS);
    }

    /**
     * Creates a Person object with the updated Contacts.
     *
     * @param personToEdit        The original Person to be updated
     * @param setPersonDescriptor Container for the Contacts to be updated
     * @return A Person object with the updated Contacts
     */
    private Person createUpdatedPerson(Person personToEdit,
                                       SetPersonDescriptor setPersonDescriptor) {
        assert personToEdit != null;
        Name name = setPersonDescriptor.getName().orElse(personToEdit.getName());
        Set<Tag> tags = setPersonDescriptor.getTags().orElse(personToEdit.getTags());
        Address address = setPersonDescriptor.getAddress().orElse(personToEdit.getAddress().orElse(null));
        Role role = setPersonDescriptor.getRole().orElse(personToEdit.getRole().orElse(null));
        Timezone timezone = setPersonDescriptor.getTimezone().orElse(personToEdit.getTimezone().orElse(null));
        User githubUser = setPersonDescriptor.getGithubUser().orElse(personToEdit.getGithubUser().orElse(null));

        //TODO: add functionality to update user once the Person class is updated

        // get map of contacts from person
        Map<ContactType, Contact> oldContacts = personToEdit.getContacts();
        Map<ContactType, Contact> newContacts = setPersonDescriptor.getContacts();
        for (ContactType key : oldContacts.keySet()) {
            if (!newContacts.containsKey(key)) {
                newContacts.put(key, oldContacts.get(key));
            }
        }

        return new Person(name, address, tags, newContacts, role, timezone, githubUser);
    }

    public static boolean canExecuteAt(MainPanelName name) {
        return name.equals(MainPanelName.Detail);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SetCommand)) {
            return false;
        }

        // state check
        SetCommand s = (SetCommand) other;
        return this.setPersonDescriptor.equals(s.setPersonDescriptor);
    }

    /**
     * Stores the data to update the Person's Contacts with.
     */
    public static class SetPersonDescriptor {
        private final Map<ContactType, Contact> contacts = new HashMap<>();
        private Name name;
        private Address address;
        private Set<Tag> tags;
        private Role role;
        private Timezone timezone;
        private User githubUser;

        /**
         * Instantiates a SetContactDescriptor object.
         */
        public SetPersonDescriptor() {}

        /**
         * Instantiates a copy of a given SetPersonDescriptor.
         * Defensive copies of tags and contacts are used.
         */
        public SetPersonDescriptor(SetPersonDescriptor toCopy) {
            this.setName(toCopy.name);
            this.setAddress(toCopy.address);
            this.setTags(toCopy.tags);
            this.setRole(toCopy.role);
            this.setTimezone(toCopy.timezone);
            this.setGithubUser(toCopy.githubUser);
            for (ContactType key : toCopy.contacts.keySet()) {
                this.setContact(key, toCopy.contacts.get(key));
            }
        }

        /**
         * Updates the Contacts map.
         *
         * @param typeToSet    The type of Contact to be updated
         * @param contactToSet The Contact to be updated
         */
        public void setContact(ContactType typeToSet, Contact contactToSet) {
            contacts.put(typeToSet, contactToSet);
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(this.name);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(this.address);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public void setTags(Set<Tag> tags) {
            // Set tags to a defensive copy of the given set of Tags
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        public Optional<Role> getRole() {
            return Optional.ofNullable(this.role);
        }

        public void setRole(Role role) {
            this.role = role;
        }

        public Optional<Timezone> getTimezone() {
            return Optional.ofNullable(this.timezone);
        }

        public void setTimezone(Timezone timezone) {
            this.timezone = timezone;
        }

        public Optional<User> getGithubUser() {
            return Optional.ofNullable(this.githubUser);
        }

        public void setGithubUser(User githubUser) {
            this.githubUser = githubUser;
        }

        /**
         * Gets the Map of updated Contacts.
         *
         * @return Map containing the Contacts
         */
        public Map<ContactType, Contact> getContacts() {
            return contacts;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof SetPersonDescriptor)) {
                return false;
            }

            // state check
            SetPersonDescriptor s = (SetPersonDescriptor) other;
            return this.contacts.equals(s.contacts)
                    && getName().equals(s.getName())
                    && getAddress().equals(s.getAddress())
                    && getTags().equals(s.getTags())
                    && getRole().equals(s.getRole())
                    && getTimezone().equals(s.getTimezone())
                    && getGithubUser().equals(s.getGithubUser());
        }
    }
}
