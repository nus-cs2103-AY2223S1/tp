package seedu.studmap.logic.parser;

import static seedu.studmap.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_CLASS;

import seedu.studmap.commons.core.index.IndexListGenerator;
import seedu.studmap.logic.commands.EditStudentCommand;
import seedu.studmap.logic.commands.UnmarkCommand;
import seedu.studmap.logic.parser.exceptions.ParseException;
import seedu.studmap.model.student.Assignment;
import seedu.studmap.model.student.Attendance;

/**
 * Parses input arguments and creates a new UnmarkCommand object
 */
public class UnmarkCommandParser extends EditStudentCommandParser<UnmarkCommand.UnmarkCommandStudentEditor> {

    public static final String MESSAGE_INVALID_DOUBLE_MARK =
            "Only the attendance or assignment can be unmarked in a single command";

    /**
     * Parses the given {@code String} of arguments in the context of the UnmarkCommand
     * and returns an UnmarkCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public EditStudentCommand<UnmarkCommand.UnmarkCommandStudentEditor> getIndexCommand(
        ArgumentMultimap argMultimap, IndexListGenerator indexListGenerator) throws ParseException {

        String[] preamble = argMultimap.getPreamble().split("\\s+");
        if (preamble.length != 1) {
            throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
        }

        UnmarkCommand.UnmarkCommandStudentEditor editor = null;

        if (argMultimap.getValue(PREFIX_CLASS).isPresent()
                && argMultimap.getValue(PREFIX_ASSIGNMENT).isPresent()) {
            throw new ParseException(MESSAGE_INVALID_DOUBLE_MARK);
        }

        if (argMultimap.getValue(PREFIX_CLASS).isPresent()) {
            String className = ParserUtil.parseClassName(argMultimap.getValue(PREFIX_CLASS).orElse(""));
            Attendance attendance = new Attendance(className, true);
            editor = new UnmarkCommand.UnmarkCommandStudentEditor(attendance);
        } else if (argMultimap.getValue(PREFIX_ASSIGNMENT).isPresent()) {
            String assignmentName = ParserUtil.parseAssignmentName(
                    argMultimap.getValue(PREFIX_ASSIGNMENT).orElse(""));
            Assignment assignment = new Assignment(assignmentName, Assignment.Status.NEW);
            editor = new UnmarkCommand.UnmarkCommandStudentEditor(assignment);
        }

        return new UnmarkCommand(indexListGenerator, editor);
    }

    @Override
    public Prefix[] getPrefixes() {
        return new Prefix[]{PREFIX_CLASS, PREFIX_ASSIGNMENT};
    }

    @Override
    public String getUsageMessage() {
        return UnmarkCommand.MESSAGE_USAGE;
    }
}
