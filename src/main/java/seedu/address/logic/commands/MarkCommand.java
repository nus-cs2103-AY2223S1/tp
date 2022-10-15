package seedu.address.logic.commands;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UID;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Uid;
import seedu.address.model.person.VisitStatus;

/**
 * Marks a patient identified using their unique id from the displayed person list as having been visited.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the patient identified by the unique id number in the displayed person list as visited.\n"
            + "Parameters: UID (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_UID + " 1";

    public static final String MESSAGE_MARK_PATIENT_SUCCESS = "Marked Patient: %1$s";

    private final Uid uid;
    private final EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor()
            .setVisitStatus(VisitStatus.getVisited());

    public MarkCommand(Uid uid) {
        requireNonNull(uid);
        this.uid = uid;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        Optional<Person> personToMark = lastShownList.stream().filter(p -> p.getUid().equals(this.uid)).findFirst();
        new EditCommand(uid, editPersonDescriptor).execute(model);
        return new CommandResult(String.format(MESSAGE_MARK_PATIENT_SUCCESS, personToMark));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MarkCommand // instanceof handles nulls
                && this.uid.equals(((MarkCommand) other).uid)); // state check
    }
}
