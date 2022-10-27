package seedu.intrack.logic.commands;

import seedu.intrack.logic.commands.exceptions.CommandException;
import seedu.intrack.model.Model;

/**
 * Sorts all the internships in the internship list
 */
public abstract class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all internships applications by "
            + "the specified SORT_TYPE and SORT_ORDER.\n"
            + "Parameters: SORT_TYPE (must be either \"salary\" or \"time\") "
            + "SORT_ORDER (must be either \"a\" or \"d\")\n"
            + "\"a\" and \"d\" will sort the list in ascending and descending order respectively.\n"
            + "Example: " + SortCommand.COMMAND_WORD + " time a";

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;

}
