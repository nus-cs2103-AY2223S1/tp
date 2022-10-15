package seedu.address.logic.commands;

import javafx.beans.property.SimpleObjectProperty;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.contact.Contact;
import seedu.address.model.person.contact.ContactType;
import seedu.address.model.tag.Tag;
import seedu.address.ui.MainPanelName;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

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

    public SetCommand(SetContactDescriptor setContactDescriptor) {
        this.setContactDescriptor = setContactDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        // get the selected person
        Person toUpdate;
        if (model.getSelectedPerson().get() != null) {
            toUpdate = model.getSelectedPerson().get();
        } else {
            throw new CommandException("no person selected");
        }

        Person updatedPerson = createUpdatedPerson(toUpdate, setContactDescriptor);
        model.setPerson(toUpdate, updatedPerson);
        return new CommandResult(MESSAGE_UPDATE_SUCCESS);
    }

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
        return true;
    }

    public static class SetContactDescriptor {
        private Map<ContactType, Contact> contacts = new HashMap<>();

        public SetContactDescriptor(){}

        public SetContactDescriptor(Map<ContactType, Contact> toCopy) {
            this.contacts.putAll(toCopy);
        }

        public void updateContacts(ContactType typeToSet, Contact contactToSet) {
            contacts.put(typeToSet, contactToSet);
        }

        public Map<ContactType, Contact> getContacts() {
            return contacts;
        }
    }
}
