package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_LINK_INDEX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.LABEL_LINK_INDEX;

import java.util.List;

import picocli.CommandLine;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.team.Link;

/**
 * Deletes an existing link from TruthTable.
 */
@CommandLine.Command(name = DeleteLinkCommand.COMMAND_WORD,
        aliases = {DeleteLinkCommand.ALIAS}, mixinStandardHelpOptions = true)
public class DeleteLinkCommand extends Command {
    public static final String COMMAND_WORD = "link";
    public static final String ALIAS = "l";
    public static final String FULL_COMMAND = DeleteCommand.COMMAND_WORD + " " + COMMAND_WORD;
    public static final String HELP_MESSAGE =
            "The '" + FULL_COMMAND + "' command is used to delete a link from the current team.\n";

    public static final String MESSAGE_DELETE_LINK_SUCCESS = "Deleted Link: %1$s";

    @CommandLine.Parameters(arity = "1",
            paramLabel = LABEL_LINK_INDEX,
            description = FLAG_LINK_INDEX_DESCRIPTION)
    private Index targetIndex;

    @CommandLine.Option(names = {FLAG_HELP_STR, FLAG_HELP_STR_LONG}, usageHelp = true,
            description = FLAG_HELP_DESCRIPTION)
    private boolean help;

    @CommandLine.Spec
    private CommandLine.Model.CommandSpec commandSpec;

    public DeleteLinkCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (commandSpec.commandLine().isUsageHelpRequested()) {
            return new CommandResult(HELP_MESSAGE + commandSpec.commandLine().getUsageMessage());
        }
        requireNonNull(model);
        List<Link> lastShownList = model.getLinkList();
        if (targetIndex.getOneBased() > lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_LINK_DISPLAYED_INDEX);
        }
        Link linkToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteLink(linkToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_LINK_SUCCESS, linkToDelete));
    }

}
