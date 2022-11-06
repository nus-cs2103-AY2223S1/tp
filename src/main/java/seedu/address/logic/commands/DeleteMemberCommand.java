package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_MEMBER_INDEX_DESCRIPTION;

import java.util.List;

import picocli.CommandLine;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes a member identified using it's displayed index from the member list.
 */
@CommandLine.Command(name = DeleteMemberCommand.COMMAND_WORD,
        aliases = {DeleteMemberCommand.ALIAS}, mixinStandardHelpOptions = true)
public class DeleteMemberCommand extends Command {
    public static final String COMMAND_WORD = "member";
    public static final String ALIAS = "m";
    public static final String FULL_COMMAND = DeleteCommand.COMMAND_WORD + " " + COMMAND_WORD;
    public static final String HELP_MESSAGE =
            "The 'delete member' command is used to delete a member from the current team.\n";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Member: %1$s";

    @CommandLine.Parameters(arity = "1", description = FLAG_MEMBER_INDEX_DESCRIPTION)
    private Index targetIndex;

    @CommandLine.Option(names = {FLAG_HELP_STR, FLAG_HELP_STR_LONG}, usageHelp = true,
            description = FLAG_HELP_DESCRIPTION)
    private boolean help;

    @CommandLine.Spec
    private CommandLine.Model.CommandSpec commandSpec;

    public DeleteMemberCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (commandSpec.commandLine().isUsageHelpRequested()) {
            return new CommandResult(HELP_MESSAGE + commandSpec.commandLine().getUsageMessage());
        }
        requireNonNull(model);
        List<Person> teamMembers = model.getTeam().getTeamMembers();

        if (targetIndex.getZeroBased() >= teamMembers.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = teamMembers.get(targetIndex.getZeroBased());
        model.getTeam().removeMember(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteMemberCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteMemberCommand) other).targetIndex)); // state check
    }

}
