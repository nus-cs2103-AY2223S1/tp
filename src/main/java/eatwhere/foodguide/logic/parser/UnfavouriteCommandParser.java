package eatwhere.foodguide.logic.parser;

import static eatwhere.foodguide.logic.parser.CliSyntax.PREFIX_HELP;
import static eatwhere.foodguide.logic.parser.ParserUtil.arePrefixesPresent;

import eatwhere.foodguide.commons.core.Messages;
import eatwhere.foodguide.commons.core.index.Index;
import eatwhere.foodguide.logic.commands.FavouriteCommand;
import eatwhere.foodguide.logic.commands.UnfavouriteCommand;
import eatwhere.foodguide.logic.parser.exceptions.DisplayCommandHelpException;
import eatwhere.foodguide.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FavouriteCommand object
 */
public class UnfavouriteCommandParser implements Parser<UnfavouriteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UnavouriteCommand
     * and returns a TagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     * @throws DisplayCommandHelpException if the user input is for displaying command help
     */
    public UnfavouriteCommand parse(String args) throws ParseException, DisplayCommandHelpException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_HELP);

        if (arePrefixesPresent(argMultimap, PREFIX_HELP)) {
            throw new DisplayCommandHelpException(FavouriteCommand.MESSAGE_USAGE);
        }

        try {
            Index index = ParserUtil.parseIndex(args);
            return new UnfavouriteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, UnfavouriteCommand.MESSAGE_USAGE), pe);
        }
    }

}
