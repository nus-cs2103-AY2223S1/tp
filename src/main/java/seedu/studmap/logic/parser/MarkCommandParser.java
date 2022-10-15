package seedu.studmap.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.studmap.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_CLASS;

import seedu.studmap.commons.core.index.Index;
import seedu.studmap.commons.exceptions.IllegalValueException;
import seedu.studmap.logic.commands.MarkCommand;
import seedu.studmap.logic.parser.exceptions.ParseException;
import seedu.studmap.model.student.Attendance;

/**
 * Parses input arguments and creates a new MarkCommand object
 */
public class MarkCommandParser implements Parser<MarkCommand> {

    public static final String MESSAGE_INVALID_OPTION = "Option must either be 'present' or 'absent'!";

    /**
     * Parses the given {@code String} of arguments in the context of the MarkCommand
     * and returns a MarkCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkCommand parse(String args) throws ParseException {

        ParseException parseException = new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MarkCommand.MESSAGE_USAGE));
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_CLASS);

        Index index;
        String[] preamble = argMultimap.getPreamble().split("\\s+");
        if (preamble.length != 2) {
            throw parseException;
        }

        try {
            index = ParserUtil.parseIndex(preamble[0]);
        } catch (IllegalValueException ive) {
            throw parseException;
        }

        boolean attended = parseOption(preamble[1]);
        String className = ParserUtil.parseClassName(argMultimap.getValue(PREFIX_CLASS).orElse(""));
        Attendance attendance = new Attendance(className, attended);

        return new MarkCommand(index, attendance);
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
}
