package seedu.address.logic.parser.getparsers;

import seedu.address.logic.commands.getcommands.GetWardNumberCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.WardNumber;
import seedu.address.model.person.WardNumberContainsKeywordsPredicate;

import java.util.ArrayList;
import java.util.List;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class GetWardNumberCommandParser implements Parser<GetWardNumberCommand> {

    @Override
    public GetWardNumberCommand parse(String userInput) throws ParseException {
        String trimmedArgs = userInput.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetWardNumberCommand.MESSAGE_USAGE));
        }

        List<WardNumber> wardNumbers = new ArrayList<>();
        for (String arg : trimmedArgs.split("\\s+")) {
            try {
                wardNumbers.add(new WardNumber(Integer.parseInt(arg)));
            } catch (IllegalArgumentException e) { // Catches both String input and negative number errors.
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetWardNumberCommand.MESSAGE_USAGE));
            }
        }
        return new GetWardNumberCommand(new WardNumberContainsKeywordsPredicate(wardNumbers));
    }
}
