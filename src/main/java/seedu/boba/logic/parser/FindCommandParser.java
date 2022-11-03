package seedu.boba.logic.parser;

import static seedu.boba.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Arrays;
import java.util.stream.Stream;

import seedu.boba.logic.commands.FindCommand;
import seedu.boba.logic.parser.exceptions.ParseException;
import seedu.boba.model.customer.Email;
import seedu.boba.model.customer.MultiSearchPredicate;
import seedu.boba.model.customer.Phone;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        try {
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(args, PREFIX_PHONE, PREFIX_EMAIL);
            FindCommand.FindPersonDescriptor findPersonDescriptor = new FindCommand.FindPersonDescriptor();

            boolean isBothEmpty = !arePrefixesPresent(argMultimap, PREFIX_PHONE)
                    && !arePrefixesPresent(argMultimap, PREFIX_EMAIL);
            boolean isBothFilled = arePrefixesPresent(argMultimap, PREFIX_PHONE)
                    && arePrefixesPresent(argMultimap, PREFIX_EMAIL);
            if (isBothEmpty) {
                String trimmedArgs = args.trim();
                if (trimmedArgs.isEmpty()) {
                    throw new ParseException(
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
                }
                String[] nameKeywords = trimmedArgs.split("\\s+");
                return new FindCommand(new MultiSearchPredicate((Arrays.asList(nameKeywords))));
            } else if (isBothFilled || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            } else if (arePrefixesPresent(argMultimap, PREFIX_PHONE)
                    && argMultimap.getAllValues(PREFIX_PHONE).size() == 1) {
                Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
                findPersonDescriptor.setPhone(phone);
            } else if (arePrefixesPresent(argMultimap, PREFIX_EMAIL)
                    && argMultimap.getAllValues(PREFIX_EMAIL).size() == 1) {
                Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
                findPersonDescriptor.setEmail(email);
            } else {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }

            //Checks the assumption that either the Phone_Number or Email should be filled
            assert(findPersonDescriptor.isAnyFilled());
            return new FindCommand(findPersonDescriptor);

        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE), pe);
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
