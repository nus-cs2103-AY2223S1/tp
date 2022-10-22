package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADEPROGRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOMEWORK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON_PLAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SESSION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemoveCommand;
import seedu.address.logic.commands.RemoveCommand.RemovePersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;


public class RemoveCommandParser implements Parser<RemoveCommand> {

    public RemoveCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_HOMEWORK,
                        PREFIX_GRADEPROGRESS, PREFIX_ATTENDANCE, PREFIX_SESSION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveCommand.MESSAGE_USAGE), pe);
        }

        RemovePersonDescriptor removePersonDescriptor = new RemovePersonDescriptor();
        if (argMultimap.getValue(PREFIX_HOMEWORK).isPresent()) {
            String[] homeworkArgs = ParserUtil.parseIndexedRemove(argMultimap.getValue(PREFIX_HOMEWORK).get());
            removePersonDescriptor.setHomeworkIndex(ParserUtil.parseIndex(homeworkArgs[0]));
        }
        if (argMultimap.getValue(PREFIX_GRADEPROGRESS).isPresent()) {
            String[] gradeProgressArgs = ParserUtil.parseIndexedRemove(argMultimap.getValue(PREFIX_GRADEPROGRESS).get());
            removePersonDescriptor.setGradeProgressIndex(ParserUtil.parseIndex(gradeProgressArgs[0]));
        }
        if (argMultimap.getValue(PREFIX_ATTENDANCE).isPresent()) {
            String[] attendanceArgs = ParserUtil.parseIndexedRemove(argMultimap.getValue(PREFIX_ATTENDANCE).get());
            removePersonDescriptor.setAttendanceIndex(ParserUtil.parseIndex(attendanceArgs[0]));
        }
        if (argMultimap.getValue(PREFIX_SESSION).isPresent()) {
            String[] sessionArgs = ParserUtil.parseIndexedRemove(argMultimap.getValue(PREFIX_SESSION).get());
            removePersonDescriptor.setSessionIndex(ParserUtil.parseIndex(sessionArgs[0]));
        }

        return new RemoveCommand(index, removePersonDescriptor);
    }
}
