package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR_LONG;

import picocli.CommandLine;
import seedu.address.model.Model;

/**
 * Switches the theme of the program.
 */
@CommandLine.Command(name = ThemeCommand.COMMAND_WORD, aliases = {ThemeCommand.ALIAS}, mixinStandardHelpOptions = true)
public class ThemeCommand extends Command {
    public static final String COMMAND_WORD = "theme";
    public static final String ALIAS = "th";
    public static final String FULL_COMMAND = COMMAND_WORD;
    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Switched themes!";
    public static final String HELP_MESSAGE =
            "The '" + FULL_COMMAND + "' command is used to toggle between the light and dark theme of TruthTable.\n";

    @CommandLine.Option(names = {FLAG_HELP_STR, FLAG_HELP_STR_LONG}, usageHelp = true,
            description = FLAG_HELP_DESCRIPTION)
    private boolean help;

    @CommandLine.Spec
    private CommandLine.Model.CommandSpec commandSpec;

    @Override
    public CommandResult execute(Model model) {
        if (commandSpec.commandLine().isUsageHelpRequested()) {
            return new CommandResult(HELP_MESSAGE + commandSpec.commandLine().getUsageMessage());
        }
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ThemeCommand); // instanceof handles nulls
    }
}
