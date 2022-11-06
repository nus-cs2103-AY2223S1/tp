package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_EXAM_INDEX;

import java.util.regex.Pattern;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteExamCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteExamCommand object
 */
public class DeleteExamCommandParser implements Parser<DeleteExamCommand> {

    private final Pattern pattern = Pattern.compile("(-|\\+)?\\d+(\\.\\d+)?");

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteExamCommand
     * and returns a DeleteExamCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteExamCommand parse(String args) throws ParseException {
        if (!pattern.matcher(args.strip()).matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteExamCommand.MESSAGE_USAGE));
        }

        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteExamCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_EXAM_INDEX);
        }
    }
}
