package seedu.taassist.logic.parser;

import static seedu.taassist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_SESSION;

import java.time.LocalDate;
import java.util.Set;

import seedu.taassist.logic.commands.AddsCommand;
import seedu.taassist.logic.parser.exceptions.ParseException;
import seedu.taassist.model.session.Date;
import seedu.taassist.model.session.Session;

/**
 * Parses input arguments and creates a new AddsCommand object.
 */
public class AddsCommandParser implements Parser<AddsCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddsCommand
     * and returns a AddsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public AddsCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_SESSION, PREFIX_DATE);
        if (!argMultimap.containsPrefixes(PREFIX_SESSION) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddsCommand.COMMAND_WORD,
                    AddsCommand.MESSAGE_USAGE));
        }

        Date date;
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
        } else {
            date = new Date(LocalDate.now());
        }

        Set<Session> sessions = ParserUtil.parseSessions(argMultimap.getAllValuesIgnoreCase(PREFIX_SESSION), date);

        return new AddsCommand(sessions);
    }
}
