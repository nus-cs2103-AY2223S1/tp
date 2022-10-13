package seedu.address.logic.parser.getparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.getcommands.GetFloorNumberCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.FloorNumber;
import seedu.address.model.person.FloorNumberContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new GetFloorNumberCommand object
 */
public class GetFloorNumberCommandParser implements Parser<GetFloorNumberCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the GetFloorNumberCommand
     * and returns a GetFloorNumberCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public GetFloorNumberCommand parse(String userInput) throws ParseException {
        String trimmedArgs = userInput.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetFloorNumberCommand.MESSAGE_USAGE));
        }
        List<FloorNumber> floorNumbers = new ArrayList<>();
        for (String arg : trimmedArgs.split("\\s+")) {
            try {
                floorNumbers.add(new FloorNumber(Integer.parseInt(arg)));
            } catch (IllegalArgumentException e) { // Catches both String input and negative number errors.
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetFloorNumberCommand.MESSAGE_USAGE));
            }
        }
        return new GetFloorNumberCommand(new FloorNumberContainsKeywordsPredicate(floorNumbers));
    }
}
