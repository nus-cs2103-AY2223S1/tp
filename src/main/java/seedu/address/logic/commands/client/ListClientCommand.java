package seedu.address.logic.commands.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.ui.Ui;

/**
 * Lists all persons in the address book to the user.
 */
public class ListClientCommand extends ClientCommand {

    public static final String COMMAND_FLAG = "-l";

    public static final String MESSAGE_SUCCESS = "Listed all persons";
    
    // TODO: revise implementation
    @Override
    public CommandResult execute(Model model, Ui ui) {
        requireNonNull(model);
        ui.showClients();
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
