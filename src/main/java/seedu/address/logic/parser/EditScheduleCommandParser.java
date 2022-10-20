package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_VENUE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_OF_SCHEDULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEKDAY;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.schedule.EditScheduleCommand;
import seedu.address.logic.commands.schedule.EditScheduleCommand.EditScheduleDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.schedule.Schedule;

/**
 * Parses input arguments and creates an EditScheduleCommand.
 */
public class EditScheduleCommandParser implements Parser<EditScheduleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditScheduleCommand
     * and returns an EditScheduleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditScheduleCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MODULE_OF_SCHEDULE,
                PREFIX_WEEKDAY, PREFIX_CLASS_TIME, PREFIX_CLASS_CATEGORY, PREFIX_CLASS_VENUE);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditScheduleCommand.MESSAGE_USAGE),
                    pe);
        }
        EditScheduleDescriptor editScheduleDescriptor = new EditScheduleDescriptor();
        if (argMultimap.getValue(PREFIX_MODULE_OF_SCHEDULE).isPresent()) {
            editScheduleDescriptor.setModule(ParserUtil.parseModule(argMultimap.getValue(PREFIX_MODULE_OF_SCHEDULE)
                    .get()));
        }
        if (argMultimap.getValue(PREFIX_CLASS_TIME).isPresent()) {
            String startTime = ParserUtil.parseClassStartTime(argMultimap.getValue(PREFIX_CLASS_TIME).get());
            String endTime = ParserUtil.parseClassEndTime(argMultimap.getValue(PREFIX_CLASS_TIME).get());
            if (isValidTimeSlot(startTime, endTime)) {
                editScheduleDescriptor.setStartTime(startTime);
                editScheduleDescriptor.setEndTime(endTime);
            }
        }
        if (argMultimap.getValue(PREFIX_WEEKDAY).isPresent()) {
            editScheduleDescriptor.setWeekday(ParserUtil.parseWeekday(argMultimap.getValue(PREFIX_WEEKDAY).get()));
        }
        if (argMultimap.getValue(PREFIX_CLASS_CATEGORY).isPresent()) {
            editScheduleDescriptor.setClassType(ParserUtil.parseClassType(argMultimap.getValue(PREFIX_CLASS_CATEGORY)
                    .get()));
        }
        if (argMultimap.getValue(PREFIX_CLASS_VENUE).isPresent()) {
            editScheduleDescriptor.setVenue(ParserUtil.parseVenue(argMultimap.getValue(PREFIX_CLASS_VENUE).get()));
        }

        if (!editScheduleDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditScheduleCommand.MESSAGE_NOT_EDITED);
        }

        return new EditScheduleCommand(index, editScheduleDescriptor);
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
