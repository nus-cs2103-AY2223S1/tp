package seedu.travelr.logic.commands;

import seedu.travelr.model.Model;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts trips within Travelr.";

    public static final String SORT_SUCCESS = "Trips have been sorted.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SORT_SUCCESS);
    }
}
