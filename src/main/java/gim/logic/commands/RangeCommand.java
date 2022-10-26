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

    public static final String MESSAGE_USAGE = "The range feature has two variations: \n\n"
            + COMMAND_WORD + " d/START_DATE e/END_DATE\n"
            + "displays all exercises with dates that are within "
            + "the specified start date and end date.\n"
            + "Example: \n" + COMMAND_WORD + " d/10/10/2022 e/22/10/2022\n\n"
            + COMMAND_WORD + " last/DAYS\n"
            + "displays all exercises within the last DAYS days and and today's exercises.\n"
            + "Example: \n" + COMMAND_WORD + " last/7";

    public static final String MESSAGE_USAGE_TWO = "The range feature has two variations: \n\n"
            + COMMAND_WORD + " last/DAYS\n"
            + "displays all exercises within the last DAYS days and and today's exercises.\n"
            + "Example: \n" + COMMAND_WORD + " last/7\n\n"
            + COMMAND_WORD + " d/START_DATE e/END_DATE\n"
            + "displays all exercises with dates that are within "
            + "the specified start date and end date.\n"
            + "Example: \n" + COMMAND_WORD + " d/10/10/2022 e/22/10/2022";

    public final boolean isAdvanced;
    private final DateWithinRangePredicate predicate;

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
        model.sortFilteredExerciseList(predicate);
        int rangeInDays = predicate.getRangeSizeInDays();

        // Display different message to user when command used is the advanced version
        if (isAdvanced && rangeInDays == 0) {
            return new CommandResult(Messages.MESSAGE_RANGE_COMMAND_TWO_TODAY);
        } else if (isAdvanced && rangeInDays == 1) {
            return new CommandResult(Messages.MESSAGE_RANGE_COMMAND_TWO_YESTERDAY);
        } else if (isAdvanced && rangeInDays == 7) {
            return new CommandResult(Messages.MESSAGE_RANGE_COMMAND_TWO_WEEK);
        } else if (isAdvanced && rangeInDays > 1) {
            return new CommandResult(
                    String.format(Messages.MESSAGE_RANGE_COMMAND_TWO, rangeInDays));
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
