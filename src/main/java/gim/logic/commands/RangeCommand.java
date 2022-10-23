package gim.logic.commands;

import static java.util.Objects.requireNonNull;

import gim.commons.core.Messages;
import gim.model.Model;
import gim.model.exercise.DateWithinRangePredicate;

/**
 * Finds all exercises with dates that are within the specified start date
 * and end date (both inclusive).
 */
public class RangeCommand extends Command {

    public static final String COMMAND_WORD = ":range";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " displays all exercises with dates that are within "
            + "the specified start date and end date.\n\n"
            + "Parameters: \nKEYWORD d/START_DATE e/END_DATE\n\n"
            + "Example: \n" + COMMAND_WORD + " d/10/10/2022 e/22/10/2022";

    private final DateWithinRangePredicate predicate;

    public RangeCommand(DateWithinRangePredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredExerciseList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_EXERCISES_LISTED_OVERVIEW, model.getFilteredExerciseList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RangeCommand // instanceof handles nulls
                && predicate.equals(((RangeCommand) other).predicate)); // state check
    }
}
