package seedu.address.logic.commands.consultation;


import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Clears all consultations.
 */
public class ClearConsultationCommand extends Command {

    public static final String COMMAND_WORD = "clear consultation";
    public static final String MESSAGE_SUCCESS = "All consultations have been cleared!";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.resetConsultations();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
