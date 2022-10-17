package seedu.foodrem.logic.commands.generalcommands;

import static seedu.foodrem.enums.CommandType.EXIT_COMMAND;

import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {
    private static final String MESSAGE_SUCCESS = "Exiting Address Book as requested ...";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_SUCCESS, false, true);
    }

    public static String getUsage() {
        return EXIT_COMMAND.getUsage();
    }
}
