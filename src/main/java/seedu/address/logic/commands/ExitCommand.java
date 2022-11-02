package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR_LONG;

import picocli.CommandLine;
import seedu.address.model.Model;
import seedu.address.ui.MainWindow;

/**
 * Terminates the program.
 */
@CommandLine.Command(name = ExitCommand.COMMAND_WORD,
        aliases = {ExitCommand.ALIAS_EX, ExitCommand.ALIAS_BYE, ExitCommand.ALIAS_QUIT},
        mixinStandardHelpOptions = true)
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";
    public static final String ALIAS_EX = "ex";
    public static final String ALIAS_BYE = "bye";
    public static final String ALIAS_QUIT = "quit";
    public static final String FULL_COMMAND = COMMAND_WORD;

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT =
            "Exiting TruthTable as requested (will close in " + MainWindow.DELAY_DURATION_SECONDS + "s)...";

    @CommandLine.Option(names = {FLAG_HELP_STR, FLAG_HELP_STR_LONG}, usageHelp = true,
            description = FLAG_HELP_DESCRIPTION)
    private boolean help;

    @CommandLine.Spec
    private CommandLine.Model.CommandSpec commandSpec;

    @Override
    public CommandResult execute(Model model) {
        if (commandSpec.commandLine().isUsageHelpRequested()) {
            return new CommandResult(commandSpec.commandLine().getUsageMessage());
        }
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExitCommand); // instanceof handles nulls
    }

}
