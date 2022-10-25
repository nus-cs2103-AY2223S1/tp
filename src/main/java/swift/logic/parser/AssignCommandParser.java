package swift.logic.parser;

import static swift.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import swift.commons.core.index.Index;
import swift.logic.commands.AssignCommand;
import swift.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AssignCommand object
 */
public class AssignCommandParser implements Parser<AssignCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * AssignCommand
     * and returns a AssignCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AssignCommand parse(String args) throws ParseException {
        try {
            String[] indices = args.trim().split("\\s+");
            if (indices.length != 2) {
                throw new ParseException("");
            }

            Index contactIndex = ParserUtil.parseIndex(indices[0]);
            Index taskIndex = ParserUtil.parseIndex(indices[1]);

            return new AssignCommand(contactIndex, taskIndex);
        } catch (Exception e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignCommand.MESSAGE_USAGE), e);
        }
    }

}
