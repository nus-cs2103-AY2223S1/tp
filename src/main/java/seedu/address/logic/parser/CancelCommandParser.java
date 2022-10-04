package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CancelCommand;
import seedu.address.logic.parser.exceptions.ParseException;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX;


public class CancelCommandParser implements Parser<CancelCommand> {

    @Override
    public CancelCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        String trimmedIndices = userInput.trim();
        String[] patientAndApptIndex = trimmedIndices.split(" ");
        if (patientAndApptIndex.length != 2) {
            throw new ParseException(MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }
        Index patientIndex = ParserUtil.parseIndex(patientAndApptIndex[0]);
        Index apptIndex = ParserUtil.parseIndex(patientAndApptIndex[1]);

        return new CancelCommand(patientIndex, apptIndex);
    }
}

