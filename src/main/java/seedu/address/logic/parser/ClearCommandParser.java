package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.consultation.AddConsultationCommand;
import seedu.address.logic.commands.consultation.DeleteConsultationCommand;
import seedu.address.logic.parser.consultation.ConsultationParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.consultation.ConsultationModule;
import seedu.address.model.consultation.ConsultationName;
import seedu.address.model.consultation.ConsultationVenue;


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

        ParserUtil.assertPrefixesPresent(argMultimap, PREFIX_FIELD);

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
