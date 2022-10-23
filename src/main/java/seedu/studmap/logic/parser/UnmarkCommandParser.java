package seedu.studmap.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.studmap.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_CLASS;

import seedu.studmap.commons.core.index.Index;
import seedu.studmap.commons.exceptions.IllegalValueException;
import seedu.studmap.logic.commands.UnmarkCommand;
import seedu.studmap.logic.parser.exceptions.ParseException;
import seedu.studmap.model.student.Assignment;
import seedu.studmap.model.student.Attendance;

/**
 * Parses input arguments and creates a new UnmarkCommand object
 */
public class UnmarkCommandParser implements Parser<UnmarkCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UnmarkCommand
     * and returns an UnmarkCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnmarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_CLASS, PREFIX_ASSIGNMENT);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UnmarkCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_CLASS).isPresent()) {
            String className = ParserUtil.parseClassName(argMultimap.getValue(PREFIX_CLASS).get());
            return new UnmarkCommand(index, new Attendance(className, true));
        }
        if (argMultimap.getValue(PREFIX_ASSIGNMENT).isPresent()) {
            String assignmentName = ParserUtil.parseAssignmentName(argMultimap.getValue(PREFIX_ASSIGNMENT).get());
            return new UnmarkCommand(index, new Assignment(assignmentName, Assignment.Status.NEW));
        }
        throw new ParseException(UnmarkCommand.MESSAGE_NO_EDIT);
    }
}
