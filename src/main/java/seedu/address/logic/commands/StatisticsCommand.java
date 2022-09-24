package seedu.address.logic.commands;

import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

/**
 * Formats full statistics for every command for display.
 */
public class StatisticsCommand extends Command {

    public static final String COMMAND_WORD = "statistics";
    public static final String SHOWING_STATISTICS_MESSAGE = "Opened statistics window.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(SHOWING_STATISTICS_MESSAGE, false, false, true);
    }
}