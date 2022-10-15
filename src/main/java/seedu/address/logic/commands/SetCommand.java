package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SLACK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.contact.Contact;
import seedu.address.model.person.contact.ContactType;
import seedu.address.ui.MainPanelName;

/**
 * Sets a Contact of a Person.
 */
public class SetCommand extends Command {
    public static final String COMMAND_WORD = "set";

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

    private final SetContactDescriptor setContactDescriptor;

    /**
     * Instantiates a SetCommand object.
     *
     * @param setContactDescriptor Container for the Contacts to be updated
     */
    public SetCommand(SetContactDescriptor setContactDescriptor) {
        this.setContactDescriptor = setContactDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        // get the selected person
        Person toUpdate = model.getSelectedPerson().get();
        assert toUpdate != null;

        // Create the updated person
        Person updatedPerson = createUpdatedPerson(toUpdate, setContactDescriptor);

        // Updates the current person
        model.setPerson(toUpdate, updatedPerson);

        // Display the success message
        return new CommandResult(MESSAGE_UPDATE_SUCCESS);
    }

    /**
     * Creates a Person object with the updated Contacts.
     *
     * @param personToEdit The original Person to be updated
     * @param setContactDescriptor Container for the Contacts to be updated
     * @return A Person object with the updated Contacts
     */
    private Person createUpdatedPerson(Person personToEdit,
                                       SetContactDescriptor setContactDescriptor) {
        assert personToEdit != null;
        Name name = personToEdit.getName();
        Address addr = personToEdit.getAddress();

        // get map of contacts from person
        Map<ContactType, Contact> oldContacts = personToEdit.getContacts();
        Map<ContactType, Contact> newContacts = setContactDescriptor.getContacts();
        for (ContactType key : oldContacts.keySet()) {
            if (!newContacts.containsKey(key)) {
                newContacts.put(key, oldContacts.get(key));
            }
        }

        return new Person(name, addr, new HashSet<>(), newContacts);
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
        return this.setContactDescriptor.equals(s.setContactDescriptor);
    }

    /**
     * Stores the data to update the Person's Contacts with.
     */
    public static class SetContactDescriptor {
        private Map<ContactType, Contact> contacts = new HashMap<>();

        /**
         * Instantiates a SetContactDescriptor object.
         */
        public SetContactDescriptor() {}

        /**
         * Updates the Contacts map.
         *
         * @param typeToSet The type of Contact to be updated
         * @param contactToSet The Contact to be updated
         */
        public void updateContacts(ContactType typeToSet, Contact contactToSet) {
            contacts.put(typeToSet, contactToSet);
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
            if (!(other instanceof SetContactDescriptor)) {
                return false;
            }

            // state check
            SetContactDescriptor s = (SetContactDescriptor) other;
            return this.contacts.equals(s.contacts);
        }
    }
}
