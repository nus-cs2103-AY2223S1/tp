package seedu.boba.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.boba.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.boba.logic.commands.IncreaseCommand;
import seedu.boba.logic.parser.exceptions.ParseException;
import seedu.boba.model.customer.Email;
import seedu.boba.model.customer.Phone;
import seedu.boba.model.customer.Reward;

/**
 * Parses input arguments and creates a new IncreaseCommand object
 */
public class IncreaseCommandParser implements CommandParser<IncreaseCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the IncreaseCommand
     * and returns an IncreaseCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public IncreaseCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PHONE, PREFIX_EMAIL);
        Phone phoneIdentifier = null;
        Email emailIdentifier = null;
        Prefix firstPrefix = argMultimap.getFirstPrefix();
        String incrementReward;

        try {
            incrementReward = argMultimap.getPreamble();
            if (incrementReward.isEmpty() || Integer.parseInt(incrementReward) < 0) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, IncreaseCommand.MESSAGE_USAGE));
            }
            if (!(firstPrefix.equals(PREFIX_PHONE) || firstPrefix.equals(PREFIX_EMAIL))) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, IncreaseCommand.MESSAGE_USAGE));
            } else if (argMultimap.getPhoneIdentifier()) {
                if (argMultimap.getAllValues(PREFIX_PHONE).size() == 1
                        && argMultimap.getAllValues(PREFIX_EMAIL).size() == 0) {
                    phoneIdentifier = ParserUtil.parsePhone(argMultimap.getAllValues(PREFIX_PHONE).get(0));
                } else {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            IncreaseCommand.MESSAGE_USAGE));
                }
            } else if (argMultimap.getEmailIdentifier()) {
                if (argMultimap.getAllValues(PREFIX_EMAIL).size() == 1
                        && argMultimap.getAllValues(PREFIX_PHONE).size() == 0) {
                    emailIdentifier = ParserUtil.parseEmail(argMultimap.getAllValues(PREFIX_EMAIL).get(0));
                } else {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            IncreaseCommand.MESSAGE_USAGE));
                }
            }
        } catch (NumberFormatException ne) {
            throw new ParseException(Reward.MESSAGE_MAX_INTEGER);
        }

        return argMultimap.getPhoneIdentifier()
                ? new IncreaseCommand(phoneIdentifier, incrementReward)
                : new IncreaseCommand(emailIdentifier, incrementReward);
    }
}
