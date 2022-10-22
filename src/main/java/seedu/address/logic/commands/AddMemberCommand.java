package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.NamePredicate;
import seedu.address.model.person.Person;
import seedu.address.model.team.Team;

/**
 * Adds the person with the specified name to the current team.
 */
public class AddMemberCommand extends Command {

    public static final String COMMAND_WORD = "add_member";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds the person with the specified name into the current team.\n"
            + "Parameters: NAME\n"
            + "Example: " + COMMAND_WORD + " Alice";

    public static final String MESSAGE_ADD_MEMBER_SUCCESS = "Added Member: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person is already in the team";
    public static final String MESSAGE_PERSON_NOT_EXISTS = "The person you are trying to add does not exist";

    private final Name targetPersonName;

    /**
     * Creates an AddMemberCommand to add the {@code Person} with the specified {@code Name} to the current {@code Team}
     */
    public AddMemberCommand(Name targetPersonName) {
        requireNonNull(targetPersonName);
        this.targetPersonName = targetPersonName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> matchingPersons =
                model.getFilteredPersonList(new NamePredicate(targetPersonName));

        if (matchingPersons.size() == 0) {
            throw new CommandException(MESSAGE_PERSON_NOT_EXISTS);
        }

        // Filtered persons list will always have either size 0 or 1, as names are unique
        assert matchingPersons.size() == 1;

        Person toAdd = matchingPersons.get(0);
        Team team = model.getTeam();
        if (team.hasMember(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        team.addMember(toAdd);
        return new CommandResult(String.format(MESSAGE_ADD_MEMBER_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddMemberCommand // instanceof handles nulls
                && targetPersonName.equals(((AddMemberCommand) other).targetPersonName)); // state check
    }
}
