package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_LINK_INDEX_DESCRIPTION;

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

    public static final String MESSAGE_USAGE = FULL_COMMAND
            + ": Deletes an existing link identified by the index number used in the displayed link list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + FULL_COMMAND + " 1";
    public static final String MESSAGE_DELETE_LINK_SUCCESS = "Deleted Link: %1$s";

    @CommandLine.Parameters(arity = "1", description = FLAG_LINK_INDEX_DESCRIPTION)
    private Index index;

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
            return new CommandResult(commandSpec.commandLine().getUsageMessage());
        }
        requireNonNull(model);
        List<Link> lastShownList = model.getLinkList();
        if (index.getOneBased() > lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_LINK_DISPLAYED_INDEX);
        }
        Link linkToDelete = lastShownList.get(index.getZeroBased());
        model.deleteLink(linkToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_LINK_SUCCESS, linkToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteLinkCommand // instanceof handles nulls
                && index.equals(((DeleteLinkCommand) other).index)); // state check
    }
}
