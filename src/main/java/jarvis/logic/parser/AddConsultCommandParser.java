package jarvis.logic.parser;

import static jarvis.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jarvis.logic.parser.CliSyntax.PREFIX_END_DATE;
import static jarvis.logic.parser.CliSyntax.PREFIX_END_TIME;
import static jarvis.logic.parser.CliSyntax.PREFIX_LESSON;
import static jarvis.logic.parser.CliSyntax.PREFIX_START_DATE;
import static jarvis.logic.parser.CliSyntax.PREFIX_START_TIME;
import static jarvis.logic.parser.CliSyntax.PREFIX_STUDENT_INDEX;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import jarvis.commons.core.index.Index;
import jarvis.logic.commands.AddConsultCommand;
import jarvis.logic.parser.exceptions.ParseException;
import jarvis.model.LessonDesc;
import jarvis.model.TimePeriod;

/**
 * Parses input arguments and creates a new AddConsultCommand object
 */
public class AddConsultCommandParser implements Parser<AddConsultCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddConsultCommand
     * and returns an AddConsultCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddConsultCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_LESSON, PREFIX_START_DATE,
                PREFIX_START_TIME, PREFIX_END_DATE, PREFIX_END_TIME, PREFIX_STUDENT_INDEX);

        if (!arePrefixesPresent(argMultimap, PREFIX_START_DATE, PREFIX_START_TIME, PREFIX_END_TIME,
                PREFIX_STUDENT_INDEX) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddConsultCommand.MESSAGE_USAGE));
        }

        Optional<String> desc = argMultimap.getValue(PREFIX_LESSON);
        LessonDesc consultDesc = desc.isPresent() ? ParserUtil.parseLessonDesc(desc.get()) : null;
        LocalDate startDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_START_DATE).get());
        LocalDate endDate = startDate;
        if (argMultimap.getValue(PREFIX_END_DATE).isPresent()) {
            endDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_END_DATE).get());
        }

        LocalTime startTime = ParserUtil.parseTime(argMultimap.getValue(PREFIX_START_TIME).get());
        LocalTime endTime = ParserUtil.parseTime(argMultimap.getValue(PREFIX_END_TIME).get());

        LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);
        LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);

        if (!startDateTime.isBefore(endDateTime)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddConsultCommand.MESSAGE_START_DATE_AFTER_END_DATE));
        }
        TimePeriod consultPeriod = new TimePeriod(startDateTime, endDateTime);

        Set<Index> studentIndexSet =
                ParserUtil.parseStudentIndexes(argMultimap.getAllValues(PREFIX_STUDENT_INDEX));

        return new AddConsultCommand(consultDesc, consultPeriod, studentIndexSet);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
