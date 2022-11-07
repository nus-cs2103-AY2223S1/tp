package seedu.address.logic.commands.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CLIENTS;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.ui.Ui;

/**
 * Lists all persons in the project book to the user.
 */
public class ListClientCommand extends ClientCommand {

    public static final String COMMAND_FLAG = "-l";

    public static final String MESSAGE_SUCCESS = "Listed all clients in the project book";

    @Override
    public CommandResult execute(Model model, Ui ui) {
        requireNonNull(model);
        ui.showClients();
        model.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
