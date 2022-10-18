package seedu.address.logic.commands.getcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.GetCommand;
import seedu.address.model.Model;
import seedu.address.model.person.MedicationContainsKeywordsPredicate;

/**
 * Finds and lists all patients taking a type of medication.
 * Keyword matching is case insensitive.
 */
public class GetMedicationCommand extends GetCommand {

    public static final String MEDICATION_PREFIX = "/m";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Gets all patients who are on the same medication "
            + "and displays them as a list with index numbers. The specified keywords are case-insensitive.\n"
            + "Parameters: "
            + MEDICATION_PREFIX + " MEDICATION\n"
            + "Example: "
            + COMMAND_WORD + " "
            + MEDICATION_PREFIX
            + " paracetamol";

    private final MedicationContainsKeywordsPredicate predicate;

    public GetMedicationCommand(MedicationContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GetMedicationCommand // instanceof handles nulls
                && predicate.equals(((GetMedicationCommand) other).predicate)); // state check
    }
}
