package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Swaps between Address Books.
 */
public class SwapCommand extends Command {

    public static final String COMMAND_WORD = "swap";

    public static final String MESSAGE_SWAP_ACKNOWLEDGEMENT = "Swapping Address Book as requested ...";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_SWAP_ACKNOWLEDGEMENT, null, false, false, true, false);
    }

}
