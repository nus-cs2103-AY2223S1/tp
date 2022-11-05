package longtimenosee.logic.commands;

import static java.util.Objects.requireNonNull;

import longtimenosee.model.AddressBook;
import longtimenosee.model.Model;

/**
 * Clears the LTNS.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS, false, true, false);
    }
}
