package seedu.studmap.logic.parser;

import static seedu.studmap.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_CLASS;
import static seedu.studmap.logic.parser.ParserUtil.separatePreamble;

import seedu.studmap.commons.core.index.IndexListGenerator;
import seedu.studmap.commons.exceptions.IllegalValueException;
import seedu.studmap.logic.commands.EditStudentCommand;
import seedu.studmap.logic.commands.MarkCommand;
import seedu.studmap.logic.parser.exceptions.ParseException;
import seedu.studmap.model.student.Assignment;
import seedu.studmap.model.student.Attendance;

/**
 * Parses input arguments and creates a new MarkCommand object
 */
public class MarkCommandParser extends EditStudentCommandParser<MarkCommand.MarkCommandStudentEditor> {

    public static final String MESSAGE_INVALID_OPTION = "Option must either be 'present' or 'absent' for attendance and"
            + "\n'new' or 'received' or 'marked' for assignment";

    public static final String MESSAGE_INVALID_DOUBLE_MARK =
            "Only the attendance or assignment can be marked in a single command";

    @Override
    public Prefix[] getPrefixes() {
        return new Prefix[]{PREFIX_CLASS, PREFIX_ASSIGNMENT};
    }

    @Override
    public String getUsageMessage() {
        return MarkCommand.MESSAGE_USAGE;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the MarkCommand
     * and returns a MarkCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public EditStudentCommand<MarkCommand.MarkCommandStudentEditor> getIndexCommand(
            ArgumentMultimap argMultimap, IndexListGenerator indexListGenerator) throws ParseException {

        String[] preamble = argMultimap.getPreamble().split("\\s+");
        if (preamble.length != 2) {
            throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
        }

        MarkCommand.MarkCommandStudentEditor editor = null;

        if (argMultimap.getValue(PREFIX_CLASS).isPresent()
                && argMultimap.getValue(PREFIX_ASSIGNMENT).isPresent()) {
            throw new ParseException(MESSAGE_INVALID_DOUBLE_MARK);
        }

        if (argMultimap.getValue(PREFIX_CLASS).isPresent()) {
            String className = ParserUtil.parseClassName(argMultimap.getValue(PREFIX_CLASS).orElse(""));
            boolean attended = parseOption(preamble[1]);
            Attendance attendance = new Attendance(className, attended);
            editor = new MarkCommand.MarkCommandStudentEditor(attendance);
        } else if (argMultimap.getValue(PREFIX_ASSIGNMENT).isPresent()) {
            String assignmentName = ParserUtil.parseAssignmentName(
                    argMultimap.getValue(PREFIX_ASSIGNMENT).orElse(""));
            Assignment.Status markingStatus = parseStatus(preamble[1]);
            Assignment assignment = new Assignment(assignmentName, markingStatus);
            editor = new MarkCommand.MarkCommandStudentEditor(assignment);
        }

        assert editor != null : "Only the attendance or the assignment can be mark in the same command";

        return new MarkCommand(indexListGenerator, editor);

    }

    private boolean parseOption(String option) throws ParseException {
        if (option.equals("present")) {
            return true;
        } else if (option.equals("absent")) {
            return false;
        } else {
            throw new ParseException(MESSAGE_INVALID_OPTION);
        }
    }

    private Assignment.Status parseStatus(String status) throws ParseException {
        try {
            return Assignment.stringToStatus(status);
        } catch (IllegalValueException e) {
            throw new ParseException(e.getMessage());
        }
    }

    @Override
    public void validatePreamble(String preamble) throws ParseException {
        if (separatePreamble(preamble).length != 2) {
            throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }
}
