package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAM_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.team.Team;
import seedu.address.model.team.UniqueTeamList;


/**
 * Adds a person to the address book.
 */
public class AssignMemberCommand extends Command {

    public static final String COMMAND_WORD = "assign";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Assigns a person to the team. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_TEAM_NAME + "TEAM NAME \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_TEAM_NAME + "Team1";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s to team: %2$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the team";
    public static final String MESSAGE_ARGUMENTS = "Person: %1$s, Team: %2$s";

    private final Name toAssign;
    private final seedu.address.model.team.Name teamName;
    private final UniqueTeamList teams = new UniqueTeamList();

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AssignMemberCommand(Name person, seedu.address.model.team.Name teamName) {
        requireAllNonNull(person, teamName);
        this.toAssign = person;
        this.teamName = teamName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person p = model.getPerson(toAssign);
        Team t = model.getTeam(teamName);
        model.addPersonToTeam(p, t);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAssign, teamName));
    }

}
