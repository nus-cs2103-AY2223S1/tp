package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.GradeCommand.EMPTY_FIELD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;

import java.util.NoSuchElementException;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.GradeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Assignment;

/**
 * Parses input arguments and creates a new GradeCommand object
 */
public class GradeCommandParser implements Parser<GradeCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code GradeCommand}
     * and returns a {@code GradeCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GradeCommand parse(String args) throws ParseException {
        requireNonNull(args);
        if (args.trim().split(" ").length == 1 && !args.trim().equals("")) {
            throw new ParseException(EMPTY_FIELD);
        }
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ASSIGNMENT, PREFIX_GRADE);
        Index indexOfStudent;
        try {
            indexOfStudent = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    GradeCommand.MESSAGE_USAGE), ive);
        } catch (NoSuchElementException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    GradeCommand.MESSAGE_USAGE), e);
        }

        Index indexOfAssignment;
        try {
            indexOfAssignment = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_ASSIGNMENT).get());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    GradeCommand.MESSAGE_USAGE), ive);
        } catch (NoSuchElementException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    GradeCommand.MESSAGE_USAGE), e);
        }

        String grade = argMultimap.getValue(PREFIX_GRADE).orElse("");
        if (!Assignment.isValidInputGrade(grade)) {
            throw new ParseException(Assignment.GRADE_CONSTRAINTS);
        }

        return new GradeCommand(indexOfStudent, indexOfAssignment, grade);
    }

}
