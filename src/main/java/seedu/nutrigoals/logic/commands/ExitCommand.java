package seedu.nutrigoals.logic.commands;

import seedu.nutrigoals.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";
    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting NutriGoals as requested ...";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Closes NutriGoals. \nExample: exit";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
