package seedu.watson.logic.parser;

import static seedu.watson.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.watson.logic.commands.GradeCommand;
import seedu.watson.logic.parser.exceptions.ParseException;
import seedu.watson.model.student.subject.Assessment;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class GradeCommandParser implements Parser<GradeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public GradeCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        String[] parsedString = trimmedArgs.split("_");

        if (trimmedArgs.isEmpty() || parsedString.length != 5) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GradeCommand.MESSAGE_USAGE));
        } else if (!Assessment.getErrorMessage(args).equals("")) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, Assessment.getErrorMessage(args)));
        }
        String subject = args.split("_")[0].trim();
        return new GradeCommand(subject, args.trim());

    }
}
