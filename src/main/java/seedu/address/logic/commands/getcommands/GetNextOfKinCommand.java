package seedu.address.logic.commands.getcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.GetCommand;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.NextOfKinPredicate;

/**
 * Finds the next-of-kin data of the given patient.
 * Keyword matching is case insensitive.
 */
public class GetNextOfKinCommand extends GetCommand {

    public static final String NEXT_OF_KIN_PREFIX = "/nok";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds the next-of-kin data with the given  "
            + "specified keywords (patient name; case insensitive) \n"
            + "Parameters: "
            + NEXT_OF_KIN_PREFIX + " PATIENT NAME\n"
            + "Example: "
            + COMMAND_WORD + " "
            + NEXT_OF_KIN_PREFIX
            + " alice ";

    private final NextOfKinPredicate predicate;

    public GetNextOfKinCommand(NextOfKinPredicate predicate) {
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
                || (other instanceof GetNextOfKinCommand // instanceof handles nulls
                && predicate.equals(((GetNextOfKinCommand) other).predicate)); // state check
    }
}
