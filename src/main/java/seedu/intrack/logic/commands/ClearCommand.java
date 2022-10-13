package seedu.intrack.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.intrack.model.InTrack;
import seedu.intrack.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Internship tracker has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setInTrack(new InTrack());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
