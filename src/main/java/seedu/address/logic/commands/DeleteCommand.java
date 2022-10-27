package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_WITH_HELP_FORMAT;

import picocli.CommandLine;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Command that contains all subcommands starting with {@code delete}.
 */
@CommandLine.Command(name = "delete", subcommands = {
    DeleteLinkCommand.class,
    DeleteMemberCommand.class,
    DeletePersonCommand.class,
    DeleteTaskCommand.class,
    DeleteTeamCommand.class,
})
public class DeleteCommand extends Command {
    @CommandLine.Spec
    private CommandLine.Model.CommandSpec commandSpec;

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(String.format(MESSAGE_INVALID_COMMAND_WITH_HELP_FORMAT,
                commandSpec.qualifiedName().trim()));
    }
}
