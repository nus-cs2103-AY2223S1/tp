package seedu.intrack.logic.commands;

import seedu.intrack.logic.commands.exceptions.CommandException;
import seedu.intrack.model.Model;

/**
 * Sorts all the internships in the internship list
 */
public abstract class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all the internships by a "
            + "SORTTYPE that can be either \"salary\" or \"time\"\n"
            + "and ORDERTYPE that can be either \"a\" or \"d\"\n"
            + "\"a\" will sort in ascending order and "
            + "\"d\" will sort in descending order \n"
            + "Example: " + SortCommand.COMMAND_WORD + " SORTTYPE a";

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;

}
