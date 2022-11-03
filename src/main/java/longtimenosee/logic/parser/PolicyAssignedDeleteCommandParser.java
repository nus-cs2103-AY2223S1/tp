package longtimenosee.logic.parser;

import static longtimenosee.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import longtimenosee.commons.core.index.Index;
import longtimenosee.logic.commands.PolicyDeleteAssignedCommand;
import longtimenosee.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new PolicyDeleteCommand object
 */
public class PolicyAssignedDeleteCommandParser implements Parser<PolicyDeleteAssignedCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PolicyDeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PolicyDeleteAssignedCommand parse(String args) throws ParseException {

        Index personIndex;
        Index policyIndex;

        String trimmedInput = args.trim();
        String[] indexes = (trimmedInput.split(" "));

        if (indexes.length != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    PolicyDeleteAssignedCommand.MESSAGE_USAGE));
        }

        try {
            personIndex = ParserUtil.parseIndex(indexes[0]);
            policyIndex = ParserUtil.parseIndex(indexes[1]);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    PolicyDeleteAssignedCommand.MESSAGE_USAGE), pe);
        }
        return new PolicyDeleteAssignedCommand(personIndex, policyIndex);
    }

}
