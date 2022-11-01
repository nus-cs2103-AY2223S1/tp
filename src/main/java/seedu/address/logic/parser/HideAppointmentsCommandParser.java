package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_EMPTY_REASON;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_STATUS;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TAGS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REASON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;

import seedu.address.logic.commands.HideAppointmentsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.predicates.HideAppointmentPredicate;



/**
 * Parses input arguments and creates a new HideAppointmentsCommand.
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

        HideAppointmentPredicate.HideBy cond;
        List<String> val;
        if (argMultimap.getValue(PREFIX_REASON).isPresent()) {
            val = argMultimap.getAllValues(PREFIX_REASON);
            cond = HideAppointmentPredicate.HideBy.KEYWORD;
            if (val.stream().anyMatch(x -> x.equals(""))) {
                throw new ParseException(MESSAGE_EMPTY_REASON);
            }
        } else if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            val = argMultimap.getAllValues(PREFIX_TAG);
            cond = HideAppointmentPredicate.HideBy.TAG;
            if (!areValidTags(val)) {
                throw new ParseException(MESSAGE_INVALID_TAGS);
            }
        } else if (argMultimap.getValue(PREFIX_STATUS).isPresent()
                && isValidStatusInput(argMultimap.getValue(PREFIX_STATUS).orElse(""))) {
            val = argMultimap.getAllValues(PREFIX_STATUS);
            cond = HideAppointmentPredicate.HideBy.IS_MARKED;
            if (val.size() > 1) {
                throw new ParseException(MESSAGE_INVALID_STATUS);
            }
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, HideAppointmentsCommand.MESSAGE_USAGE));
        }
        return new HideAppointmentsCommand(new HideAppointmentPredicate(cond, val));
    }

    /**
     * Checks if input status is valid (marked/unmarked/m/um).
     * @param status The given input from the user after s/ prefix.
     * @return Whether the status given is valid.
     */
    public boolean isValidStatusInput(String status) {
        return status.equalsIgnoreCase("um") || status.equalsIgnoreCase("m")
                || status.equalsIgnoreCase("marked") || status.equalsIgnoreCase("unmarked");
    }

    /**
     * Checks if given tags match ear, nose or throat.
     * @param tags The values gotten from the user after the t/ prefix.
     * @return Whether tags are all valid.
     */
    public boolean areValidTags(List<String> tags) {
        for (String s: tags) {
            if (!s.equalsIgnoreCase("ear") && !s.equalsIgnoreCase("nose")
                    && !s.equalsIgnoreCase("throat")) {
                return false;
            }
        }
        return true;
    }
}
