package seedu.foodrem.logic.parser.tagcommandparser;

import seedu.foodrem.commons.core.Messages;
import seedu.foodrem.logic.commands.tagcommands.DeleteTagCommand;
import seedu.foodrem.logic.parser.ArgumentMultimap;
import seedu.foodrem.logic.parser.ArgumentTokenizer;
import seedu.foodrem.logic.parser.CliSyntax;
import seedu.foodrem.logic.parser.Parser;
import seedu.foodrem.logic.parser.ParserUtil;
import seedu.foodrem.logic.parser.exceptions.ParseException;
import seedu.foodrem.model.tag.Tag;

/**
 * Parses input arguments and creates a new DeleteTagCommand object
 */
public class DeleteTagCommandParser implements Parser<DeleteTagCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteTagCommand
     * and returns an DeleteTagCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteTagCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_NAME);

        if (!ParserUtil.arePrefixesPresent(argMultimap, CliSyntax.PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteTagCommand.getUsage()));
        }

        String name = argMultimap.getValue(CliSyntax.PREFIX_NAME).get().trim();

        Tag tagToDelete = new Tag(name);

        return new DeleteTagCommand(tagToDelete);
    }
}
