package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.AddressContainsKeywordsPredicate;
import seedu.address.model.person.AlwaysTruePredicate;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.BirthdayContainsKeywordsPredicate;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.TagContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object.
 */
public class FindCommandParser implements Parser<FindCommand> {

    public static final String MESSAGE_NO_ALPHANUMERIC = "Parameters for "
            + "the find command must contain an alphanumeric character";

    public static final String MESSAGE_MULTIPLE_PREFIX = "Parameters for "
            + "the find command can only contain one prefix";

    private static final Prefix[] validPrefixArray = new Prefix[] {PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
        PREFIX_ADDRESS, PREFIX_TAG, PREFIX_BIRTHDAY};

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            return new FindCommand(new AlwaysTruePredicate());
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ADDRESS, PREFIX_BIRTHDAY, PREFIX_TAG);

        checkMultiplePrefix(argMultimap);

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String noPrefixArgs = argMultimap.getValue(PREFIX_NAME).get();
            checkEmptyField(noPrefixArgs);
            String[] nameKeywords = noPrefixArgs.split("\\s+");
            return new FindCommand(
                    new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        } else if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            String noPrefixArgs = argMultimap.getValue(PREFIX_PHONE).get();
            checkEmptyField(noPrefixArgs);
            String[] phoneKeywords = noPrefixArgs.split("\\s+");
            return new FindCommand(
                    new PhoneContainsKeywordsPredicate(Arrays.asList(phoneKeywords)));
        } else if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            String noPrefixArgs = argMultimap.getValue(PREFIX_ADDRESS).get();
            checkEmptyField(noPrefixArgs);
            checkAlphanumeric(noPrefixArgs);
            String[] addressKeywords = noPrefixArgs.split("\\s+");
            return new FindCommand(
                    new AddressContainsKeywordsPredicate(Arrays.asList(addressKeywords)));
        } else if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            String noPrefixArgs = argMultimap.getValue(PREFIX_EMAIL).get();
            checkEmptyField(noPrefixArgs);
            String[] emailKeywords = noPrefixArgs.split("\\s+");
            return new FindCommand(
                    new EmailContainsKeywordsPredicate(Arrays.asList(emailKeywords)));
        } else if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            String noPrefixArgs = argMultimap.getValue(PREFIX_TAG).get();
            checkEmptyField(noPrefixArgs);
            String[] tagKeywords = noPrefixArgs.split("\\s+");
            return new FindCommand(
                    new TagContainsKeywordsPredicate(Arrays.asList(tagKeywords)));
        } else if (argMultimap.getValue(PREFIX_BIRTHDAY).isPresent()) {
            String noPrefixArgs = argMultimap.getValue(PREFIX_BIRTHDAY).get();
            checkEmptyField(noPrefixArgs);
            String[] birthdayInput = noPrefixArgs.split("\\s+");
            String[] birthdayKeywords = parseBirthdayArray(birthdayInput);
            return new FindCommand(
                    new BirthdayContainsKeywordsPredicate(Arrays.asList(birthdayKeywords)));
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Checks the given {@code String} of arguments.
     * @throws ParseException if the user input is empty
     */
    public static void checkEmptyField(String args) throws ParseException {
        if (args.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Checks the given {@code String} of arguments.
     * @throws ParseException if the user input is empty
     */
    public static void checkAlphanumeric(String args) throws ParseException {
        String preppedArg = args.replaceAll("[^a-zA-Z0-9]", "").trim();
        if (preppedArg.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_NO_ALPHANUMERIC));
        }
    }

    /**
     * Checks the given {@code Array} of birthdays.
     * @throws ParseException if any birthday is in an invalid format
     */
    public static String[] parseBirthdayArray(String[] birthdayInput) throws ParseException {
        String[] birthdayKeywords = new String[birthdayInput.length];
        // try parsing birthdays
        for (int i = 0; i < birthdayInput.length; i++) {
            Birthday birthday = ParserUtil.parseBirthday(birthdayInput[i]);
            birthdayKeywords[i] = birthday.toString();
        }
        return birthdayKeywords;
    }

    /**
     * Checks the given {@code String} of arguments for multiple prefixes.
     * @throws ParseException if there are more than 1 valid prefixes present.
     */
    public static void checkMultiplePrefix(ArgumentMultimap argMultimap) throws ParseException {
        int prefixCount = 0;
        for (Prefix pfx : validPrefixArray) {
            if (argMultimap.getValue(pfx).isPresent()) {
                prefixCount++;
            }
        }
        if (prefixCount > 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_MULTIPLE_PREFIX));
        }
    }

}

