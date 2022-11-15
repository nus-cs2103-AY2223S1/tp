package seedu.waddle.logic.parser;

import static seedu.waddle.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_ITEM_DURATION;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_PRIORITY;

import java.util.stream.Stream;

import seedu.waddle.logic.commands.AddItemCommand;
import seedu.waddle.logic.parser.exceptions.ParseException;
import seedu.waddle.model.item.Cost;
import seedu.waddle.model.item.Duration;
import seedu.waddle.model.item.Item;
import seedu.waddle.model.item.Priority;
import seedu.waddle.model.itinerary.Description;

/**
 * Parses input arguments and creates a new AddItemCommand object
 */
public class AddItemCommandParser implements Parser<AddItemCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddItemCommand
     * and returns an AddItemCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddItemCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_ITEM_DURATION,
                                           PREFIX_PRIORITY, PREFIX_COST);

        if (!arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION, PREFIX_ITEM_DURATION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                                   AddItemCommand.MESSAGE_USAGE));
        }

        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        Priority priority;

        if (arePrefixesPresent(argMultimap, PREFIX_PRIORITY)) {
            priority = ParserUtil.parsePriority(argMultimap.getValue(PREFIX_PRIORITY).get());
        } else {
            priority = ParserUtil.parsePriority("1");
        }

        Cost cost;
        if (arePrefixesPresent(argMultimap, PREFIX_COST)) {
            cost = ParserUtil.parseCost(argMultimap.getValue(PREFIX_COST).get());
        } else {
            cost = ParserUtil.parseCost("0");
        }

        Duration duration;
        if (arePrefixesPresent(argMultimap, PREFIX_ITEM_DURATION)) {
            duration = ParserUtil.parseDuration(argMultimap.getValue(PREFIX_ITEM_DURATION).get());
        } else {
            duration = ParserUtil.parseDuration("0");
        }

        Item item = new Item(description, priority, cost, duration);

        return new AddItemCommand(item);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
