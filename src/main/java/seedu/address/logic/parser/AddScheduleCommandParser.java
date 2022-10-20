package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_VENUE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_OF_SCHEDULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEKDAY;

import java.util.stream.Stream;

import seedu.address.logic.commands.schedule.AddScheduleCommand;
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
                PREFIX_WEEKDAY, PREFIX_CLASS_TIME, PREFIX_CLASS_CATEGORY, PREFIX_CLASS_VENUE);

        if (!arePrefixesPresent(argMultimap, PREFIX_MODULE_OF_SCHEDULE, PREFIX_WEEKDAY, PREFIX_CLASS_TIME,
                PREFIX_CLASS_CATEGORY, PREFIX_CLASS_VENUE) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddScheduleCommand.MESSAGE_USAGE));
        }
        String module = ParserUtil.parseModule(argMultimap.getValue(PREFIX_MODULE_OF_SCHEDULE).get());
        Weekdays weekday = ParserUtil.parseWeekday(argMultimap.getValue(PREFIX_WEEKDAY).get());
        String startTime = ParserUtil.parseClassStartTime(argMultimap.getValue(PREFIX_CLASS_TIME).get());
        String endTime = ParserUtil.parseClassEndTime(argMultimap.getValue(PREFIX_CLASS_TIME).get());
        ClassType classType = ParserUtil.parseClassType(argMultimap.getValue(PREFIX_CLASS_CATEGORY).get());
        Venue venue = ParserUtil.parseVenue(argMultimap.getValue(PREFIX_CLASS_VENUE).get());

        if (isValidTimeSlot(startTime, endTime)) {
            Schedule newSchedule = new Schedule(module, venue, weekday, startTime, endTime, classType);
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

    private static boolean isValidTimeSlot(String startTime, String endTime) throws ParseException {
        try {
            int startHour = Integer.parseInt(startTime.split(":")[0]);
            int startMin = Integer.parseInt(startTime.split(":")[1]);
            int endHour = Integer.parseInt(endTime.split(":")[0]);
            int endMin = Integer.parseInt(endTime.split(":")[1]);

            if (startHour >= 24 || endHour >= 24 || startHour < 0 || endHour < 0) {
                throw new ParseException(Schedule.MESSAGE_TIMESLOT_CONSTRAINT);
            }
            if (startMin != 0 && startMin != 30) {
                throw new ParseException(Schedule.MESSAGE_TIMESLOT_CONSTRAINT);
            }
            if (endMin != 0 && endMin != 30) {
                throw new ParseException(Schedule.MESSAGE_TIMESLOT_CONSTRAINT);
            }
            if (startHour < 7) {
                throw new ParseException(Schedule.MESSAGE_CLASS_STARTINGTIME_CONSTRAINT);
            }
            if (endHour >= 22 && endMin > 0) {
                throw new ParseException(Schedule.MESSAGE_CLASS_ENDINGTIME_CONSTRAINT);
            }

            if ((startHour > endHour) || ((startHour == endHour) && (startMin >= endMin))) {
                throw new ParseException(Schedule.MESSAGE_CLASS_STARTING_ENDINGT_CONSTRAINT);
            }

            return true;


        } catch (Exception e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, e.getMessage()));
        }

    }
}
