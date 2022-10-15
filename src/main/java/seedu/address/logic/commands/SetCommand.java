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

import java.util.*;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

public class SetCommand extends Command {
    public static final String COMMAND_WORD = "set";

    // TODO: add prefixes for each Contact
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets a person's Contacts. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_EDIT_SUCCESS = "Contact edited: %1$s";
    public static final String MESSAGE_ADD_SUCCESS = "Contact added: %1$s";

    private final ContactType typeToSet;
    private final Contact contactToSet;

    public SetCommand(ContactType typeToSet, Contact contactToSet) {
        this.typeToSet = typeToSet;
        this.contactToSet = contactToSet;
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

        // get map of contacts from person
        HashMap<ContactType, Contact> contacts = new HashMap<>();
        contacts.putAll(toUpdate.getContacts()); // clone the map

        // if contact in contacts edit the Contact
        if (contacts.containsKey(typeToSet)) {
            contacts.put(typeToSet, contactToSet);
            Person updatedPerson = createUpdatedPerson(model.getSelectedPerson().get(), contacts);
            model.setPerson(toUpdate, updatedPerson);
            return new CommandResult(String.format(MESSAGE_EDIT_SUCCESS, typeToSet));
        } else {
            contacts.put(typeToSet, contactToSet);
            Person updatedPerson = createUpdatedPerson(model.getSelectedPerson().get(), contacts);
            model.setPerson(toUpdate, updatedPerson);
            return new CommandResult(String.format(MESSAGE_ADD_SUCCESS, typeToSet));
        }
    }

    private Person createUpdatedPerson(Person personToEdit,
                                       Map<ContactType,Contact> newContacts) {
        assert personToEdit != null;
        Name name = personToEdit.getName();
        Address addr = personToEdit.getAddress();
//        Set<Tag> tags = personToEdit.getTags() != null ? personToEdit.getTags() : new HashSet<>();

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
}
