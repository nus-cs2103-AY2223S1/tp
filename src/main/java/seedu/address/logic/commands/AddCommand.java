package seedu.address.logic.commands;

import picocli.CommandLine;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

@CommandLine.Command(name = "add", subcommands = {AddPersonCommand.class, AddMemberCommand.class})
public class AddCommand extends Command {
    @CommandLine.Spec
    private CommandLine.Model.CommandSpec spec;

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException("random");
    }

}
