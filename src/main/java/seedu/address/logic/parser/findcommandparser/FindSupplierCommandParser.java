package seedu.address.logic.parser.findcommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.function.Predicate;

import seedu.address.logic.commands.findcommands.FindCommand;
import seedu.address.logic.commands.findcommands.FindSupplierCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.PredicateParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Supplier;

/**
 * Parses input arguments and creates a new FindCommand object.
 */
public class FindSupplierCommandParser implements Parser<FindCommand> {
    public static final String PARSE_WORD = "find-s";
    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public FindSupplierCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        int totalPrefixesPresent = 0;
        if (trimmedArgs.contains(PREFIX_ADDRESS.getPrefix())) {
            totalPrefixesPresent += 1;
        }
        if (trimmedArgs.contains(PREFIX_EMAIL.getPrefix())) {
            totalPrefixesPresent += 1;
        }
        if (trimmedArgs.contains(PREFIX_INDEX.getPrefix())) {
            totalPrefixesPresent += 1;
        }
        if (trimmedArgs.contains(PREFIX_LOCATION.getPrefix())) {
            totalPrefixesPresent += 1;
        }
        if (trimmedArgs.contains(PREFIX_NAME.getPrefix())) {
            totalPrefixesPresent += 1;
        }
        if (trimmedArgs.contains(PREFIX_PHONE.getPrefix())) {
            totalPrefixesPresent += 1;
        }

        if (totalPrefixesPresent > 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, "More than 1 prefix present"));
        }

        Predicate<Supplier> supplierPredicate = PredicateParser.parseSupplier(trimmedArgs);

        return new FindSupplierCommand(supplierPredicate);
    }
}
