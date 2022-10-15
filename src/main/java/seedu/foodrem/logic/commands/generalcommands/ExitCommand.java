package seedu.foodrem.logic.commands.generalcommands;

import seedu.foodrem.enums.CommandWord;
import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Address Book as requested ...";

    private static final String COMMAND_WORD = CommandWord.EXIT_COMMAND.getCommandWord();
    private static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits FoodRem."
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

    public static String getUsage() {
        return MESSAGE_USAGE;
    }
}
