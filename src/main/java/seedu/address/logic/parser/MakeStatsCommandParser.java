package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MakeStatsCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MakeStatsCommand object.
 */
public class MakeStatsCommandParser implements Parser<MakeStatsCommand> {

    private static final String TYPE_GENDER = "g";
    private static final String TYPE_AGE = "a";

    /**
     * Parses the given {@code String} of arguments in the context of the MakeStatsCommand
     * and returns an MakeStatsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public MakeStatsCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TYPE);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MakeStatsCommand.MESSAGE_USAGE), pe);
        }
        //Check if prefix and index are present
        if (!argMultimap.getValue(PREFIX_TYPE).isPresent()
            || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MakeStatsCommand.MESSAGE_USAGE));
        }
        //check which type of statistics is requested
        String statsType = argMultimap.getValue(PREFIX_TYPE).get().trim();
        if ((!TYPE_GENDER.equals(statsType)) && (!TYPE_AGE.equals(statsType))) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MakeStatsCommand.MESSAGE_USAGE));
        }
        return new MakeStatsCommand(index, TYPE_GENDER.equals(statsType));
    }
}
