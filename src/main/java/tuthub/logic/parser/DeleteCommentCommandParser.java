package tuthub.logic.parser;

import static tuthub.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import tuthub.commons.core.index.Index;
import tuthub.logic.commands.DeleteCommentCommand;
import tuthub.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommentCommand object
 */
public class DeleteCommentCommandParser implements Parser<DeleteCommentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommendCommand
     * and returns a DeleteCommentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommentCommand parse(String args) throws ParseException {
        try {
            Index tutorIndex = ParserUtil.parseIndex(args.split(" ")[1]);
            Index commentIndex = ParserUtil.parseIndex(args.split(" ")[2]);
            return new DeleteCommentCommand(tutorIndex, commentIndex);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommentCommand.MESSAGE_USAGE), pe);
        }
    }

}
