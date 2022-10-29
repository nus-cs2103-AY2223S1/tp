package seedu.watson.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.watson.model.Model;

/**
 * Command to view statistics regarding a certain subject or student
 */
public class StatisticsCommand extends Command {

    public static final String COMMAND_WORD = "statistics";

    // TODO: Add correct usage message
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons matching the specified criteria "
          + "the specified keywords (case-insensitive) and displays them"
          + " as a list with index numbers.\n"
          + "Parameters: n/NAMES (OPTIONAL) "
          + "c/CLASS (OPTIONAL) s/SUBJECT (OPTIONAL)\n"
          + "However, the command should not be blank.\n"
          + "Example: " + COMMAND_WORD + " n/alice bob charlie\n"
          + "Example: " + COMMAND_WORD + " n/alice bob charlie c/1A s/English";

    public static final String MESSAGE_SUCCESS = "Statistics shown";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
