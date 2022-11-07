package tuthub.logic.parser;

import static tuthub.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import tuthub.commons.core.index.Index;
import tuthub.logic.commands.DeleteCommentCommand;
import tuthub.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommentCommand object
 */
public class DeleteCommentCommandParser implements Parser<DeleteCommentCommand> {

    public static final int EXPECTED_LEN = 2;

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommendCommand
     * and returns a DeleteCommentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommentCommand parse(String args) throws ParseException {
        try {
            String trimmedArgs = args.trim();
            String[] strArr = trimmedArgs.split(" ");
            if (!(strArr.length == EXPECTED_LEN)) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommentCommand.MESSAGE_USAGE));
            }
            Index tutorIndex = ParserUtil.parseIndex(strArr[0]);
            Index commentIndex = ParserUtil.parseIndex(strArr[1]);
            return new DeleteCommentCommand(tutorIndex, commentIndex);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommentCommand.MESSAGE_USAGE), pe);
        }
    }

}
