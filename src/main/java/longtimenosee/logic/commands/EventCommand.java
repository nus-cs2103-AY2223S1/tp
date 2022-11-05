package longtimenosee.logic.commands;

import static java.util.Objects.requireNonNull;

import longtimenosee.model.Model;

/**
 * Lists all currently filtered events in the address book to the user.
 */
public class EventCommand extends Command {

    public static final String COMMAND_WORD = "events";

    public static final String MESSAGE_SUCCESS = "Listed all currently filtered events";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_SUCCESS, false, false, true);
    }
}
