package eatwhere.foodguide.logic.parser;

import static eatwhere.foodguide.logic.parser.CliSyntax.PREFIX_CUISINE;
import static eatwhere.foodguide.logic.parser.CliSyntax.PREFIX_LOCATION;
import static eatwhere.foodguide.logic.parser.CliSyntax.PREFIX_NAME;
import static eatwhere.foodguide.logic.parser.CliSyntax.PREFIX_PRICE;
import static eatwhere.foodguide.logic.parser.CliSyntax.PREFIX_TAG;
import static eatwhere.foodguide.logic.parser.ParserUtil.arePrefixesPresent;
import static eatwhere.foodguide.logic.parser.ParserUtil.isDisplayHelp;

import java.util.Set;

import eatwhere.foodguide.commons.core.Messages;
import eatwhere.foodguide.logic.commands.AddCommand;
import eatwhere.foodguide.logic.parser.exceptions.DisplayCommandHelpException;
import eatwhere.foodguide.logic.parser.exceptions.ParseException;
import eatwhere.foodguide.model.eatery.Cuisine;
import eatwhere.foodguide.model.eatery.Eatery;
import eatwhere.foodguide.model.eatery.Location;
import eatwhere.foodguide.model.eatery.Name;
import eatwhere.foodguide.model.eatery.Price;
import eatwhere.foodguide.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     * @throws DisplayCommandHelpException if the user input is for displaying command help
     */
    public AddCommand parse(String args) throws ParseException, DisplayCommandHelpException {
        if (isDisplayHelp(args)) {
            throw new DisplayCommandHelpException(AddCommand.MESSAGE_USAGE);
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_CUISINE,
                        PREFIX_LOCATION, PREFIX_PRICE, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_LOCATION, PREFIX_PRICE, PREFIX_CUISINE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Cuisine cuisine = ParserUtil.parseCuisine(argMultimap.getValue(PREFIX_CUISINE).get());
        Price price = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PRICE).get());
        Location location = ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Eatery eatery = new Eatery(name, price, cuisine, location, tagList);

        return new AddCommand(eatery);
    }

}
