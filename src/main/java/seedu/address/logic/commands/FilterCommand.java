package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.isAnyNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;

/**
 * Filters contacts in address book whose name contains the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Filters for contacts with the specified name and tags (both case-insensitive) \n"
            + "and displays them as a list with index numbers.\n"
            + "Parameters: [" + PREFIX_NAME + "NAME1,NAME2,...] [" + PREFIX_TAG + "TAG1,TAG2,...] \n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "alice";

    protected final FilterCommandPredicate predicate;

    /**
     * Creates a {@code FilterCommand} object for filtering.
     *
     * @param predicate {@code FilterCommandPredicate} to use for filtering.
     */
    public FilterCommand(FilterCommandPredicate predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    protected FilterCommand() {
        this.predicate = null;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        requireNonNull(predicate);
        applySpecifiedFilters(model);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    private void applySpecifiedFilters(Model model) {
        assert isAnyNonNull(predicate.getNamePredicate(), predicate.getTagPredicate());
        if (predicate.getNamePredicate() != null) {
            predicate.getNamePredicate()
                    .forEach((namePredicate) -> model.addNewFilterToFilteredPersonList(namePredicate));
        }
        if (predicate.getTagPredicate() != null) {
            predicate.getTagPredicate()
                    .forEach((tagPredicate) -> model.addNewFilterToFilteredPersonList(tagPredicate));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterCommand // instanceof handles nulls
                        && predicate.equals(((FilterCommand) other).predicate)); // state check
    }
}
