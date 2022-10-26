package seedu.address.logic.commands.client;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.project.Project;
import seedu.address.ui.Ui;

/**
 * A delete client command to delete clients
 */
public class DeleteClientCommand extends ClientCommand {

    public static final String COMMAND_FLAG = "-d";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " " + COMMAND_FLAG
            + ": Deletes the client by its Id. Id must be positive and valid \n"
            + "Parameters: CLIENT_ID \n"
            + "Example: " + COMMAND_WORD + " " + COMMAND_FLAG + " 1";

    public static final String MESSAGE_SUCCESS = "Deleted Client: %1$s";

    private final Index targetIndex;

    public DeleteClientCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, Ui ui) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getFilteredClientList();

        for (Client c : lastShownList) {
            if (c.getClientId().getIdInt() == targetIndex.getOneBased()) {
                Client clientToDelete = c;
                model.deleteClient(clientToDelete);
                for (Project p: clientToDelete.getProjects()) {
                    p.removeClient();
                }
                ui.showClients();
                model.updateFilteredClientList(Model.PREDICATE_SHOW_ALL_CLIENTS);
                return new CommandResult(String.format(MESSAGE_SUCCESS, clientToDelete));
            }
        }

        throw new CommandException(Messages.MESSAGE_INVALID_ISSUE_DISPLAYED_ID);

    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DeleteClientCommand
                && targetIndex.equals(((DeleteClientCommand) other).targetIndex));
    }
}

