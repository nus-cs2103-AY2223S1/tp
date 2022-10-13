package seedu.address.logic.commands.groups;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;

import static java.util.Objects.requireNonNull;

/**
 * Add a team to the address book.
 */
public class GroupCommand extends Command {
    public static final String COMMAND_WORD = "team";

    public static final String MESSAGE_SUCCESS = "New team added: %1$s!";
    public static final String MESSAGE_DUPLICATE_TEAM = "This team already exists in the address book";

    private final Group toAdd;
    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public GroupCommand(Group team) {
        requireNonNull(team);
        toAdd = team;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTeam(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TEAM);
        }

        model.addTeam(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GroupCommand // instanceof handles nulls
                && toAdd.equals(((GroupCommand) other).toAdd));
    }
}
