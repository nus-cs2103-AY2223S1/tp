package tracko.logic.parser.order;

import static tracko.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tracko.logic.parser.CliSyntax.FLAG_DELIVERED;
import static tracko.logic.parser.CliSyntax.FLAG_PAID;

import tracko.commons.core.index.Index;
import tracko.logic.commands.order.MarkOrderCommand;
import tracko.logic.parser.ArgumentMultimap;
import tracko.logic.parser.ArgumentTokenizer;
import tracko.logic.parser.Parser;
import tracko.logic.parser.ParserUtil;
import tracko.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MarkOrderCommand object
 */
public class MarkOrderCommandParser implements Parser<MarkOrderCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MarkOrderCommand
     * and returns a MarkOrderCommand object for execution.
     * @param userInput user input string
     * @return a MarkOrderCommand object
     * @throws ParseException if the user input does not conform to the expected format
     */
    @Override
    public MarkOrderCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(userInput, FLAG_PAID, FLAG_DELIVERED);


        if (argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkOrderCommand.MESSAGE_USAGE));
        }

        Index index = ParserUtil.parseIndex(argumentMultimap.getPreamble());

        return new MarkOrderCommand(index, true, true);
    }
}
