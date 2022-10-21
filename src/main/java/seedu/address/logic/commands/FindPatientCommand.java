package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.patient.Patient;

/**
 * Finds and lists all patients by name, email, address, phone number, tag or remark filter, according to the prefix
 * input by the user.
 */
public class FindPatientCommand extends Command {

    public static final CommandWord COMMAND_WORD =
            new CommandWord("findpatient", "fp");

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all patients whose name/phone/email/address"
            + "/tag/remark (according to prefix input) contains any of the specified keywords and displays them as "
            + "a list with index numbers.\n"
            + "Parameters: prefix, KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " n/alex \n"
            + "Example: " + COMMAND_WORD + " p/91234567 \n"
            + "Example: " + COMMAND_WORD + " e/example \n"
            + "Example: " + COMMAND_WORD + " a/Blk 123 \n"
            + "Example: " + COMMAND_WORD + " t/friends \n"
            + "Example: " + COMMAND_WORD + " r/swim";

    private final Predicate<Patient> predicate;

    public FindPatientCommand(Predicate<Patient> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPatientList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PATIENTS_LISTED_OVERVIEW, model.getFilteredPatientList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindPatientCommand // instanceof handles nulls
                && predicate.equals(((FindPatientCommand) other).predicate)); // state check
    }
}
