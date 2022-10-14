package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AttendanceAddCommand;
import seedu.address.logic.commands.AttendanceMarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attendance.Attendance;

public class AttendanceMarkCommandParser implements Parser<AttendanceMarkCommand> {

    @Override
    public AttendanceMarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_LESSON, PREFIX_MARK);
        Index studentIndex;
        String lessonIndex;
        Attendance attendance;
        try {
            studentIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
            lessonIndex = ParserUtil.parseSize(argMultimap.getValue(PREFIX_LESSON).get());
            attendance = ParserUtil.parseAttendance(argMultimap.getValue(PREFIX_MARK));
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AttendanceMarkCommand.MESSAGE_USAGE), ive);
        }
        return new AttendanceMarkCommand(studentIndex, lessonIndex, attendance);
    }
}
