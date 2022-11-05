package seedu.hrpro.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.hrpro.model.HrPro;
import seedu.hrpro.model.Model;

/**
 * Clears all data in HR Pro Max++.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "HR Pro Max++ has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setHrPro(new HrPro());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
