package seedu.address.logic.parser;

import seedu.address.logic.commands.MatchCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input to construct a MatchCommand object.
 */
public class MatchCommandParser implements Parser<MatchCommand> {

    /**
     * Constructs a MatchCommandParser.
     */
    public MatchCommandParser() {
    }

    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param userInput The string input by the user
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public MatchCommand parse(String userInput) throws ParseException {
        return null;
    }
}
