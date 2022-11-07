package seedu.classify.logic.parser;

import static seedu.classify.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.classify.logic.parser.CliSyntax.PREFIX_STUDENT_NAME;

import seedu.classify.commons.core.Messages;
import seedu.classify.logic.commands.DeleteCommand;
import seedu.classify.logic.parser.exceptions.ParseException;
import seedu.classify.model.student.Id;
import seedu.classify.model.student.IdPredicate;
import seedu.classify.model.student.Name;
import seedu.classify.model.student.NamePredicate;

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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_STUDENT_NAME, PREFIX_ID);

        boolean isNamePresent = argMultimap.getValue(PREFIX_STUDENT_NAME).isPresent();
        boolean isIdPresent = argMultimap.getValue(PREFIX_ID).isPresent();

        if (isNamePresent && isIdPresent) {
            throw new ParseException(String.format(Messages.MESSAGE_DELETE_COMMAND_DOUBLE_INPUT));
        } else if (isNamePresent) {
            try {
                Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_STUDENT_NAME).get());
                return new DeleteCommand(name, new NamePredicate(name));
            } catch (ParseException namePE) {
                throw new ParseException(
                        String.format(Messages.MESSAGE_INVALID_STUDENT_NAME, DeleteCommand.MESSAGE_USAGE), namePE);
            }
        } else if (isIdPresent) {
            try {
                Id id = ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get());
                return new DeleteCommand(id, new IdPredicate(id));
            } catch (ParseException idPE) {
                throw new ParseException(
                        String.format(Messages.MESSAGE_INVALID_STUDENT_ID, DeleteCommand.MESSAGE_USAGE), idPE);
            }
        } else {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteCommand.MESSAGE_USAGE));
        }
    }
}
