package longtimenosee.logic.parser;

import longtimenosee.commons.core.index.Index;
import longtimenosee.logic.commands.PolicyAssignedListCommand;
import longtimenosee.logic.commands.PolicyDeleteCommand;
import longtimenosee.logic.parser.exceptions.ParseException;

import static longtimenosee.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and creates a new PolicyDeleteCommand object
 */
public class PolicyAssignedListCommandParser implements Parser<PolicyAssignedListCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PolicyDeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PolicyAssignedListCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new PolicyAssignedListCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, PolicyAssignedListCommand.MESSAGE_USAGE), pe);
        }
    }

}
