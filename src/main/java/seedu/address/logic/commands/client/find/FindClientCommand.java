package seedu.address.logic.commands.client.find;

import seedu.address.logic.commands.client.ClientCommand;

public abstract class FindClientCommand extends ClientCommand {

    public static final String COMMAND_FLAG = "-f";
    public static final String MESSAGE_SUCCESS = "Filtered list of clients shown.";
    private static final String MESSAGE_CLIENT_NOT_FOUND = "A client matching requirements not found.";
    
}
