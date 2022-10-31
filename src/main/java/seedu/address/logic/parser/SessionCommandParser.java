package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SESSION;

import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SessionCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input command and creates a new SessionCommand object.
 */
public class SessionCommandParser implements Parser<SessionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SessionCommand
     * and returns a SessionCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public SessionCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_SESSION);
        Index index;

        try {
            index = ParserUtil.parseIndex(argumentMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SessionCommand.MESSAGE_USAGE), pe);
        }

        Optional<String> optSession = argumentMultimap.getValue(PREFIX_SESSION);

        if (optSession.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SessionCommand.MESSAGE_USAGE));
        }
        String session = optSession.get();
        return new SessionCommand(index, ParserUtil.parseSession(session));
    }
}
