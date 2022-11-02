package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteProductCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteProductCommand object
 */
public class DeleteProductCommandParser implements Parser<DeleteProductCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteProductCommand
     * and returns a DeleteProductCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteProductCommand parse(String args) throws ParseException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_INDEX);
        if (!ParserUtil.arePrefixesPresent(argumentMultimap, PREFIX_INDEX)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteProductCommand.MESSAGE_USAGE));
        }

        try {
            Index index = ParserUtil.parseIndex(argumentMultimap.getValue(PREFIX_INDEX).get());
            return new DeleteProductCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteProductCommand.MESSAGE_USAGE), pe);
        }
    }
}
