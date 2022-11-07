package seedu.boba.logic.parser;

import static seedu.boba.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_BIRTHDAY_MONTH;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_REWARD;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.boba.logic.commands.AddCommand;
import seedu.boba.logic.parser.exceptions.ParseException;
import seedu.boba.model.customer.BirthdayMonth;
import seedu.boba.model.customer.Customer;
import seedu.boba.model.customer.Email;
import seedu.boba.model.customer.Name;
import seedu.boba.model.customer.Phone;
import seedu.boba.model.customer.Reward;
import seedu.boba.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements CommandParser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                    PREFIX_BIRTHDAY_MONTH, PREFIX_REWARD, PREFIX_TAG);

        boolean isUniquePrefix = argMultimap.isUniquePrefix(PREFIX_NAME)
                                    && argMultimap.isUniquePrefix(PREFIX_PHONE)
                                    && argMultimap.isUniquePrefix(PREFIX_EMAIL)
                                    && argMultimap.isUniquePrefix(PREFIX_BIRTHDAY_MONTH)
                                    && argMultimap.isUniquePrefix(PREFIX_REWARD);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_REWARD, PREFIX_PHONE,
            PREFIX_BIRTHDAY_MONTH, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()
                || !isUniquePrefix) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }
        try {
            Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
            Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
            BirthdayMonth birthdayMonth = ParserUtil.parseBirthdayMonth(argMultimap
                    .getValue(PREFIX_BIRTHDAY_MONTH).get());
            Reward reward = ParserUtil.parseReward(argMultimap.getValue(PREFIX_REWARD).get());
            Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

            Customer customer = new Customer(name, phone, email, birthdayMonth, reward, tagList);
            return new AddCommand(customer);
        } catch (NumberFormatException e) {
            throw new ParseException(Reward.MESSAGE_MAX_INTEGER);
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
