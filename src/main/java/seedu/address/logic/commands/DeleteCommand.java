package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UID;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.model.person.Uid;

/**
 * Deletes a patient/nurse identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the patient/nurse identified by the unique id number used in the displayed person list.\n"
            + "Parameters: UID (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_UID + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted %1$s: %2$s";

    private final Uid targetUid;

    public DeleteCommand(Uid targetUid) {
        this.targetUid = targetUid;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        // if (targetIndex.getZeroBased() >= lastShownList.size()) {
        // throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        // }

        Optional<Person> personToDelete = lastShownList.stream().filter(p -> p.getUid() == targetUid).findFirst();
        if (!personToDelete.isPresent()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_UID);
        }
        Person confirmedPersonToDelete = personToDelete.get();
        model.deletePerson(confirmedPersonToDelete);
        if (confirmedPersonToDelete instanceof Patient) {
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS,
                    PATIENT_INDICATOR, confirmedPersonToDelete));
        }
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS,
                PERSON_INDICATOR, confirmedPersonToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                        && targetUid.equals(((DeleteCommand) other).targetUid)); // state check
    }
}
