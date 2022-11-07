package seedu.address.logic.commands;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.EditCommand.createEditedPerson;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Occupation;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person or specified field of a person identified by the index number used \n"
            + " or index & field specified in the displayed person list. \n"
            + "Parameters: INDEX (must be a positive integer) or \n"
            + "Parameters: INDEX FIELD (prefixes such as p/ e/ etc.) \n"
            + "Examples: " + COMMAND_WORD + " 1 " + ", " + COMMAND_WORD + " 1 " + PREFIX_PHONE.getPrefix()
            + " , " + COMMAND_WORD + " 1 " + PREFIX_EMAIL.getPrefix() + "\n";


    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    public static final String MESSAGE_DELETE_FIELD_SUCCESS = "Deleted %1$s %2$s for Person: %3$s";

    public static final String MESSAGE_DELETED_ALREADY = "Field has already been deleted!";

    private final Index targetIndex;
    private String field;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Deletes a person's field identified using it's displayed index and provided prefix from the address book.
     */
    public DeleteCommand(Index targetIndex, String field) {
        this.targetIndex = targetIndex;
        this.field = field;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
        UndoCommand.prepareSaveModelBefore(this, model);

        if (isNull(field)) {
            model.deletePerson(personToDelete);
            UndoCommand.saveBeforeMod(model);
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
        }

        return deleteHandler(personToDelete, model, field);
    }

    /**
     * Handles the field & updates the model accordingly.
     * @param personToDelete
     * @param model
     * @param field
     * @return CommandResult to notify the success of the Delete Command.
     * @throws CommandException if field is deleted already.
     */
    public CommandResult deleteHandler(Person personToDelete, Model model, String field) throws CommandException {
        EditCommand.EditPersonDescriptor editPersonDescriptor = new EditCommand.EditPersonDescriptor();
        ReadOnlyAddressBook pastAddressBook = (ReadOnlyAddressBook) model.getAddressBook().clone();

        switch (field) {
        case "o/":
            if (personToDelete.getOccupation().isDeleted()) {
                throw new CommandException(DeleteCommand.MESSAGE_DELETED_ALREADY);
            }
            editPersonDescriptor.setOccupation(new Occupation("NONE"));
            Person editedPerson = createEditedPerson(personToDelete, editPersonDescriptor);
            model.setPerson(personToDelete, editedPerson);
            UndoCommand.saveBeforeMod(model);
            return new CommandResult(String.format(MESSAGE_DELETE_FIELD_SUCCESS,
                    "Occupation", personToDelete.getOccupation(), personToDelete.getName()));

        case "p/":
            if (personToDelete.getPhone().isDeleted()) {
                throw new CommandException(DeleteCommand.MESSAGE_DELETED_ALREADY);
            }
            editPersonDescriptor.setPhone(new Phone("NO NUMBER SET"));
            editedPerson = createEditedPerson(personToDelete, editPersonDescriptor);
            model.setPerson(personToDelete, editedPerson);
            UndoCommand.saveBeforeMod(model);
            return new CommandResult(String.format(MESSAGE_DELETE_FIELD_SUCCESS,
                    "Phone Number", personToDelete.getPhone(), personToDelete.getName()));

        case "e/":
            if (personToDelete.getEmail().isDeleted()) {
                throw new CommandException(DeleteCommand.MESSAGE_DELETED_ALREADY);
            }
            editPersonDescriptor.setEmail(new Email("NO EMAIL SET"));
            editedPerson = createEditedPerson(personToDelete, editPersonDescriptor);
            model.setPerson(personToDelete, editedPerson);
            UndoCommand.saveBeforeMod(model);
            return new CommandResult(String.format(MESSAGE_DELETE_FIELD_SUCCESS,
                    "Email", personToDelete.getEmail(), personToDelete.getName()));

        case "a/":
            if (personToDelete.getAddress().isDeleted()) {
                throw new CommandException(DeleteCommand.MESSAGE_DELETED_ALREADY);
            }
            editPersonDescriptor.setAddress(new Address("NO ADDRESS SET"));
            editedPerson = createEditedPerson(personToDelete, editPersonDescriptor);
            model.setPerson(personToDelete, editedPerson);
            UndoCommand.saveBeforeMod(model);
            return new CommandResult(String.format(MESSAGE_DELETE_FIELD_SUCCESS,
                    "Address", personToDelete.getAddress(), personToDelete.getName()));

        case "t/":
            Set<Tag> personTags = personToDelete.getTags();
            if (personTags.stream().findFirst().get().isDeleted()) {
                throw new CommandException(DeleteCommand.MESSAGE_DELETED_ALREADY);
            }
            HashSet<Tag> hash = new HashSet<>();
            hash.add(new Tag("No Tags Set"));
            editPersonDescriptor.setTags(hash);
            editedPerson = createEditedPerson(personToDelete, editPersonDescriptor);
            model.setPerson(personToDelete, editedPerson);
            UndoCommand.saveBeforeMod(model);
            return new CommandResult(String.format(MESSAGE_DELETE_FIELD_SUCCESS,
                    "Tag", personToDelete.getTags(), personToDelete.getName()));

        default:
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        }
        return null;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
