package tracko.logic.parser.order;

import static tracko.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tracko.logic.parser.CliSyntax.FLAG_DELIVERED;
import static tracko.logic.parser.CliSyntax.FLAG_PAID;

import java.util.stream.Stream;

import tracko.commons.core.index.Index;
import tracko.logic.commands.order.MarkOrderCommand;
import tracko.logic.parser.*;
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
                FlagTokenizer.tokenize(userInput, FLAG_PAID, FLAG_DELIVERED);


        if (argumentMultimap.getPreamble().isEmpty()
                || !isAnyFlagPresent(argumentMultimap, FLAG_PAID, FLAG_DELIVERED)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkOrderCommand.MESSAGE_USAGE));
        }

        Index index = ParserUtil.parseIndex(argumentMultimap.getPreamble());
        boolean isPaid = argumentMultimap.getValue(FLAG_PAID).orElse("false").equals("true");
        boolean isDelivered = argumentMultimap.getValue(FLAG_DELIVERED).orElse("false").equals("true");

        return new MarkOrderCommand(index, isPaid, isDelivered);
    }

    /**
     * Returns true if at least one flag does not contain empty {@Optional} value in the given
     * {@code ArgumentMultimap}
     * @param argumentMultimap Hashmap which maps all the flags with corresponding arguments
     * @param flags flags to be checked for input present
     * @return true if at least one flag is present in the given {@code ArgumentMultimap}
     */
    private static boolean isAnyFlagPresent(ArgumentMultimap argumentMultimap, Flag... flags) {
        return Stream.of(flags).anyMatch(flag -> argumentMultimap.getValue(flag).isPresent());
    }
}
