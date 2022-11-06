package seedu.address.logic.parser.schedule;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_VENUE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_OF_SCHEDULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEKDAY;
import static seedu.address.logic.parser.ParserUtil.isValidTimeSlot;

import java.util.stream.Stream;

import seedu.address.logic.commands.schedule.AddScheduleCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.schedule.ClassType;
import seedu.address.model.module.schedule.Schedule;
import seedu.address.model.module.schedule.Venue;
import seedu.address.model.module.schedule.Weekdays;

/**
 * Parses input arguments and creates an AddScheduleCommand.
 */
public class AddScheduleCommandParser implements Parser<AddScheduleCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddScheduleCommand
     * and returns an AddScheduleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddScheduleCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MODULE_OF_SCHEDULE,
                PREFIX_WEEKDAY, PREFIX_CLASS_TIME, PREFIX_CLASS_CATEGORY, PREFIX_CLASS_GROUP, PREFIX_CLASS_VENUE);

        if (!arePrefixesPresent(argMultimap, PREFIX_MODULE_OF_SCHEDULE, PREFIX_WEEKDAY, PREFIX_CLASS_TIME,
                PREFIX_CLASS_CATEGORY, PREFIX_CLASS_GROUP, PREFIX_CLASS_VENUE) || !argMultimap.getPreamble()
                .isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddScheduleCommand.MESSAGE_USAGE));
        }
        String module = ParserUtil.parseModule(argMultimap.getValue(PREFIX_MODULE_OF_SCHEDULE).get());
        Weekdays weekday = ParserUtil.parseWeekday(argMultimap.getValue(PREFIX_WEEKDAY).get());
        String startTime = ParserUtil.parseClassStartTime(argMultimap.getValue(PREFIX_CLASS_TIME).get());
        String endTime = ParserUtil.parseClassEndTime(argMultimap.getValue(PREFIX_CLASS_TIME).get());
        ClassType classType = ParserUtil.parseClassType(argMultimap.getValue(PREFIX_CLASS_CATEGORY).get());
        Venue venue = ParserUtil.parseVenue(argMultimap.getValue(PREFIX_CLASS_VENUE).get());
        String classGroup = ParserUtil.parseClassGroup(argMultimap.getValue(PREFIX_CLASS_GROUP).get());

        if (isValidTimeSlot(startTime, endTime)) {
            Schedule newSchedule = new Schedule(module, venue, weekday, startTime, endTime, classType, classGroup);
            return new AddScheduleCommand(newSchedule);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddScheduleCommand.MESSAGE_USAGE));
        }


    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
