package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.item.AbstractContainerItem;

/**
 * Add a group to the address book.
 */
public class AddGroupCommand extends Command {

    public static final String COMMAND_WORD = "mkgroup";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add a new group in the current "
        + "group scope if the group name does not currently exist. The group name should only "
        + "be alphanumeric and have hyphens and/or underscores only.\n"
        + "Parameters: group_name/group_within_group_name\n"
        + "Example: " + COMMAND_WORD + " group_1/group_a";

    public static final String MESSAGE_SUCCESS = "New group added: %1$s";
    public static final String MESSAGE_DUPLICATE_GROUP = "This group already exists in the address book";

    private final Group toAdd;

    /**
     * Creates a AddGroupCommand to add the specified {@Code group}
     *
     * @param group that is being added to the AddressBook.
     */
    public AddGroupCommand(Group group) {
        requireNonNull(group);
        toAdd = group;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTeam(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_GROUP);
        }

        AbstractContainerItem currentContext = model.getContextContainer();

        if (currentContext != null) {
            toAdd.setParent(currentContext);
        }
        model.addTeam(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}
