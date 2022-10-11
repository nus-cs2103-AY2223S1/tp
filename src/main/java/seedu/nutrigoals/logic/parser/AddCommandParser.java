package seedu.nutrigoals.logic.parser;

import static seedu.nutrigoals.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.nutrigoals.logic.parser.CliSyntax.PREFIX_CALORIE;
import static seedu.nutrigoals.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.nutrigoals.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.stream.Stream;

import seedu.nutrigoals.logic.commands.AddCommand;
import seedu.nutrigoals.logic.parser.exceptions.ParseException;
import seedu.nutrigoals.model.Calorie;
import seedu.nutrigoals.model.meal.Food;
import seedu.nutrigoals.model.meal.Name;
import seedu.nutrigoals.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_CALORIE, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_CALORIE, PREFIX_TAG)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Calorie calorie = ParserUtil.parseCalorie(argMultimap.getValue(PREFIX_CALORIE).get());
        Tag tag = ParserUtil.parseTag(argMultimap.getValue(PREFIX_TAG).get());

        Food food = new Food(name, calorie, tag);

        return new AddCommand(food);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
