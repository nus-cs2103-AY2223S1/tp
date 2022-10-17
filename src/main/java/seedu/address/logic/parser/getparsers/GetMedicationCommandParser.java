package seedu.address.logic.parser.getparsers;

import seedu.address.logic.commands.getcommands.GetMedicationCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.MedicationContainsKeywordsPredicate;

import java.util.Arrays;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class GetMedicationCommandParser implements Parser<GetMedicationCommand> {

    @Override
    public GetMedicationCommand parse(String userInput) throws ParseException {
        String trimmedArgs = userInput.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetMedicationCommand.MESSAGE_USAGE));
        }

        String[] medications = trimmedArgs.split("\\s+");

        return new GetMedicationCommand(new MedicationContainsKeywordsPredicate(Arrays.asList(medications)));
    }

}
