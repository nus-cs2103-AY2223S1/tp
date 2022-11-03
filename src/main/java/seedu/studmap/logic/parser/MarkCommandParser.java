package seedu.studmap.logic.parser;

import static seedu.studmap.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_CLASS;
import static seedu.studmap.logic.parser.ParserUtil.separatePreamble;

import seedu.studmap.commons.core.index.IndexListGenerator;
import seedu.studmap.logic.commands.EditStudentCommand;
import seedu.studmap.logic.commands.MarkCommand;
import seedu.studmap.logic.parser.exceptions.ParseException;
import seedu.studmap.model.student.Attendance;

/**
 * Parses input arguments and creates a new MarkCommand object
 */
public class MarkCommandParser extends EditStudentCommandParser<MarkCommand.MarkCommandStudentEditor> {

    public static final String MESSAGE_INVALID_OPTION = "Option must either be 'present' or 'absent' for attendance";

    @Override
    public Prefix[] getPrefixes() {
        return new Prefix[]{PREFIX_CLASS};
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

        String[] preamble = separatePreamble(argMultimap.getPreamble());

        MarkCommand.MarkCommandStudentEditor editor = null;

        boolean attended = parseOption(preamble[1]);

        if (argMultimap.getValue(PREFIX_CLASS).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, getUsageMessage()));
        }

        String className = ParserUtil.parseClassName(argMultimap.getValue(PREFIX_CLASS).orElse(""));
        Attendance attendance = new Attendance(className, attended);
        editor = new MarkCommand.MarkCommandStudentEditor(attendance);

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

    @Override
    public void validatePreamble(String preamble) throws ParseException {
        if (separatePreamble(preamble).length != 2) {
            throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }
}
