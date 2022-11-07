package longtimenosee.logic.commands;

import longtimenosee.model.Model;

/**
 * Lists all Clients in the address book to the user.
 */
public class PersonCommand extends Command {

    public static final String COMMAND_WORD = "clients";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Listed all currently filtered clients";


    @Override
    public CommandResult execute(Model model) {

        return new CommandResult(MESSAGE_SUCCESS, false, false, false, true, false, false);

    }
}
