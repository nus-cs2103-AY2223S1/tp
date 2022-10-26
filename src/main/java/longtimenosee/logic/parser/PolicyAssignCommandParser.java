
package longtimenosee.logic.parser;

import static java.util.Objects.requireNonNull;
import static longtimenosee.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_END;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_PREMIUM;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_START;

import java.util.stream.Stream;

import longtimenosee.commons.core.index.Index;
import longtimenosee.logic.commands.PolicyAssignCommand;
import longtimenosee.logic.parser.exceptions.ParseException;
import longtimenosee.model.policy.AssignedPolicy;
import longtimenosee.model.policy.PolicyDate;
import longtimenosee.model.policy.Premium;

/**
 * Parses input arguments and creates a new PolicyAssignCommand object
 */
public class PolicyAssignCommandParser implements Parser<PolicyAssignCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PolicyAssignCommand
     * and returns a PolicyAssignCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PolicyAssignCommand parse(String args) throws ParseException {

        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PREMIUM, PREFIX_START, PREFIX_END);

        Index personIndex;
        Index policyIndex;
        PolicyDate startDate;
        PolicyDate endDate;
        Premium premium;

        if (!arePrefixesPresent(argMultimap, PREFIX_PREMIUM, PREFIX_START, PREFIX_END)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, PolicyAssignCommand.MESSAGE_USAGE));
        }
        String[] splitString = argMultimap.getPreamble().split(" ");
        String premiumString = argMultimap.getValue(PREFIX_PREMIUM).get();
        String startDateString = argMultimap.getValue(PREFIX_START).get();
        String endDateString = argMultimap.getValue(PREFIX_END).get();
        if (splitString.length == 2) {
            personIndex = ParserUtil.parseIndex(splitString[0]);
            policyIndex = ParserUtil.parseIndex(splitString[1]);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, PolicyAssignCommand.MESSAGE_USAGE));
        }
        if (Premium.isValidPremium(premiumString)) {
            premium = new Premium(argMultimap.getValue(PREFIX_PREMIUM).get());
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, Premium.MESSAGE_CONSTRAINTS));
        }
        if (PolicyDate.isValidDate(startDateString)
                && PolicyDate.isValidDate(endDateString)) {
            startDate = new PolicyDate(argMultimap.getValue(PREFIX_START).get());
            endDate = new PolicyDate(argMultimap.getValue(PREFIX_END).get());
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    PolicyDate.MESSAGE_FORMAT_CONSTRAINTS));
        }

        if (!AssignedPolicy.isChronological(startDate, endDate)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AssignedPolicy.MESSAGE_DATE_CONSTRAINTS));
        }

        return new PolicyAssignCommand(personIndex, policyIndex, premium, startDate, endDate);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
