package seedu.address.logic.parser.findcommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.function.Predicate;

import seedu.address.logic.commands.findcommands.FindBuyerCommand;
import seedu.address.logic.commands.findcommands.FindCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.PredicateParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Buyer;

/**
 * Parses input arguments and creates a new FindBuyerCommand object.
 */
public class FindBuyerCommandParser implements Parser<FindCommand> {
    public static final String PARSE_WORD = "find-b";

    /**
     * Parses the given {@code String} of arguments in the context of the FindBuyerCommand
     * and returns a FindBuyerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public FindBuyerCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        Predicate<Buyer> buyerPredicate = PredicateParser.parseBuyer(trimmedArgs);

        return new FindBuyerCommand(buyerPredicate);
    }
}
