package seedu.address.logic.commands;

import static seedu.address.ui.HelpWindow.USERGUIDE_URL;

import picocli.CommandLine;
import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
@CommandLine.Command(name = "help")
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    @CommandLine.Spec
    private CommandLine.Model.CommandSpec commandSpec;

    @Override
    public CommandResult execute(Model model) {
        CommandLine parent = commandSpec.commandLine().getParent();
        return new CommandResult(parent.getUsageMessage() + "\nAccess our user guide here: " + USERGUIDE_URL);
    }

}
