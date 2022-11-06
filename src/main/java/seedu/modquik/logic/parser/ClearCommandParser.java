package seedu.modquik.logic.parser;

import static seedu.modquik.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_FIELD;

import java.util.Objects;

import seedu.modquik.logic.commands.ClearCommand;
import seedu.modquik.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class ClearCommandParser implements Parser<ClearCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteConsultationCommand
     * and returns a DeleteConsultationCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ClearCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_FIELD);

        ParserUtil.assertAllPrefixesPresent(argMultimap, ClearCommand.MESSAGE_USAGE,
                PREFIX_FIELD);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ClearCommand.MESSAGE_USAGE));
        }

        String field = argMultimap.getValue(PREFIX_FIELD).get();
        Objects.requireNonNull(field);
        String trimmedField = field.trim();
        if (!ClearCommand.isValidField(trimmedField)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearCommand.MESSAGE_USAGE));
        }

        return new ClearCommand(trimmedField);
    }
}
