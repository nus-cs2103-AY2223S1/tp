package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindPatientCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.AddressContainsKeywordsPredicate;
import seedu.address.model.patient.EmailContainsKeywordsPredicate;
import seedu.address.model.patient.NameContainsKeywordsPredicatePatient;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.PhoneContainsNumbersPredicate;
import seedu.address.model.patient.RemarkContainsKeywordsPredicate;
import seedu.address.model.patient.TagContainsKeywordPredicate;

/**
 * Parses input arguments and creates a new FindPatientCommand object
 */
public class FindPatientCommandParser implements Parser<FindPatientCommand> {

    private Predicate<Patient> predicate = null;

    /**
     * Parses the given {@code String} of arguments in the context of the FindTagCommand
     * and returns a FilterNameCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindPatientCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE,
                        PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG, PREFIX_REMARK);

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String trimmedArgs = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()).toString().trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPatientCommand.MESSAGE_USAGE));
            }

            String[] nameKeywords = trimmedArgs.split("\\s+");

            String predicateName = nameKeywords[0];
            for (int i = 1; i < nameKeywords.length; i++) {
                predicateName += " " + nameKeywords[i];
            }

            this.predicate = new NameContainsKeywordsPredicatePatient(predicateName);
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            String trimmedArgs = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()).toString().trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPatientCommand.MESSAGE_USAGE));
            }

            String[] numbers = trimmedArgs.split("\\s+");

            this.predicate = new PhoneContainsNumbersPredicate(Arrays.asList(numbers));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            String trimmedArgs = argMultimap.getValue(PREFIX_EMAIL).get().trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPatientCommand.MESSAGE_USAGE));
            }

            String[] keywords = trimmedArgs.split("\\s+");

            String predicateEmail = keywords[0];
            for (int i = 1; i < keywords.length; i++) {
                predicateEmail += " " + keywords[i];
            }

            this.predicate = new EmailContainsKeywordsPredicate(predicateEmail);
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            String trimmedArgs = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()).toString().trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPatientCommand.MESSAGE_USAGE));
            }

            String[] keywords = trimmedArgs.split("\\s+");

            String predicateAddress = keywords[0];
            for (int i = 1; i < keywords.length; i++) {
                predicateAddress += " " + keywords[i];
            }

            this.predicate = new AddressContainsKeywordsPredicate(predicateAddress);
        }
        if (argMultimap.getValue(PREFIX_REMARK).isPresent()) {
            String trimmedArgs = ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).get()).toString().trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPatientCommand.MESSAGE_USAGE));
            }

            String[] keywords = trimmedArgs.split("\\s+");

            String predicateRemark = keywords[0];
            for (int i = 1; i < keywords.length; i++) {
                predicateRemark += " " + keywords[i];
            }

            this.predicate = new RemarkContainsKeywordsPredicate(predicateRemark);
        }

        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            String trimmedArgs = argMultimap.getValue(PREFIX_TAG).get().trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPatientCommand.MESSAGE_USAGE));
            }

            this.predicate = new TagContainsKeywordPredicate(trimmedArgs);
        }

        return new FindPatientCommand(this.predicate);

    }
}
