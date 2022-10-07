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


/**
 * Adds a person to the address book.
 */
public class UnAssignMemberCommand extends Command {

    public static final String COMMAND_WORD = "unassign";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": removes a person from the team. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_TEAM_NAME + "TEAM NAME \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_TEAM_NAME + "Team1";

    public static final String MESSAGE_SUCCESS = "Person removed: %1$s to team: %2$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the team";
    public static final String MESSAGE_ARGUMENTS = "Person: %1$s, Team: %2$s";

    private final Name toUnAssign;
    private final seedu.address.model.team.Name teamName;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public UnAssignMemberCommand(Name person, seedu.address.model.team.Name teamName) {
        requireAllNonNull(person, teamName);
        this.toUnAssign = person;
        this.teamName = teamName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person p = model.getPerson(toUnAssign);
        Team t = model.getTeam(teamName);
        model.removePersonFromTeam(p, t);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toUnAssign, teamName));
    }

}
