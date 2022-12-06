package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.FilterTransCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FilterTransCommand object.
 */
public class FilterTransCmdParser implements Parser<FilterTransCommand> {
    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param userInput
     * @throws ParseException if {@code userInput} does not conform the expected format.
     */
    @Override
    public FilterTransCommand parse(String userInput) throws ParseException {
        String trimmedArgs = userInput.trim();
        if (trimmedArgs.isEmpty() || !isValidInput(trimmedArgs)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterTransCommand.MESSAGE_USAGE));
        }

        return new FilterTransCommand(isBuy(trimmedArgs));
    }

    private boolean isValidInput(String input) {
        String inputToLowerCase = input.toLowerCase();
        return inputToLowerCase.equals("buy") || inputToLowerCase.equals("sell");
    }

    private boolean isBuy(String input) {
        String inputToLowerCase = input.toLowerCase();
        return inputToLowerCase.equals("buy");
    }
}
