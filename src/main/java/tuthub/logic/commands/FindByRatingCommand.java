package tuthub.logic.commands;

import static java.util.Objects.requireNonNull;
import static tuthub.logic.parser.CliSyntax.PREFIX_RATING;

import tuthub.commons.core.Messages;
import tuthub.model.Model;
import tuthub.model.tutor.RatingContainsKeywordsPredicate;

/**
 * Finds and list all TAs in TutHub who have student id matching the student id being searched.
 */
public class FindByRatingCommand extends FindByPrefixCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tutors whose rating matches "
            + "the specified keywords and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_RATING + "4.1";

    private final RatingContainsKeywordsPredicate predicate;

    public FindByRatingCommand(RatingContainsKeywordsPredicate predicate) {
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
                || (other instanceof FindByRatingCommand // instanceof handles null
                && predicate.equals(((FindByRatingCommand) other).predicate)); // state check
    }
}
