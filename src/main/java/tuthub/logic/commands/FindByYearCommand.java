package tuthub.logic.commands;

import static java.util.Objects.requireNonNull;
import static tuthub.logic.parser.CliSyntax.PREFIX_YEAR;

import tuthub.commons.core.Messages;
import tuthub.model.Model;
import tuthub.model.tutor.YearContainsKeywordsPredicate;

/**
 * Finds and lists all tutors in TutHub whose year contains any of the argument keywords.
 */
public class FindByYearCommand extends FindByPrefixCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tutors whose year matches the "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_YEAR + " 3";

    private final YearContainsKeywordsPredicate predicate;

    public FindByYearCommand(YearContainsKeywordsPredicate predicate) {
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
                || (other instanceof FindByYearCommand // instanceof handles nulls
                && predicate.equals(((FindByYearCommand) other).predicate)); // state check
    }
}
