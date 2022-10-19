package jarvis.logic.parser;

import static jarvis.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jarvis.logic.parser.CliSyntax.PREFIX_END_DATE_TIME;
import static jarvis.logic.parser.CliSyntax.PREFIX_LESSON;
import static jarvis.logic.parser.CliSyntax.PREFIX_START_DATE_TIME;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import jarvis.logic.commands.AddStudioCommand;
import jarvis.logic.parser.exceptions.ParseException;
import jarvis.model.LessonDesc;
import jarvis.model.TimePeriod;

/**
 * Parses input arguments and creates a new AddStudioCommand object
 */
public class AddStudioCommandParser implements Parser<AddStudioCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddStudioCommand
     * and returns an AddStudioCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddStudioCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_LESSON,
                PREFIX_START_DATE_TIME, PREFIX_END_DATE_TIME);

        if (!arePrefixesPresent(argMultimap, PREFIX_LESSON, PREFIX_START_DATE_TIME, PREFIX_END_DATE_TIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddStudioCommand.MESSAGE_USAGE));
        }

        LessonDesc studioDesc = ParserUtil.parseLessonDesc(argMultimap.getValue(PREFIX_LESSON).get());
        LocalDateTime startDateTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_START_DATE_TIME).get());
        LocalDateTime endDateTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_END_DATE_TIME).get());
        TimePeriod studioPeriod = new TimePeriod(startDateTime, endDateTime);

        return new AddStudioCommand(studioDesc, studioPeriod);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
