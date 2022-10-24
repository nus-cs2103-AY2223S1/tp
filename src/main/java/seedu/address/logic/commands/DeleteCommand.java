package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import picocli.CommandLine;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

@CommandLine.Command(name = "delete", subcommands = {
    DeleteLinkCommand.class,
    DeleteMemberCommand.class,
    DeletePersonCommand.class,
    DeleteTaskCommand.class,
    DeleteTeamCommand.class,
})
public class DeleteCommand extends Command {
    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(MESSAGE_UNKNOWN_COMMAND);
    }

}
