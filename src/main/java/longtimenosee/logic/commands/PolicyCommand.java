package longtimenosee.logic.commands;

import static java.util.Objects.requireNonNull;

import longtimenosee.model.Model;

/**
 * Lists all clients in the LTNS to the user.
 */
public class PolicyCommand extends Command {

    public static final String COMMAND_WORD = "policies";

    public static final String MESSAGE_SUCCESS = "Listed all currently filtered policies";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_SUCCESS, true, false, false);
    }
}
