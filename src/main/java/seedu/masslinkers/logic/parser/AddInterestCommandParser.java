package seedu.masslinkers.logic.parser;

import static seedu.masslinkers.commons.core.Messages.MESSAGE_MISSING_ARGUMENTS;
import static seedu.masslinkers.logic.parser.ParserUtil.parseIndex;

import java.util.Set;
import java.util.regex.Pattern;

import seedu.masslinkers.commons.core.index.Index;
import seedu.masslinkers.logic.commands.AddInterestCommand;
import seedu.masslinkers.logic.parser.exceptions.ParseException;
import seedu.masslinkers.model.interest.Interest;

//@@author sltsheryl
/**
 * Parses input arguments and creates a new AddInterestCommand object
 */
public class AddInterestCommandParser implements Parser<AddInterestCommand> {

    protected static final String MESSAGE_INTERESTS_EMPTY = "Interests cannot be empty!";
    private static final Pattern INDEX_FORMAT = Pattern.compile("-?\\d+");
    private static final String MESSAGE_INDEX_EMPTY = "Index cannot be empty!";

    /**
     * Parses the given {@code String} of arguments in the context of the AddInterestCommandParser
     * and returns an AddInterestCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public AddInterestCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_MISSING_ARGUMENTS, AddInterestCommand.MESSAGE_USAGE));
        }
        String indexFromCommand = ParserUtil.getIndexFromCommand(trimmedArgs);
        Index index = parseIndex(indexFromCommand);
        Set<Interest> interestSet = InterestCommandParser.handleInvalidInterestCommandInput(trimmedArgs);
        return new AddInterestCommand(index, interestSet);
    }
}
