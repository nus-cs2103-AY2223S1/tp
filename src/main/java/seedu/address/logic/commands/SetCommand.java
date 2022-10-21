package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SLACK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;

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
import seedu.address.model.tag.Tag;
import seedu.address.ui.MainPanelName;

/**
 * Sets a Contact of a Person.
 */
public class SetCommand extends Command {
    public static final String COMMAND_WORD = "set";

    // TODO: update with every single possible prefix
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets a person's Contacts. "
            + "Parameters: "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_SLACK + "SLACK"
            + PREFIX_TELEGRAM + "TELEGRAM"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_EMAIL + "e0123456@u.nus.edu "
            + PREFIX_PHONE + "87654321"
            + PREFIX_SLACK + "johndoe "
            + PREFIX_TELEGRAM + "@johndoe";

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
     * @param personToEdit The original Person to be updated
     * @param setPersonDescriptor Container for the Contacts to be updated
     * @return A Person object with the updated Contacts
     */
    private Person createUpdatedPerson(Person personToEdit,
                                       SetPersonDescriptor setPersonDescriptor) {
        assert personToEdit != null;
        Name name = setPersonDescriptor.getName().orElse(personToEdit.getName());
        Address addr = setPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Set<Tag> tags = setPersonDescriptor.getTags().orElse(personToEdit.getTags());
        Role role = setPersonDescriptor.getRole().orElse(personToEdit.getRole());
        Timezone timezone = setPersonDescriptor.getTimezone().orElse(personToEdit.getTimezone());

        //TODO: add functionality to update user once the Person class is updated

        // get map of contacts from person
        Map<ContactType, Contact> oldContacts = personToEdit.getContacts();
        Map<ContactType, Contact> newContacts = setPersonDescriptor.getContacts();
        for (ContactType key : oldContacts.keySet()) {
            if (!newContacts.containsKey(key)) {
                newContacts.put(key, oldContacts.get(key));
            }
        }

        return new Person(name, addr, tags, newContacts, role, timezone);
    }

    @Override
    public boolean canExecuteAt(MainPanelName name) {
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
        private Name name;
        private Address address;
        private Set<Tag> tags;
        private Role role;
        private Timezone timezone;
        private Map<ContactType, Contact> contacts = new HashMap<>();

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
            for (ContactType key : toCopy.contacts.keySet()) {
                this.setContact(key, toCopy.contacts.get(key));
            }
        }

        public void setName(Name name) {
            this.name = name;
        }

        public void setAddress(Address address) {
            this.address = address;
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

        public void setRole(Role role) {
            this.role = role;
        }

        public void setTimezone(Timezone timezone) {
            this.timezone = timezone;
        }

        /**
         * Updates the Contacts map.
         *
         * @param typeToSet The type of Contact to be updated
         * @param contactToSet The Contact to be updated
         */
        public void setContact(ContactType typeToSet, Contact contactToSet) {
            contacts.put(typeToSet, contactToSet);
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(this.name);
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(this.address);
        }

        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        public Optional<Role> getRole() {
            return Optional.ofNullable(this.role);
        }

        public Optional<Timezone> getTimezone() {
            return Optional.ofNullable(this.timezone);
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
                   && this.name.equals(s.name)
                   && this.address.equals(s.address)
                   && this.tags.equals(s.tags)
                   && this.role.equals(s.role)
                   && this.timezone.equals(s.timezone);
        }
    }
}
