package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAMNAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.team.Team;

/**
 * Adds a person to the address book.
 */
public class CreateTeamCommand extends Command {

    public static final String COMMAND_WORD = "create";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Created a team. "
            + "Parameters: "
            + PREFIX_TEAMNAME + "NAME ";

    public static final String MESSAGE_SUCCESS = "New team added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This team already exists";

    private final Team toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public CreateTeamCommand(Team team) {
        requireNonNull(team);
        toAdd = team;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTeam(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addTeam(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CreateTeamCommand // instanceof handles nulls
                && toAdd.equals(((CreateTeamCommand) other).toAdd));
    }
}
