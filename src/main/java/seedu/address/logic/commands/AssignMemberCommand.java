package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAM_INDEX;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.team.Name;
import seedu.address.model.team.Team;


/**
 * Adds a person to the address book.
 */
public class AssignMemberCommand extends Command {

    public static final String COMMAND_WORD = "assign";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Assigns a person to the team. \n"
            + "Parameters: "
            + PREFIX_MEMBER_INDEX + "MEMBER INDEX "
            + PREFIX_TEAM_INDEX + "TEAM INDEX \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MEMBER_INDEX + "1 "
            + PREFIX_TEAM_INDEX + "1 ";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s to team: %2$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the team";
    public static final String MESSAGE_ARGUMENTS = "Person: %1$s, Team: %2$s";

    private final Index personIndex;
    private final Index teamIndex;

    /**
     * Creates an AssignMemberCommand to assign member to the specified {@code Team}
     */
    public AssignMemberCommand(Index personIndex, Index teamIndex) {
        requireAllNonNull(personIndex, teamIndex);
        this.personIndex = personIndex;
        this.teamIndex = teamIndex;

    }


    @Override
    public CommandResult execute(Model model) throws CommandException {

        requireNonNull(model);

        List<Team> lastShownList = model.getFilteredTeamList();
        List<Person> personList = model.getFilteredPersonList();

        if (teamIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TEAM_DISPLAYED_INDEX);
        }

        if (personIndex.getZeroBased() >= personList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Team team = model.getTeamUsingIndex(teamIndex);
        Person person = model.getPersonUsingIndex(personIndex);

        if (model.teamHasMember(personIndex, teamIndex)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPersonToTeam(person, team);
        Name teamName = model.getTeamName(teamIndex);
        seedu.address.model.person.Name personName = model.getPersonName(personIndex);

        return new CommandResult(String.format(MESSAGE_SUCCESS, personName, teamName));
    }

}
