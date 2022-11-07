package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindPatientCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.Address;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Phone;
import seedu.address.model.patient.Remark;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new FindPatientCommand object
 */
public class FindPatientCommandParser implements Parser<FindPatientCommand> {

    private Predicate<Name> namePredicate;
    private Predicate<Phone> phonePredicate;
    private Predicate<Email> emailPredicate;
    private Predicate<Address> addressPredicate;
    private Predicate<Remark> remarkPredicate;
    private Predicate<Set<Tag>> tagPredicate;

    /**
     * Parses the given {@code String} of arguments in the context of the FindPatientCommand
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindPatientCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE,
                        PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG, PREFIX_REMARK);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TAG,
                PREFIX_REMARK) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPatientCommand.MESSAGE_USAGE));
        }

        //Check for name prefix
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            createNamePredicate(argMultimap);
        }

        Optional<Predicate<Name>> finalNamePredicate = Optional.ofNullable(namePredicate);

        //Check for phone prefix
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            createPhonePredicate(argMultimap);
        }

        Optional<Predicate<Phone>> finalPhonePredicate = Optional.ofNullable(this.phonePredicate);

        //Check for email prefix
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            createEmailPredicate(argMultimap);
        }

        Optional<Predicate<Email>> finalEmailPredicate = Optional.ofNullable(this.emailPredicate);

        //Check for address prefix
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            createAddressPredicate(argMultimap);
        }

        Optional<Predicate<Address>> finalAddressPredicate = Optional.ofNullable(this.addressPredicate);

        //Check for remark prefix
        if (argMultimap.getValue(PREFIX_REMARK).isPresent()) {
            createRemarkPredicate(argMultimap);
        }

        Optional<Predicate<Remark>> finalRemarkPredicate = Optional.ofNullable(this.remarkPredicate);

        //Check for tag prefix
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            createTagPredicate(argMultimap);
        }

        Optional<Predicate<Set<Tag>>> finalTagPredicate = Optional.ofNullable(this.tagPredicate);

        return new FindPatientCommand(finalNamePredicate, finalPhonePredicate, finalEmailPredicate,
                finalAddressPredicate, finalTagPredicate, finalRemarkPredicate);

    }

    /**
     * Returns true if any of the prefixes contains non-empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns the predicate string to be used in the predicate.
     *
     * @param trimmedArgs The trimmed arguments.
     * @return The predicate string.
     */
    public String createPredicateString(String trimmedArgs) {
        String[] keywords = trimmedArgs.split("\\s+");

        String predicateString = keywords[0];
        for (int i = 1; i < keywords.length; i++) {
            predicateString += " " + keywords[i];
        }

        return predicateString;
    }

    /**
     * Checks if the prefix for a field is present more than once in one command.
     *
     * @param prefix The prefix to be checked.
     * @param argMultimap The argument multimap.
     * @throws ParseException If the prefix is present more than once.
     */
    public void checkNumberOfPrefixes(Prefix prefix, ArgumentMultimap argMultimap) throws ParseException {
        if (argMultimap.getAllValues(prefix).size() > 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindPatientCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Creates the name predicate.
     * @param argMultimap The argument multimap to get the input from.
     * @throws ParseException If the prefix is present more than once.
     */
    public void createNamePredicate(ArgumentMultimap argMultimap) throws ParseException {
        checkNumberOfPrefixes(PREFIX_NAME, argMultimap);

        String trimmedArgs = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()).toString().trim();

        final String finalPredicateString = createPredicateString(trimmedArgs);

        Predicate<Name> namePredicate = (name -> name.fullName.toLowerCase()
                .contains(finalPredicateString.toLowerCase()));

        this.namePredicate = namePredicate;
    }

    /**
     * Creates the phone predicate.
     * @param argMultimap The argument multimap to get the input from.
     * @throws ParseException If the prefix is present more than once.
     */
    public void createPhonePredicate(ArgumentMultimap argMultimap) throws ParseException {
        checkNumberOfPrefixes(PREFIX_PHONE, argMultimap);

        String trimmedArgs = argMultimap.getValue(PREFIX_PHONE).get().trim();

        if (!trimmedArgs.matches("[0-9]+")) {
            throw new ParseException("Input for finding by phone number must be a number consisting of one or"
                    + " more digits");
        }

        Predicate<Phone> phonePredicate = (phone -> phone.value.contains(trimmedArgs));

        this.phonePredicate = phonePredicate;
    }

    /**
     * Creates the email predicate.
     * @param argMultimap The argument multimap to get the input from.
     * @throws ParseException If the prefix is present more than once.
     */
    public void createEmailPredicate(ArgumentMultimap argMultimap) throws ParseException {
        checkNumberOfPrefixes(PREFIX_EMAIL, argMultimap);

        String trimmedArgs = argMultimap.getValue(PREFIX_EMAIL).get().trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException("Input for finding by email should not be empty");
        }

        final String finalPredicateString = createPredicateString(trimmedArgs);

        Predicate<Email> emailPredicate = (email -> email.value.toLowerCase()
                .contains(finalPredicateString.toLowerCase()));

        this.emailPredicate = emailPredicate;
    }

    /**
     * Creates the address predicate.
     * @param argMultimap The argument multimap to get the input from.
     * @throws ParseException If the prefix is present more than once.
     */
    public void createAddressPredicate(ArgumentMultimap argMultimap) throws ParseException {
        checkNumberOfPrefixes(PREFIX_ADDRESS, argMultimap);

        String trimmedArgs = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()).toString().trim();

        final String finalPredicateString = createPredicateString(trimmedArgs);

        Predicate<Address> addressPredicate = (address -> address.toString().toLowerCase()
                .contains(finalPredicateString.toLowerCase()));

        this.addressPredicate = addressPredicate;
    }

    /**
     * Creates the remark predicate.
     * @param argMultimap The argument multimap to get the input from.
     * @throws ParseException If the prefix is present more than once.
     */
    public void createRemarkPredicate(ArgumentMultimap argMultimap) throws ParseException {
        checkNumberOfPrefixes(PREFIX_REMARK, argMultimap);

        String trimmedArgs = ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).get()).toString().trim();

        final String finalPredicateString = createPredicateString(trimmedArgs);

        Predicate<Remark> remarkPredicate = (remark -> remark.toString().toLowerCase()
                .contains(finalPredicateString.toLowerCase()));

        this.remarkPredicate = remarkPredicate;
    }

    /**
     * Creates the tag predicate.
     * @param argMultimap The argument multimap to get the input from.
     * @throws ParseException If the prefix is present more than once.
     */
    public void createTagPredicate(ArgumentMultimap argMultimap) throws ParseException {
        Set<Tag> tagSet = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Predicate<Set<Tag>> tagPredicate = patientTags -> tagSet.stream().allMatch(inputTag -> patientTags
                .stream().anyMatch(patientTag -> patientTag.tagName.toLowerCase()
                        .contains(inputTag.tagName.toLowerCase())));

        this.tagPredicate = tagPredicate;
    }
}
