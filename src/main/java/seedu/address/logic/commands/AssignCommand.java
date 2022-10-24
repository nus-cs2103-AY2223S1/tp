package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import picocli.CommandLine;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

@CommandLine.Command(name = "assign", subcommands = {
    AssignTaskCommand.class,
    AssignTaskRandomlyCommand.class,
})
public class AssignCommand extends Command {
    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(MESSAGE_UNKNOWN_COMMAND);
    }

}
