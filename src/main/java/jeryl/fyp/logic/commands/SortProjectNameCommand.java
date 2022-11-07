package jeryl.fyp.logic.commands;

import static java.util.Objects.requireNonNull;
import static jeryl.fyp.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import jeryl.fyp.model.Model;

/**
 * Sorts all projects in the FYP manager by project name.
 */
public class SortProjectNameCommand extends Command {

    public static final String COMMAND_WORD = "sort -p";

    public static final String ALTERNATIVE_COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the FYP projects "
            + "by the project names in alphabetical order.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Projects sorted by project name!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(MESSAGE_SUCCESS, false, false, true, false);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortProjectNameCommand)) {
            return false;
        }

        // state check
        SortProjectNameCommand e = (SortProjectNameCommand) other;
        return COMMAND_WORD.equals(e.COMMAND_WORD);
    }
}
