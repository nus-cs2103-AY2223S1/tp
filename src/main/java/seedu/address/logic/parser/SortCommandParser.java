package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortCommand object.
 */
public class SortCommandParser implements Parser<SortCommand> {
    /**
     * Parses {@code userInput} into a command and returns it.
     * @throws ParseException if {@code userInput} does not conform the expected format.
     */
    @Override
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);
        Index index;
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);
        String [] indexAndLatest = argMultimap.getPreamble().split(" ", 2);

        if (!isValidInput(indexAndLatest)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        try {
            index = ParserUtil.parseIndex(indexAndLatest[0]);
        } catch (ParseException ive) {
            throw new ParseException(MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX, ive);
        }

        return new SortCommand(index, isLatest(indexAndLatest[1].trim()));
    }

    private boolean isValidInput(String[] input) {
        if (input.length != 2) {
            return false;
        }
        String inputToLowerCase = input[1].trim().toLowerCase();
        return inputToLowerCase.equals("latest") || inputToLowerCase.equals("oldest");
    }

    private boolean isLatest(String input) {
        String inputToLowerCase = input.toLowerCase();
        return inputToLowerCase.equals("latest");
    }
}
