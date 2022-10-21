package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.AddCommandWithPopup;
import seedu.address.logic.parser.exceptions.ParseException;

public class AddCommandWithPopupParser implements Parser<AddCommandWithPopup> {

    @Override
    public AddCommandWithPopup parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        userInput = userInput.trim().toUpperCase();
        switch (userInput) {
        case AddCommandWithPopup.ADD_BUYER:
            return new AddCommandWithPopup(AddCommandWithPopup.ADD_BUYER);
        case AddCommandWithPopup.ADD_SUPPLIER:
            return new AddCommandWithPopup(AddCommandWithPopup.ADD_SUPPLIER);
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommandWithPopup.MESSAGE_USAGE));
        }
    }

}
