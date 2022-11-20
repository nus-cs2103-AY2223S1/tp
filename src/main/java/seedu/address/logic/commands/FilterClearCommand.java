package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Removes filter applied to address book based on arguments provided.
 */
public class FilterClearCommand extends FilterCommand {

    public static final String COMMAND_SPECIFIER = "clear";
    public static final String COMMAND_SPECIFIER_ALIAS = "c";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + COMMAND_SPECIFIER
            + ": Removes filter of the specified name and tags (both case-insensitive) \n"
            + "and displays the contacts without the filter applied \n"
            + "Parameters: [" + PREFIX_NAME + "NAME1,NAME2,...] [" + PREFIX_TAG + "TAG1,TAG2,...] \n"
            + "Example: " + COMMAND_WORD + " " + COMMAND_SPECIFIER + " " + PREFIX_NAME + "alice";

    public FilterClearCommand(FilterCommandPredicate predicate) {
        super(predicate);
    }

    public FilterClearCommand() {
        super();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        requireTagExists(model, predicate);
        clearSpecifiedFilters(model);
        return new CommandResult(getListOverviewAsString(model) + "\n" + getFiltersAppliedAsString(model));
    }

    /**
     * Clear filters in {@code model}. Will clear all filters if {@code predicate} is {@code null}.
     *
     * @params model Model which the filter will be updated.
     */
    private void clearSpecifiedFilters(Model model) {
        if (predicate == null) {
            model.clearFiltersInFilteredPersonList();
            return;
        }
        model.removeFilterFromFilteredPersonList(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof FilterClearCommand)) {
            return false;
        }
        FilterClearCommand otherCommand = (FilterClearCommand) other;
        return (predicate == null && otherCommand.predicate == null)
                || predicate.equals(otherCommand.predicate);
    }
}
