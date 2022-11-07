package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAM_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAM_NAME;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.team.Team;
import seedu.address.model.team.TeamName;

/**
 * Edits team name identified using it's displayed index from the address book.
 */
public class EditTeamCommand extends Command {

    public static final String COMMAND_WORD = "editteam";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the team details identified by the index number of the required team.\n"
            + "Parameters: "
            + PREFIX_TEAM_INDEX + "TEAM INDEX "
            + PREFIX_TEAM_NAME + "TEAM NAME \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TEAM_INDEX + "1 "
            + PREFIX_TEAM_NAME + "TeamNew ";

    public static final String MESSAGE_EDIT_TEAM_SUCCESS = "Edited Team: %1$s";
    public static final String MESSAGE_DUPLICATE_TEAM_NAME = "This team name already exists";


    private final Index targetIndex;
    private final TeamName newTeamTeamName;

    /**
     * Edit team command constructor
     * @param targetIndex of the team in the filtered person list to edit
     * @param newTeamTeamName details to edit the team with
     */
    public EditTeamCommand(Index targetIndex, TeamName newTeamTeamName) {
        requireNonNull(targetIndex);
        requireNonNull(newTeamTeamName);

        this.targetIndex = targetIndex;
        this.newTeamTeamName = newTeamTeamName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Team> lastShownList = model.getFilteredTeamList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TEAM_DISPLAYED_INDEX);
        }

        if (model.teamNameExists(newTeamTeamName)) {
            throw new CommandException(MESSAGE_DUPLICATE_TEAM_NAME);
        }

        model.setTeamName(targetIndex, newTeamTeamName);
        return new CommandResult(String.format(MESSAGE_EDIT_TEAM_SUCCESS, newTeamTeamName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditTeamCommand // instanceof handles nulls
                && targetIndex.equals(((EditTeamCommand) other).targetIndex)
                && newTeamTeamName.equals(((EditTeamCommand) other).newTeamTeamName)); // state check
    }
}
