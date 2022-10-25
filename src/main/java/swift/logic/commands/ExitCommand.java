package swift.logic.commands;

import java.util.HashMap;

import swift.logic.parser.Prefix;
import swift.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";
    public static final HashMap<Prefix, String> ARGUMENT_PROMPTS = new HashMap<>();

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Address Book as requested ...";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
