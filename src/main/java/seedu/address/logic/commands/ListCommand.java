package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends PureCommand {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons in the current context!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.refresh();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
