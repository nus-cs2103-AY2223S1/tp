package seedu.address.logic.commands.teams;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.PureCommandInterface;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;

// @@author autumn-sonata
/**
 * Add a team to the address book.
 */
public class AddTeamCommand extends TeamCommand implements PureCommandInterface {
    public static final String SUBCOMMAND_WORD = "new";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + SUBCOMMAND_WORD
        + ": Add a new group in the current "
        + "group scope if the group name does not currently exist.\n"
        + "The group name should only begin with a letter and "
        + "be alphanumeric and have hyphens and/or underscores only\n"
        + "Parameters: group_name/group_within_group_name\n"
        + "Example: " + COMMAND_WORD + " " + SUBCOMMAND_WORD + " group_c345";
    // + "Example: " + COMMAND_WORD + " group_1/group_a\n"

    public static final String MESSAGE_SUCCESS = "New team added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This team already exists in the address book";

    private Group toAdd;

    public AddTeamCommand(Group toAdd) {
        this.toAdd = toAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        toAdd.setParent(model.getContextContainer());
        if (model.hasTeam(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }
        model.addTeam(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), false, false, toAdd);
    }

    @Override
    public Command setInput(Object additionalData) throws CommandException {
        // this method does not take in an input
        return this;
    }
}
