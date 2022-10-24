package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.function.Predicate;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.PersonCategory;
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
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        Predicate<Buyer> buyerPredicate = new Predicate<Buyer>() {
            @Override
            public boolean test(Buyer buyer) {
                return false;
            }
            public boolean equals(Object object) {
                return object instanceof Predicate;
            }
        };
        Predicate<Deliverer> delivererPredicate = new Predicate<Deliverer>() {
            @Override
            public boolean test(Deliverer deliverer) {
                return false;
            }
            public boolean equals(Object object) {
                return object instanceof Predicate;
            }
        };
        Predicate<Supplier> supplierPredicate = PredicateParser.parseSupplier(trimmedArgs);

        return new FindCommand(buyerPredicate, delivererPredicate, supplierPredicate,
                PersonCategory.getFromString("Supplier"));
    }
}
