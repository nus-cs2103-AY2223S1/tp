package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.person.Class;
import seedu.address.model.timeRange.TimeRange;

/**
 * Finds the next available class schedule
 */
public class AvailCommand extends Command {
    public static final String COMMAND_WORD = "avail";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds the next available class date specified by the date range.\n"
            + "Parameters: [CLASS_DATE_TIME] "
            + "[DURATION](in minutes)\n"
            + "Example: " + COMMAND_WORD + " 1000-1500 120";
    public static final String MESSAGE_SUCCESS = "Available date is: %1$s";
    private final TimeRange timeRange;

    /**
     * Creates a AvailCommand object
     * @param timeRange a {@code TimeRange} object
     */
    public AvailCommand(TimeRange timeRange) {
        this.timeRange = timeRange;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Class classToDisplay = model.getAvailableClass(timeRange);

        return new CommandResult(String.format(MESSAGE_SUCCESS, classToDisplay));
    }
}
