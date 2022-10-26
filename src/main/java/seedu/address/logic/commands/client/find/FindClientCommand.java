package seedu.address.logic.commands.client.find;

import static seedu.address.logic.parser.ClientCliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.ClientCliSyntax.PREFIX_MOBILE;
import static seedu.address.logic.parser.ClientCliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.client.ClientCommand;


/**
 * Represents an abstract class to find and filter client list.
 */
public abstract class FindClientCommand extends ClientCommand {

    public static final String COMMAND_FLAG = "-f";
    public static final String MESSAGE_SUCCESS = "Filtered list of clients shown.";
    private static final String MESSAGE_CLIENT_NOT_FOUND = "A client matching requirements not found.";
    public static final String MESSAGE_FIND_CLIENT_USAGE = COMMAND_WORD + ": Finds and filters clients by keyword "
            + "address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_MOBILE + "MOBILE "
            + "Example: " + COMMAND_WORD + " "
            + COMMAND_FLAG + " "
            + PREFIX_NAME + "John "
            + PREFIX_EMAIL + "john@gmail.com "
            + PREFIX_MOBILE + "98765432 ";
}
