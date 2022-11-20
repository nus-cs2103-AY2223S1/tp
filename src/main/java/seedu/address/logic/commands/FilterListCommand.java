package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;

/**
 * Removes filter applied to address book based on arguments provided.
 */
public class FilterListCommand extends FilterCommand {

    public static final String COMMAND_SPECIFIER = "list";
    public static final String COMMAND_SPECIFIER_ALIAS = "l";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + COMMAND_SPECIFIER
            + ": Shows the filters applied \n"
            + "Example: " + COMMAND_WORD + " " + COMMAND_SPECIFIER;

    public FilterListCommand() {
        super();
    }

    @Override
    public CommandResult execute(Model model) {
        String message = getFiltersAppliedAsString(model).length() > 0 ? getFiltersAppliedAsString(model)
                : Messages.MESSAGE_NO_FILTERS_APPLIED;
        return new CommandResult(message);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof FilterListCommand)) {
            return false;
        }
        FilterListCommand otherCommand = (FilterListCommand) other;
        return (predicate == null && otherCommand.predicate == null)
                || predicate.equals(otherCommand.predicate);
    }
}
