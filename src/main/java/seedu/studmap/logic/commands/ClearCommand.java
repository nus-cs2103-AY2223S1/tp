package seedu.studmap.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.studmap.model.Model;
import seedu.studmap.model.StudMap;

/**
 * Clears the student map.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setStudMap(new StudMap());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
