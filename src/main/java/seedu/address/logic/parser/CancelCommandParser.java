package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CancelCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * A class that encapsulates parsing of the input for cancel commands.
 */
public class CancelCommandParser implements Parser<CancelCommand> {

    /**
     * Reads the user input and generates the specified cancel command.
     * @param userInput Input string from user without the command word.
     * @return The specified cancel command to execute.
     * @throws ParseException If the input is not in the correct format.
     */
    @Override
    public CancelCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        String trimmedIndices = userInput.trim();
        String[] arrIndex = trimmedIndices.split(" ");
        if (arrIndex.length != 1 || arrIndex[0].isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CancelCommand.MESSAGE_USAGE));
        }
        Index apptIndex;
        try {
            apptIndex = ParserUtil.parseIndex(arrIndex[0]);
        } catch (ParseException e) {
            throw new ParseException(MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        return new CancelCommand(apptIndex);
    }
}

