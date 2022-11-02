package seedu.studmap.logic.parser;

import static seedu.studmap.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;
import static seedu.studmap.logic.parser.ParserUtil.separatePreamble;

import seedu.studmap.commons.core.index.IndexListGenerator;
import seedu.studmap.commons.exceptions.IllegalValueException;
import seedu.studmap.logic.commands.EditStudentCommand;
import seedu.studmap.logic.commands.GradeCommand;
import seedu.studmap.logic.parser.exceptions.ParseException;
import seedu.studmap.model.student.Assignment;

/**
 * Parses input arguments and creates a new MarkCommand object
 */
public class GradeCommandParser extends EditStudentCommandParser<GradeCommand.GradeCommandStudentEditor> {

    public static final String MESSAGE_INVALID_OPTION = "Option must either be 'new' or "
            + "'received' or 'marked' for assignment status";

    @Override
    public Prefix[] getPrefixes() {
        return new Prefix[]{PREFIX_ASSIGNMENT};
    }

    @Override
    public String getUsageMessage() {
        return GradeCommand.MESSAGE_USAGE;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the MarkCommand
     * and returns a MarkCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public EditStudentCommand<GradeCommand.GradeCommandStudentEditor> getIndexCommand(
            ArgumentMultimap argMultimap, IndexListGenerator indexListGenerator) throws ParseException {

        String[] preamble = separatePreamble(argMultimap.getPreamble());

        GradeCommand.GradeCommandStudentEditor editor = null;

        Assignment.Status markingStatus = parseStatus(preamble[1]);

        if(argMultimap.getValue(PREFIX_ASSIGNMENT).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, getUsageMessage()));
        }

        String assignmentName = ParserUtil.parseAssignmentName(
                argMultimap.getValue(PREFIX_ASSIGNMENT).orElse(""));
        Assignment assignment = new Assignment(assignmentName, markingStatus);
        editor = new GradeCommand.GradeCommandStudentEditor(assignment);

        return new GradeCommand(indexListGenerator, editor);

    }

    private Assignment.Status parseStatus(String status) throws ParseException {
        try {
            return Assignment.stringToStatus(status);
        } catch (IllegalValueException e) {
            throw new ParseException(MESSAGE_INVALID_OPTION);
        }
    }

    @Override
    public void validatePreamble(String preamble) throws ParseException {
        if (separatePreamble(preamble).length != 2) {
            throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }
}
