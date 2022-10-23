package jarvis.logic.parser;

import static jarvis.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jarvis.logic.parser.CliSyntax.PREFIX_END_DATE;
import static jarvis.logic.parser.CliSyntax.PREFIX_END_TIME;
import static jarvis.logic.parser.CliSyntax.PREFIX_LESSON;
import static jarvis.logic.parser.CliSyntax.PREFIX_NAME;
import static jarvis.logic.parser.CliSyntax.PREFIX_START_DATE;
import static jarvis.logic.parser.CliSyntax.PREFIX_START_TIME;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
                PREFIX_START_DATE, PREFIX_START_TIME, PREFIX_END_DATE, PREFIX_END_TIME);

        if (!arePrefixesPresent(argMultimap, PREFIX_LESSON, PREFIX_START_DATE, PREFIX_START_TIME, PREFIX_END_TIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddStudioCommand.MESSAGE_USAGE));
        }

        LessonDesc studioDesc = ParserUtil.parseLessonDesc(argMultimap.getValue(PREFIX_LESSON).get());
        LocalDate startDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_START_DATE).get());
        LocalDate endDate = startDate;
        if (argMultimap.getValue(PREFIX_END_DATE).isPresent()) {
            endDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_NAME).get());
        }

        LocalTime startTime = ParserUtil.parseTime(argMultimap.getValue(PREFIX_START_TIME).get());
        LocalTime endTime = ParserUtil.parseTime(argMultimap.getValue(PREFIX_END_TIME).get());

        LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);
        LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);

        if (!startDateTime.isBefore(endDateTime)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddStudioCommand.MESSAGE_START_DATE_AFTER_END_DATE));
        }
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
