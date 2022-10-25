package jeryl.fyp.logic.commands;

import static java.util.Objects.requireNonNull;

import jeryl.fyp.model.Model;

/**
 * Sorts all projects in the FYP manager by the specialisation, which is equivalent to the project name
 */
public class SortSpecialisationCommand extends Command {

    public static final String COMMAND_WORD = "sort-sp";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the FYP projects "
            + "by the specialisation of the  projects followed by alphabetical order"
            + "Note that the format is fixed, hence only " + COMMAND_WORD + " is accepted"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Projects sorted by specialisation!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortFilteredStudentListBySpecialisation();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortSpecialisationCommand)) {
            return false;
        }

        // state check
        SortSpecialisationCommand e = (SortSpecialisationCommand) other;
        return COMMAND_WORD.equals(e.COMMAND_WORD);
    }
}
