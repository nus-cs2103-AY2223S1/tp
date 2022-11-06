package seedu.boba.logic.parser;

import static seedu.boba.commons.core.Messages.MESSAGE_BOTH_EMAIL_AND_PHONE;
import static seedu.boba.commons.core.Messages.MESSAGE_EMPTY_EMAIL_AND_PHONE;
import static seedu.boba.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.boba.commons.core.Messages.MESSAGE_NO_UNIQUE_PREFIX_IDENTIFIER;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.stream.Stream;

import seedu.boba.logic.commands.DeleteCommand;
import seedu.boba.logic.parser.exceptions.ParseException;
import seedu.boba.model.customer.Email;
import seedu.boba.model.customer.Phone;



/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements CommandParser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        try {
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(args, PREFIX_PHONE, PREFIX_EMAIL);
            DeleteCommand.DeletePersonDescriptor deletePersonDescriptor = new DeleteCommand.DeletePersonDescriptor();

            boolean isBothEmpty = !arePrefixesPresent(argMultimap, PREFIX_PHONE)
                    && !arePrefixesPresent(argMultimap, PREFIX_EMAIL);
            boolean isBothFilled = arePrefixesPresent(argMultimap, PREFIX_PHONE)
                    && arePrefixesPresent(argMultimap, PREFIX_EMAIL);
            boolean isPrefixUnique = arePrefixesPresent(argMultimap, PREFIX_PHONE)
                    ? argMultimap.isUniquePrefix(PREFIX_PHONE)
                    : argMultimap.isUniquePrefix(PREFIX_EMAIL);

            if (isBothEmpty) {
                throw new ParseException(String.format(MESSAGE_EMPTY_EMAIL_AND_PHONE, DeleteCommand.MESSAGE_USAGE));
            } else if (isBothFilled) {
                throw new ParseException(String.format(MESSAGE_BOTH_EMAIL_AND_PHONE, DeleteCommand.MESSAGE_USAGE));
            } else if (!argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
            } else if (!isPrefixUnique) {
                throw new ParseException(String.format(MESSAGE_NO_UNIQUE_PREFIX_IDENTIFIER,
                        DeleteCommand.MESSAGE_USAGE));
            } else if (arePrefixesPresent(argMultimap, PREFIX_PHONE)) {
                Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
                deletePersonDescriptor.setPhone(phone);
            } else if (arePrefixesPresent(argMultimap, PREFIX_EMAIL)) {
                Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
                deletePersonDescriptor.setEmail(email);
            }

            //Checks the assumption that either the Phone_Number or Email should be filled
            assert(deletePersonDescriptor.isAnyFilled());
            return new DeleteCommand(deletePersonDescriptor);

        } catch (ParseException pe) {
            throw new ParseException(pe.getMessage(), pe);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
