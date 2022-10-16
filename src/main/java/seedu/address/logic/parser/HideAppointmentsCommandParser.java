package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REASON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRING_PERIOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;

import seedu.address.logic.commands.HideAppointmentsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.HiddenPredicateSingleton;
import seedu.address.model.person.HideAppointmentPredicate;

/**
 * Parses input arguments and creates a new FilterPatientCommand object
 */
public class HideAppointmentsCommandParser implements Parser<HideAppointmentsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterPatientCommand
     * and returns a FilterPatientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public HideAppointmentsCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_REASON, PREFIX_TAG,
                PREFIX_STATUS);
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, HideAppointmentsCommand.MESSAGE_USAGE));
        }

        String reason = argMultimap.getValue(PREFIX_REASON).orElse("");
        String tag = argMultimap.getValue(PREFIX_TAG).orElse("");
        String status = argMultimap.getValue(PREFIX_STATUS).orElse("");
        HiddenPredicateSingleton.combineWithApptPredicate(new HideAppointmentPredicate(HideAppointmentPredicate.hideBy.TAG, tag));
        HiddenPredicateSingleton.combineWithApptPredicate(new HideAppointmentPredicate(HideAppointmentPredicate.hideBy.KEYWORD, reason));
        HiddenPredicateSingleton.combineWithApptPredicate(new HideAppointmentPredicate(HideAppointmentPredicate.hideBy.IS_MARKED, status));
        return new HideAppointmentsCommand(HiddenPredicateSingleton.getCurrApptPredicate());

    }

}
