package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
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
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

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
        System.out.println(toAssign);
        UniquePersonList persons = (UniquePersonList) model.getPersons();
        UniqueTeamList teams = (UniqueTeamList) model.getTeams();
        //System.out.println(persons.internalList.size());
        Person p = persons.getPerson(toAssign);
        System.out.println(p.getName());
        Team t = teams.getTeam(teamName);
        System.out.println(t.getName());
        model.addPersonToTeam(p, t);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAssign, teamName));
    }

}
