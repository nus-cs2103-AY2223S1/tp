package jarvis.logic.parser;

import static jarvis.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jarvis.logic.parser.CliSyntax.PREFIX_END_DATE_TIME;
import static jarvis.logic.parser.CliSyntax.PREFIX_LESSON;
import static jarvis.logic.parser.CliSyntax.PREFIX_START_DATE_TIME;
import static jarvis.logic.parser.CliSyntax.PREFIX_STUDENT_INDEX;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import jarvis.commons.core.index.Index;
import jarvis.logic.commands.AddMasteryCheckCommand;
import jarvis.logic.parser.exceptions.ParseException;
import jarvis.model.LessonDesc;
import jarvis.model.TimePeriod;

/**
 * Parses input arguments and creates a new AddMasteryCheckCommand object
 */
public class AddMasteryCheckCommandParser implements Parser<AddMasteryCheckCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddMasteryCheckCommand
     * and returns an AddMasteryCheckCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddMasteryCheckCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_LESSON, PREFIX_START_DATE_TIME,
                PREFIX_END_DATE_TIME, PREFIX_STUDENT_INDEX);

        if (!arePrefixesPresent(argMultimap, PREFIX_START_DATE_TIME, PREFIX_END_DATE_TIME,
                PREFIX_STUDENT_INDEX) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddMasteryCheckCommand.MESSAGE_USAGE));
        }

        Optional<String> desc = argMultimap.getValue(PREFIX_LESSON);
        LessonDesc masteryCheckDesc = desc.isPresent() ? ParserUtil.parseLessonDesc(desc.get()) : null;
        LocalDateTime startDateTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_START_DATE_TIME).get());
        LocalDateTime endDateTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_END_DATE_TIME).get());

        if (!startDateTime.isBefore(endDateTime)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddMasteryCheckCommand.MESSAGE_START_DATE_AFTER_END_DATE));
        }
        TimePeriod masteryCheckPeriod = new TimePeriod(startDateTime, endDateTime);

        Set<Index> studentIndexSet =
                ParserUtil.parseStudentIndexes(argMultimap.getAllValues(PREFIX_STUDENT_INDEX));

        return new AddMasteryCheckCommand(masteryCheckDesc, masteryCheckPeriod, studentIndexSet);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
