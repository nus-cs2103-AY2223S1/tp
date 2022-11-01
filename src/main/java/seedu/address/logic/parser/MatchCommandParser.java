package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;

import seedu.address.commons.core.index.Index;
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
     * @throws ParseException if {@code userInput} does not conform the expected format.
     */
    @Override
    public MatchCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_INDEX);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_INDEX) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MatchCommand.MESSAGE_USAGE));
        }

        String indexStr = argMultimap.getValue(PREFIX_INDEX).orElse("");
        Index index = ParserUtil.parseIndex(indexStr);

        return new MatchCommand(index);
    }
}
