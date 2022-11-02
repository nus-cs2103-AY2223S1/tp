package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR_LONG;

import picocli.CommandLine;
import seedu.address.model.Model;
import seedu.address.model.TruthTable;

/**
 * Clears the TruthTable.
 */
@CommandLine.Command(name = "clear", aliases = {"c"}, mixinStandardHelpOptions = true)
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";

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
        requireNonNull(model);
        model.setTruthTable(TruthTable.createNewTruthTable());
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
