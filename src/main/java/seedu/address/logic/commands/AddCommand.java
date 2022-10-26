package seedu.address.logic.commands;

import picocli.CommandLine;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Command that contains all subcommands starting with {@code add}.
 */
@CommandLine.Command(name = "add", subcommands = {
    AddPersonCommand.class,
    AddMemberCommand.class,
    AddTaskCommand.class,
    AddTeamCommand.class,
    AddLinkCommand.class
})
public class AddCommand extends Command {
    @CommandLine.Spec
    private CommandLine.Model.CommandSpec commandSpec;

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(commandSpec.commandLine().getUsageMessage());
    }
}
