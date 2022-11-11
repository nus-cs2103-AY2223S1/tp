package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListStudentCommand;
import seedu.address.logic.commands.ListTuitionClassCommand;
import seedu.address.logic.commands.ListTutorCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns an ListCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        requireNonNull(args);
        if (args.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.FEEDBACK_MESSAGE));
        }
        AddCommand.Entity entity = ParserUtil.parseEntity(args);
        switch (entity) {
        case STUDENT:
            return new ListStudentCommand();
        case TUTOR:
            return new ListTutorCommand();
        case CLASS:
            return new ListTuitionClassCommand();
        default:
            assert false;
            return null;
        }
    }
}
