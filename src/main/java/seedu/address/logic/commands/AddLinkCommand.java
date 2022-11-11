package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_LINK_NAME_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_LINK_URL_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_URL_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_URL_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.LABEL_LINK_NAME;
import static seedu.address.logic.parser.CliSyntax.LABEL_LINK_URL;

import picocli.CommandLine;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.team.Link;
import seedu.address.model.team.LinkName;
import seedu.address.model.team.Url;

/**
 * Adds a new link to TruthTable.
 */
@CommandLine.Command(name = AddLinkCommand.COMMAND_WORD,
        aliases = {AddLinkCommand.ALIAS}, mixinStandardHelpOptions = true)
public class AddLinkCommand extends Command {
    public static final String COMMAND_WORD = "link";
    public static final String ALIAS = "l";
    public static final String FULL_COMMAND = AddCommand.COMMAND_WORD + " " + COMMAND_WORD;
    public static final String HELP_MESSAGE =
            "The '" + FULL_COMMAND + "' command is used to add links relevant to the current team.\n";

    public static final String MESSAGE_SUCCESS = "New link added: %1$s";
    public static final String MESSAGE_DUPLICATE_LINK = "This link already exists in team";

    @CommandLine.Option(names = {FLAG_NAME_STR, FLAG_NAME_STR_LONG}, required = true,
            paramLabel = LABEL_LINK_NAME,
            description = FLAG_LINK_NAME_DESCRIPTION)
    private LinkName name;

    @CommandLine.Option(names = {FLAG_URL_STR, FLAG_URL_STR_LONG}, required = true,
            paramLabel = LABEL_LINK_URL,
            description = FLAG_LINK_URL_DESCRIPTION)
    private Url url;

    @CommandLine.Option(names = {FLAG_HELP_STR, FLAG_HELP_STR_LONG}, usageHelp = true,
            description = FLAG_HELP_DESCRIPTION)
    private boolean help;

    @CommandLine.Spec
    private CommandLine.Model.CommandSpec commandSpec;


    public AddLinkCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (commandSpec.commandLine().isUsageHelpRequested()) {
            return new CommandResult(HELP_MESSAGE + commandSpec.commandLine().getUsageMessage());
        }
        requireNonNull(model);
        Link toAdd = new Link(name, url);

        if (model.hasLink(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_LINK);
        }
        model.addLink(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

}
