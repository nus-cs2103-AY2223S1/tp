package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import picocli.CommandLine;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Command that contains all subcommands starting with {@code find}.
 */
@CommandLine.Command(name = "find", aliases = {"f"}, subcommands = {
    FindPersonCommand.class,
    FindMemberCommand.class,
    FindTaskCommand.class
})
public class FindCommand extends Command {
    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(MESSAGE_UNKNOWN_COMMAND);
    }

}
