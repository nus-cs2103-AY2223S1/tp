package jeryl.fyp.logic.commands;

import static java.util.Objects.requireNonNull;

import jeryl.fyp.model.Model;

/**
 * Sorts projects in the FYP manager by the project status, followed by alphabetical order
 */
public class SortProjectStatusCommand extends Command {

    public static final String COMMAND_WORD = "sort-s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the FYP projects "
            + "by the current project status, in the order YTS, IP followed by DONE"
            + "Note that the format is fixed, hence only " + COMMAND_WORD + " is accepted"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Projects sorted by project status!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortFilteredStudentListByProjectStatus();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortProjectStatusCommand)) {
            return false;
        }

        // state check
        SortProjectStatusCommand e = (SortProjectStatusCommand) other;
        return COMMAND_WORD.equals(e.COMMAND_WORD);
    }
}
