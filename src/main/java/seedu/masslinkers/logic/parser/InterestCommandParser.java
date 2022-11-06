package seedu.masslinkers.logic.parser;

import static seedu.masslinkers.commons.core.Messages.MESSAGE_INVALID_INTERESTS;
import static seedu.masslinkers.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static seedu.masslinkers.logic.parser.AddInterestCommandParser.MESSAGE_INTERESTS_EMPTY;
import static seedu.masslinkers.logic.parser.ParserUtil.parseIndex;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.masslinkers.commons.core.index.Index;
import seedu.masslinkers.logic.parser.exceptions.ParseException;
import seedu.masslinkers.model.interest.Interest;

/**
 * Parses input arguments and creates a new AddInterest and DeleteInterest object.
 */
public class InterestCommandParser {
    /**
     * Throws suitable errors when an invalid input is passed into an interest command.
     */
    public static Set<Interest> handleInvalidInterestCommandInput(String trimmedArgs) throws ParseException {
        String indexFromCommand = ParserUtil.getIndexFromCommand(trimmedArgs);
        Set<Interest> interestSet;

        try {
            interestSet = getInterestsFromCommand(trimmedArgs);
        } catch (IllegalArgumentException e) {
            throw new ParseException(MESSAGE_INVALID_INTERESTS, e);
        }

        try {
            Index index = parseIndex(indexFromCommand);
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        if (interestSet.isEmpty()) {
            throw new ParseException(MESSAGE_INTERESTS_EMPTY);
        }
        return interestSet;
    }

    /**
     * Extracts out the interests specified in the user command.
     *
     * @param args The user command.
     * @return A set of interests of type Interest.
     */
    private static Set<Interest> getInterestsFromCommand(String args) {
        String[] splitArgs = args.split("\\s+");
        return Arrays.stream(splitArgs)
                .skip(1)
                .map(Interest::new)
                .collect(Collectors.toCollection(HashSet::new));
    }
}


