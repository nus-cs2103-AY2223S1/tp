package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.model.Model;
import seedu.address.model.client.Client;

/**
 * Lists all persons in MyInsuRec to the user.
 */
public class ListClientCommand extends Command {

    public static final String COMMAND_WORD = "listClient";
    public static final String MESSAGE_SUCCESS = "Listed all clients";
    private final Predicate<Client> predicate;

    public ListClientCommand(Predicate<Client> predicate) {
        this.predicate = predicate;
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredClientList(predicate);
        return new CommandResult(MESSAGE_SUCCESS, CommandSpecific.CLIENT);
    }
}
