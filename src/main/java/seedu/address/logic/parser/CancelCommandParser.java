package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.BookCommand;
import seedu.address.logic.commands.CancelCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;


public class CancelCommandParser implements Parser<CancelCommand> {

    @Override
    public CancelCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        String trimmedIndices = userInput.trim();
        String[] patientAndApptIndex = trimmedIndices.split(" ");
        Index patientIndex = ParserUtil.parseIndex(patientAndApptIndex[0]);
        Index apptIndex = ParserUtil.parseIndex(patientAndApptIndex[1]);

        return new CancelCommand(patientIndex, apptIndex);
    }
}

