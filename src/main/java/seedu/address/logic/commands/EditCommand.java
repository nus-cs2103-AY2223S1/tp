package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import picocli.CommandLine;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Command that contains all subcommands starting with {@code edit}.
 */
@CommandLine.Command(name = "edit", aliases = {"e"}, subcommands = {
    EditLinkCommand.class,
    EditPersonCommand.class,
})
public class EditCommand extends Command {
    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(MESSAGE_UNKNOWN_COMMAND);
    }

}
