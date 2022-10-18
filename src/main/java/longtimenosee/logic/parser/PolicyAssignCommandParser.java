
package longtimenosee.logic.parser;

import static java.util.Objects.requireNonNull;
import static longtimenosee.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_END;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_PREMIUM;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_START;

import longtimenosee.commons.core.index.Index;
import longtimenosee.logic.commands.PolicyAssignCommand;
import longtimenosee.logic.parser.exceptions.ParseException;
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

        try {
            String[] test = (argMultimap.getPreamble()).split(" ");
            personIndex = ParserUtil.parseIndex(argMultimap.getPreamble().split(" ")[0]);
            policyIndex = ParserUtil.parseIndex(argMultimap.getPreamble().split(" ")[1]);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    PolicyAssignCommand.MESSAGE_USAGE), pe);
        }

        Premium premium = new Premium(argMultimap.getValue(PREFIX_PREMIUM).get());
        PolicyDate startDate = new PolicyDate(argMultimap.getValue(PREFIX_START).get());
        PolicyDate endDate = new PolicyDate(argMultimap.getValue(PREFIX_END).get());

        return new PolicyAssignCommand(personIndex, policyIndex, premium, startDate, endDate);
    }

}
