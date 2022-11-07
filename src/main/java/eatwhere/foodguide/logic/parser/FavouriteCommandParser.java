package eatwhere.foodguide.logic.parser;

import static eatwhere.foodguide.logic.parser.ParserUtil.isDisplayHelp;
import static eatwhere.foodguide.logic.parser.ParserUtil.parseTags;

import java.util.ArrayList;
import java.util.Set;

import eatwhere.foodguide.commons.core.Messages;
import eatwhere.foodguide.commons.core.index.Index;
import eatwhere.foodguide.logic.commands.FavouriteCommand;
import eatwhere.foodguide.logic.commands.TagCommand;
import eatwhere.foodguide.logic.parser.exceptions.DisplayCommandHelpException;
import eatwhere.foodguide.logic.parser.exceptions.ParseException;
import eatwhere.foodguide.model.tag.Tag;

/**
 * Parses input arguments and creates a new FavouriteCommand object
 */
public class FavouriteCommandParser implements Parser<TagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FavouriteCommand
     * and returns a TagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     * @throws DisplayCommandHelpException if the user input is for displaying command help
     */
    public TagCommand parse(String args) throws ParseException, DisplayCommandHelpException {
        if (isDisplayHelp(args)) {
            throw new DisplayCommandHelpException(FavouriteCommand.MESSAGE_USAGE);
        }

        try {
            Index index = ParserUtil.parseIndex(args);
            ArrayList<String> fav = new ArrayList<>();
            fav.add("<3");
            Set<Tag> favTag = parseTags(fav);
            return new TagCommand(index, favTag);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FavouriteCommand.MESSAGE_USAGE), pe);
        }
    }

}
