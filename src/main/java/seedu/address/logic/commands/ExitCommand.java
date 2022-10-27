package seedu.address.logic.commands;

import picocli.CommandLine;
import seedu.address.model.Model;
import seedu.address.ui.MainWindow;

/**
 * Terminates the program.
 */
@CommandLine.Command(name = "exit", aliases = {"ex", "bye", "quit"})
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT =
            "Exiting Address Book as requested (will close in " + MainWindow.DELAY_DURATION_SECONDS + "s)...";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
