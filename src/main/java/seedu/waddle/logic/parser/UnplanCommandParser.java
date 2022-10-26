package seedu.waddle.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.waddle.commons.core.index.MultiIndex;
import seedu.waddle.logic.commands.UnplanCommand;
import seedu.waddle.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UnplanCommand object
 */
public class UnplanCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the UnplanCommand
     * and returns an UnplanCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public UnplanCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        MultiIndex multiIndex = ParserUtil.parseMultiIndex(argMultimap.getPreamble());

        return new UnplanCommand(multiIndex);
    }
}
