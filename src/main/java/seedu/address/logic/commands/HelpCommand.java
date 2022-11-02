package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR_LONG;
import static seedu.address.ui.HelpWindow.USERGUIDE_URL;

import picocli.CommandLine;
import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
@CommandLine.Command(name = HelpCommand.COMMAND_WORD, aliases = {HelpCommand.ALIAS}, mixinStandardHelpOptions = true)
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    public static final String ALIAS = "h";
    public static final String FULL_COMMAND = COMMAND_WORD;

    public static final String MESSAGE_USAGE = FULL_COMMAND + ": Shows program usage instructions.\n"
            + "Example: " + FULL_COMMAND;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    @CommandLine.Spec
    private CommandLine.Model.CommandSpec commandSpec;

    @CommandLine.Option(names = {FLAG_HELP_STR, FLAG_HELP_STR_LONG}, usageHelp = true,
            description = FLAG_HELP_DESCRIPTION)
    private boolean help;

    @Override
    public CommandResult execute(Model model) {
        if (commandSpec.commandLine().isUsageHelpRequested()) {
            return new CommandResult(commandSpec.commandLine().getUsageMessage());
        }
        CommandLine parent = commandSpec.commandLine().getParent();
        return new CommandResult(parent.getUsageMessage() + "\nAccess our user guide here: " + USERGUIDE_URL);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HelpCommand); // instanceof handles nulls
    }
}
