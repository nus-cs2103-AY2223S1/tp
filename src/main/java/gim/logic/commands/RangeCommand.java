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

    public static final String ADVANCED_MESSAGE_USAGE =
            "This is an advanced feature of " + COMMAND_WORD
            + ": displays all exercises within the last VAL days (not including today's exercises).\n\n"
            + "Parameters: \nKEYWORD l/VAL\n\n"
            + "Example: \n" + COMMAND_WORD + " l/7";

    private final DateWithinRangePredicate predicate;
    public final boolean isAdvanced;

    /**
     * Default constructor for basic version.
     * @param predicate {@code DateWithinRangePredicate} object to determine the range
     */
    public RangeCommand(DateWithinRangePredicate predicate) {
        this.predicate = predicate;
        this.isAdvanced = false;
    }

    /**
     * Extra constructor for basic version and advanced version.
     *
     * @param predicate {@code DateWithinRangePredicate} object to determine the range
     * @param isAdvanced value is true for basic version and false for advanced version of the {@code RangeCommand}
     */
    public RangeCommand(DateWithinRangePredicate predicate, boolean isAdvanced) {
        this.predicate = predicate;
        this.isAdvanced = isAdvanced;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredExerciseList(predicate);
        int rangeInDays = predicate.getRangeSizeInDays();

        // Display different message to user when command used is the advanced version
        if (isAdvanced && rangeInDays == 0) {
            return new CommandResult(Messages.MESSAGE_RANGE_COMMAND_ADVANCED_TODAY);
        } else if (isAdvanced && rangeInDays == 1) {
            return new CommandResult(Messages.MESSAGE_RANGE_COMMAND_ADVANCED_YESTERDAY);
        } else if (isAdvanced && rangeInDays == 7) {
            return new CommandResult(Messages.MESSAGE_RANGE_COMMAND_ADVANCED_WEEK);
        } else if (isAdvanced && rangeInDays > 1) {
            return new CommandResult(
                    String.format(Messages.MESSAGE_RANGE_COMMAND_ADVANCED, rangeInDays));
        }

        // Display different message to user when command used is the basic version
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
