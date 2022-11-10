package seedu.address.logic.parser.findcommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.function.Predicate;

import seedu.address.logic.commands.findcommands.FindCommand;
import seedu.address.logic.commands.findcommands.FindSupplierCommand;
import seedu.address.logic.parser.PredicateParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Supplier;

/**
 * Parses input arguments and creates a {@code FindSupplierCommand}.
 */
public class FindSupplierCommandParser extends FindCommandParser {
    public static final String PARSE_WORD = "find-s";
    /**
     * Parses the given {@code String} of arguments in the context of the FindSupplierCommand
     * and returns a FindSupplierCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public FindSupplierCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (moreThanOnePrefix(trimmedArgs)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, "More than 1 prefix present"));
        }

        Predicate<Supplier> supplierPredicate = PredicateParser.parseSupplier(trimmedArgs);

        return new FindSupplierCommand(supplierPredicate);
    }
}
