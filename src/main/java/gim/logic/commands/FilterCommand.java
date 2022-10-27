package gim.logic.commands;

import static java.util.Objects.requireNonNull;

import gim.commons.core.Messages;
import gim.model.Model;
import gim.model.exercise.NameContainsKeywordsPredicate;

/**
 * Filters and lists all exercises in exercise tracker whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = ":filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " -> Filters all exercises whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [KEYWORD]\n"
            + "Example usage:\n" + COMMAND_WORD + " Squat Deadlift";

    private final NameContainsKeywordsPredicate predicate;

    public FilterCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.filterFilteredExerciseList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_EXERCISES_LISTED_OVERVIEW, model.getFilteredExerciseList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterCommand // instanceof handles nulls
                && predicate.equals(((FilterCommand) other).predicate)); // state check
    }
}
