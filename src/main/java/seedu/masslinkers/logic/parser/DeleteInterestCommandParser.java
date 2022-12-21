package seedu.masslinkers.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.masslinkers.commons.core.Messages.MESSAGE_MISSING_ARGUMENTS;
import static seedu.masslinkers.logic.parser.ParserUtil.parseIndex;

import java.util.Set;

import seedu.masslinkers.commons.core.index.Index;
import seedu.masslinkers.logic.commands.DeleteInterestCommand;
import seedu.masslinkers.logic.parser.exceptions.ParseException;
import seedu.masslinkers.model.interest.Interest;
//@@author ElijahS67
/**
 * Parses input arguments and creates a new DeleteInterestCommand object
 */
public class DeleteInterestCommandParser implements Parser<DeleteInterestCommand> {
    private static final String MESSAGE_INTERESTS_EMPTY = "Interests cannot be empty!";

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteInterestCommandParser
     * and returns an DeleteInterestCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public DeleteInterestCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_MISSING_ARGUMENTS, DeleteInterestCommand.MESSAGE_USAGE));
        }

        String indexFromCommand = ParserUtil.getIndexFromCommand(trimmedArgs);
        Index index = parseIndex(indexFromCommand);
        Set<Interest> interestSet = InterestCommandParser.handleInvalidInterestCommandInput(trimmedArgs);
        return new DeleteInterestCommand(index, interestSet);
    }
}
