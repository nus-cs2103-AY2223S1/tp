package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_GROUPS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.FullGroupNamePredicate;
import seedu.address.model.group.Group;

/**
 * Lists all persons in the address book to the user.
 */
public class DisplayGroupCommand extends Command {

    public static final String COMMAND_WORD = "displaygroup";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Shows the group identified by its name in the displayed group list.\n"
        + "Parameters: NAME (must be exactly the same as group's name)\n"
        + "Example: " + COMMAND_WORD + " GroupName ";

    public static final String MESSAGE_SUCCESS = "Group found!";

    private final FullGroupNamePredicate predicate;

    public DisplayGroupCommand(FullGroupNamePredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredGroupList(PREDICATE_SHOW_ALL_GROUPS);
        List<Group> lastShownList = model.getFilteredGroupList();

        int targetIndex = -1;
        for (int i = 0; i < lastShownList.size(); i++) {
            Group currentGroup = lastShownList.get(i);
            if (predicate.test(currentGroup)) {
                targetIndex = i;
                break;
            }
        }
        if (targetIndex == -1) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_GROUP_NAME));
        }

        model.updateFilteredGroupList(predicate);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
