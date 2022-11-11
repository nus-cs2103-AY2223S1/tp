package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.FindMyIntern;
import seedu.address.model.Model;

/**
 * Clears findMyIntern.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Internship list has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setFindMyIntern(new FindMyIntern());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
