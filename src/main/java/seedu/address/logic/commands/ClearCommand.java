package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.ProfNus;

/**
 * Clears profNus.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "ProfNus has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setProfNus(new ProfNus());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
