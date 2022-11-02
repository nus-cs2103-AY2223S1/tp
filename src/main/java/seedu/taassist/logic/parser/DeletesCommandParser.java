package seedu.taassist.logic.parser;

import static seedu.taassist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_SESSION;

import java.util.Set;

import seedu.taassist.logic.commands.DeletesCommand;
import seedu.taassist.logic.parser.exceptions.ParseException;
import seedu.taassist.model.session.Session;

/**
 * Parses input arguments and creates a new DeletesCommand object.
 */
public class DeletesCommandParser implements Parser<DeletesCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeletesCommand
     * and returns an DeletesCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public DeletesCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_SESSION);
        if (!argMultimap.containsPrefixes(PREFIX_SESSION) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletesCommand.COMMAND_WORD,
                    DeletesCommand.MESSAGE_USAGE));
        }

        Set<Session> sessions = ParserUtil.parseSessions(argMultimap.getAllValuesIgnoreCase(PREFIX_SESSION));

        return new DeletesCommand(sessions);
    }
}
