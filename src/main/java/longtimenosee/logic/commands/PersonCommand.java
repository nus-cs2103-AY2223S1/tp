package longtimenosee.logic.commands;

import longtimenosee.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class PersonCommand extends Command {

    public static final String COMMAND_WORD = "showClient";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Listed all clients";


    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_SUCCESS, false, false, false, true);
    }
}
