package eatwhere.foodguide.logic.parser;

import eatwhere.foodguide.logic.commands.Command;
import eatwhere.foodguide.logic.parser.exceptions.DisplayCommandHelpException;
import eatwhere.foodguide.logic.parser.exceptions.ParseException;

/**
 * Represents a Parser that is able to parse user input into a {@code Command} of type {@code T}.
 */
public interface Parser<T extends Command> {

    /**
     * Parses {@code userInput} into a command and returns it.
     * @throws ParseException if {@code userInput} does not conform the expected format
     * @throws DisplayCommandHelpException for {@code userInput} intended to display command help
     */
    T parse(String userInput) throws ParseException, DisplayCommandHelpException;
}
