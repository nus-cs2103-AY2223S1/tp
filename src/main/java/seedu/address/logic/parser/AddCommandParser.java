package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.*;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    private final AddBuyerCommandParser addBuyerCommandParser;
    // private final AddDelivererCommandParser addDelivererCommandParser; //TODO Uncomment
    // private final AddSupplierCommandParser addSupplierCommandParser;

    public AddCommandParser() {
        addBuyerCommandParser = new AddBuyerCommandParser();
        //addDelivererCommandParser = new AddDelivererCommandParser(); //TODO Uncomment
        //addSupplierCommandParser = new AddSupplierCommandParser();
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PERSON_CATEGORY);

        if (!arePrefixesPresent(argMultimap, PREFIX_PERSON_CATEGORY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE)); //TODO Replace the MESSAGE_USAGE
        }

        PersonCategory personCategory =
                ParserUtil.parsePersonCategory(argMultimap.getValue(PREFIX_PERSON_CATEGORY).orElse(""));

        switch (personCategory) {
        case BUYER:
            return addBuyerCommandParser.parse(args);
        //TODO Uncomment
//        case DELIVERER:
//            return addDelivererCommandParser.parse(args);
//        case SUPPLIER:
//            return addSupplierCommandParser.parse(args);
        default:
            throw new ParseException(PersonCategory.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
