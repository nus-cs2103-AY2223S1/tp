package seedu.address.logic.commands.teams;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USER;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.item.exceptions.ItemCannotBeParentException;
import seedu.address.model.person.Person;

/**
 * Adds an existing user to a team
 */
public class AddUserToTeamCommand extends Command {
    public static final String COMMAND_WORD = "assign";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Assigns an existing contact to an existing team with the specified index\n"
            + "Parameters: "
            + PREFIX_GROUP + "INDEX of team"
            + PREFIX_USER + "INDEX of contact\n"
            + "Where INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " u/1 t/2\n";

    public static final String ASSIGN_SUCCESS = " Contact %s have been assigned to %s%n";

    private final Index userIndex;
    private final Index grpIndex;

    /**
     * Assigns user to group based on their given index
     */
    public AddUserToTeamCommand(Index userIndex, Index grpIndex) {
        this.userIndex = userIndex;
        this.grpIndex = grpIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Group> lastShownTeamList = model.getFilteredTeamList();
        List<Person> lastShownUserList = model.getFilteredPersonList();

        if (grpIndex.getZeroBased() >= lastShownTeamList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TEAM_DISPLAYED_INDEX);
        }

        if (userIndex.getZeroBased() >= lastShownUserList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Group grp = lastShownTeamList.get(grpIndex.getZeroBased());
        Person user = lastShownUserList.get(userIndex.getZeroBased());
        try {
            user.setParent(grp);
        } catch (ItemCannotBeParentException e) {
            throw new CommandException(e.getMessage());
        }
        return new CommandResult(String.format(ASSIGN_SUCCESS, user, grp));
    }
}
