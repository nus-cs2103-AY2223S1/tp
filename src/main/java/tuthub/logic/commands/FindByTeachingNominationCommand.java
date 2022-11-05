package tuthub.logic.commands;

import static java.util.Objects.requireNonNull;
import static tuthub.logic.parser.CliSyntax.PREFIX_TEACHINGNOMINATION;

import tuthub.commons.core.Messages;
import tuthub.model.Model;
import tuthub.model.tutor.TeachingNominationContainKeywordsPredicate;

/**
 * Finds and list all TAs in TutHub who have teaching nominations matching the keywords being searched.
 */
public class FindByTeachingNominationCommand extends FindByPrefixCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tutors whose teaching nominations matches "
            + "the specified keywords and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_TEACHINGNOMINATION + "2";

    private final TeachingNominationContainKeywordsPredicate predicate;

    public FindByTeachingNominationCommand(TeachingNominationContainKeywordsPredicate predicate) {
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
                || (other instanceof FindByTeachingNominationCommand // instanceof handles null
                && predicate.equals(((FindByTeachingNominationCommand) other).predicate)); // state check
    }
}
