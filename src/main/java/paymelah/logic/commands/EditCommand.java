package paymelah.logic.commands;

import static java.util.Objects.requireNonNull;
import static paymelah.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static paymelah.logic.parser.CliSyntax.PREFIX_NAME;
import static paymelah.logic.parser.CliSyntax.PREFIX_PHONE;
import static paymelah.logic.parser.CliSyntax.PREFIX_TAG;
import static paymelah.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import java.util.List;
import java.util.Set;

import paymelah.commons.core.Messages;
import paymelah.commons.core.index.Index;
import paymelah.logic.commands.exceptions.CommandException;
import paymelah.logic.parser.ParserUtil.PersonDescriptor;
import paymelah.model.Model;
import paymelah.model.debt.DebtList;
import paymelah.model.person.Address;
import paymelah.model.person.Name;
import paymelah.model.person.Person;
import paymelah.model.person.Phone;
import paymelah.model.person.Telegram;
import paymelah.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_TELEGRAM + "TELEGRAM] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_TELEGRAM + "@JohnDoe_123";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final Index index;
    private final PersonDescriptor personDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param personDescriptor details to edit the person with
     */
    public EditCommand(Index index, PersonDescriptor personDescriptor) {
        requireNonNull(index);
        requireNonNull(personDescriptor);

        this.index = index;
        this.personDescriptor = new PersonDescriptor(personDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, personDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.saveAddressBook();
        model.saveCommandMessage(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
        model.setPerson(personToEdit, editedPerson);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code personDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, PersonDescriptor personDescriptor) {
        assert personToEdit != null;

        Name updatedName = personDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = personDescriptor.getPhone().orElse(personToEdit.getPhone());
        Telegram updatedTelegram = personDescriptor.getTelegram().orElse(personToEdit.getTelegram());
        Address updatedAddress = personDescriptor.getAddress().orElse(personToEdit.getAddress());
        Set<Tag> updatedTags = personDescriptor.getTags().orElse(personToEdit.getTags());
        DebtList updatedDebts = personToEdit.getDebts(); // edit command does not allow editing debts

        return new Person(updatedName, updatedPhone, updatedTelegram, updatedAddress, updatedTags, updatedDebts);
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
                && personDescriptor.equals(e.personDescriptor);
    }
}
