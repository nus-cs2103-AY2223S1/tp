package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_STUDENT_ID;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_STUDENT_NAME;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Id;
import seedu.address.model.student.IdPredicate;
import seedu.address.model.student.Name;
import seedu.address.model.student.NamePredicate;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        if (args.startsWith(" id/")) {
            try {
                Id id = ParserUtil.parseId(args.substring(4));
                return new DeleteCommand(id, new IdPredicate(id));
            } catch (ParseException idPE) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_STUDENT_ID, DeleteCommand.MESSAGE_USAGE), idPE);
            }
        } else if (args.startsWith(" nm/")) {
            try {
                Name name = ParserUtil.parseName(args.substring(4));
                return new DeleteCommand(name, new NamePredicate(name));
            } catch (ParseException namePE) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_STUDENT_NAME, DeleteCommand.MESSAGE_USAGE), namePE);
            }
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }
    }
}
