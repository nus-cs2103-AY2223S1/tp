package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;

/**
 * Deletes a group identified using a string name from the address book.
 */
public class DeleteGroupCommand extends Command {

    public static final String COMMAND_WORD = "deletegroup";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the group identified by the GROUPNAME used.\n"
            + "Parameters: "
            + PREFIX_GROUP + "GROUPNAME "
            + "(must be one currently in Address Book)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_GROUP + "CS2103T Team Project";

    public static final String MESSAGE_SUCCESS = "Deleted group: %1$s";
    private static final String MESSAGE_UNKNOWN_GROUP = "The group with this GROUPNAME does not exist.";

    private final Group targetGroup;

    public DeleteGroupCommand(Group targetGroup) {
        this.targetGroup = targetGroup;
    }

    /** Executes DeleteGroupCommand */
    public CommandResult execute(Model model) throws CommandException {
        //todo remove assignments from members when group is deleted
        requireNonNull(model);

        if (!model.hasGroup(targetGroup)) {
            throw new CommandException(MESSAGE_UNKNOWN_GROUP);
        }
        model.deleteGroup(targetGroup);
        return new CommandResult(String.format(MESSAGE_SUCCESS, targetGroup));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DeleteGroupCommand
                && targetGroup.equals(((DeleteGroupCommand) other).targetGroup));
    }
}
