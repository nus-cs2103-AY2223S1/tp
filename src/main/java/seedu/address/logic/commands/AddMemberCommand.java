package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_MEMBER_INDEX_DESCRIPTION;

import java.util.List;

import picocli.CommandLine;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.team.Team;

/**
 * Adds the person with the specified name to the current team.
 */
@CommandLine.Command(name = AddMemberCommand.COMMAND_WORD,
        aliases = {AddMemberCommand.ALIAS}, mixinStandardHelpOptions = true)
public class AddMemberCommand extends Command {
    public static final String COMMAND_WORD = "member";
    public static final String ALIAS = "m";
    public static final String FULL_COMMAND = AddCommand.COMMAND_WORD + " " + COMMAND_WORD;

    public static final String MESSAGE_USAGE = FULL_COMMAND
            + ": Adds the person with the specified name into the current team.\n"
            + "Parameters: MEMBER_INDEX\n"
            + "Example: " + FULL_COMMAND + " 1";

    public static final String MESSAGE_ADD_MEMBER_SUCCESS = "Added Member: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person is already in the team";
    public static final String MESSAGE_PERSON_NOT_EXISTS = "The person you are trying to add does not exist";
    public static final String MESSAGE_MEMBER_INDEX_OUT_OF_BOUNDS = "Invalid member index provided";

    @CommandLine.Parameters(arity = "1", description = FLAG_MEMBER_INDEX_DESCRIPTION)
    private Index targetPersonIndex;

    @CommandLine.Option(names = {FLAG_HELP_STR, FLAG_HELP_STR_LONG}, usageHelp = true,
            description = FLAG_HELP_DESCRIPTION)
    private boolean help;

    @CommandLine.Spec
    private CommandLine.Model.CommandSpec commandSpec;


    public AddMemberCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (commandSpec.commandLine().isUsageHelpRequested()) {
            return new CommandResult(commandSpec.commandLine().getUsageMessage());
        }
        requireNonNull(model);
        List<Person> memberList = model.getFilteredPersonList();
        if (targetPersonIndex.getOneBased() > memberList.size() || targetPersonIndex.getOneBased() <= 0) {
            throw new CommandException(MESSAGE_MEMBER_INDEX_OUT_OF_BOUNDS);
        }
        Person toAdd = memberList.get(targetPersonIndex.getZeroBased());
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
                && targetPersonIndex == (((AddMemberCommand) other).targetPersonIndex)); // state check
    }
}
