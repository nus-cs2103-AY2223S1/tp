package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PAYMENT_STATUS;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindBillCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.bill.PaymentStatus;
import seedu.address.model.patient.Name;

/**
 * Parses input arguments and creates a new FindAppointmentCommand object
 */
public class FindBillCommandParser implements Parser<FindBillCommand> {
    private Predicate<Name> namePredicate;
    private Predicate<PaymentStatus> paymentStatusPredicate;

    /**
     * Parses the given {@code String} of arguments in the context of the FindBillCommand
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindBillCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PAYMENT_STATUS);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PAYMENT_STATUS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindBillCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String trimmedArgs = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()).toString().trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindBillCommand.MESSAGE_USAGE));
            }

            String[] nameKeywords = trimmedArgs.split("\\s+");

            String predicateName = nameKeywords[0];
            for (int i = 1; i < nameKeywords.length; i++) {
                predicateName += " " + nameKeywords[i];
            }

            final String finalPredicateName = predicateName;

            this.namePredicate = (name -> name.fullName.toLowerCase()
                    .contains(finalPredicateName.toLowerCase()));
        }

        Optional<Predicate<Name>> finalNamePredicate = Optional.ofNullable(this.namePredicate);

        if (argMultimap.getValue(PREFIX_PAYMENT_STATUS).isPresent()) {
            String trimmedArgs = ParserUtil.parsePaymentStatus(argMultimap
                    .getValue(PREFIX_PAYMENT_STATUS).get()).toString().trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindBillCommand.MESSAGE_USAGE));
            }

            this.paymentStatusPredicate = (paymentStatus -> paymentStatus.toString().equals(trimmedArgs));
        }

        Optional<Predicate<PaymentStatus>> finalPaymentStatusPredicate =
                Optional.ofNullable(this.paymentStatusPredicate);

        return new FindBillCommand(finalNamePredicate, finalPaymentStatusPredicate);
    }

    /**
     * Returns true if any of the prefixes contain non-empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
