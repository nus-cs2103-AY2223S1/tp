package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.patient.TagContainsKeywordPredicate;

/**
 * Filters all patients in address book who have tag(s) containing the argument keyword.
 */
public class FilterTagCommand extends Command {

    public static final CommandWord COMMAND_WORD = new CommandWord("filtertag", "ft");

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all patients who have tag(s) "
            + "containing the keyword and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD\n"
            + "Example: " + COMMAND_WORD + "critical";

    public final TagContainsKeywordPredicate predicate;

    public FilterTagCommand(TagContainsKeywordPredicate predicate) {
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
                || (other instanceof FilterTagCommand // instanceof handles nulls
                && predicate.equals(((FilterTagCommand) other).predicate)); // state check
    }


}
