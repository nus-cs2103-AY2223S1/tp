package seedu.taassist.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_SESSION;

import seedu.taassist.logic.commands.ScoresCommand;
import seedu.taassist.logic.parser.exceptions.ParseException;
import seedu.taassist.model.session.Session;

/**
 * Parses input arguments and creates a new ScoresCommand object.
 */
public class ScoresCommandParser implements Parser<ScoresCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ScoresCommand
     * and returns a ScoresCommand object for execution.
     *
     * @param args Command arguments provided by the user.
     * @throws ParseException If the user input does not conform the expected format.
     */
    public ScoresCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_SESSION);

        if (!argMultimap.containsPrefixes(PREFIX_SESSION) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ScoresCommand.COMMAND_WORD, ScoresCommand.MESSAGE_USAGE));
        }

        Session session;

        try {
            session = ParserUtil.parseSession(argMultimap.getValue(PREFIX_SESSION).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScoresCommand.COMMAND_WORD,
                    ScoresCommand.MESSAGE_USAGE));
        }

        return new ScoresCommand(session);
    }
}
