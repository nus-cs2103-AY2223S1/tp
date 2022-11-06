package tuthub.logic.commands;

import static java.util.Objects.requireNonNull;
import static tuthub.logic.parser.CliSyntax.PREFIX_NAME;

import tuthub.commons.core.Messages;
import tuthub.model.Model;
import tuthub.model.tutor.NameContainsKeywordsPredicate;

/**
 * Finds and lists all tutors in TutHub whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindByNameCommand extends FindByPrefixCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tutors whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + " alex john";

    private final NameContainsKeywordsPredicate predicate;

    public FindByNameCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTutorList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_TUTORS_LISTED_OVERVIEW, model.getFilteredTutorList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindByNameCommand // instanceof handles nulls
                && predicate.equals(((FindByNameCommand) other).predicate)); // state check
    }
}
