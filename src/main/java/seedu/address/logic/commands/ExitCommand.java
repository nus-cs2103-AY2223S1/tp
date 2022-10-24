package seedu.address.logic.commands;

import picocli.CommandLine;
import seedu.address.model.Model;

/**
 * Terminates the program.
 */
@CommandLine.Command(name = "exit")
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Address Book as requested ...";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }
 
}
