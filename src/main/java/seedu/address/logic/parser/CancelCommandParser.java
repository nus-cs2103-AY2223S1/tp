package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX;

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
        if (arrIndex.length != 1) {
            throw new ParseException(MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }
        Index apptIndex = ParserUtil.parseIndex(arrIndex[0]);

        return new CancelCommand(apptIndex);
    }
}

