package seedu.travelr.logic.commands;

import seedu.travelr.logic.commands.exceptions.CommandException;
import seedu.travelr.model.Model;

public class SummaryCommand extends Command {

    public static final String COMMAND_WORD = "summary";

    public static final String SHOWING_SUMMARY_MESSAGE = "Opened summary window.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the summary of your trips and events.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.updateFilteredTripList(Model.PREDICATE_SHOW_ALL_TRIPS);
        model.updateFilteredEventList(Model.PREDICATE_SHOW_ALL_EVENTS);
        model.refreshSummaryVariables();

        return new CommandResult(SHOWING_SUMMARY_MESSAGE, false, false, true);
    }
}
