package seedu.masslinkers.logic.parser;

import static seedu.masslinkers.commons.core.Messages.MESSAGE_INVALID_INTERESTS;
import static seedu.masslinkers.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static seedu.masslinkers.commons.core.Messages.MESSAGE_MISSING_ARGUMENTS;
import static seedu.masslinkers.logic.parser.ParserUtil.parseIndex;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import seedu.masslinkers.commons.core.index.Index;
import seedu.masslinkers.logic.commands.AddInterestCommand;
import seedu.masslinkers.logic.parser.exceptions.ParseException;
import seedu.masslinkers.model.interest.Interest;

//@@author sltsheryl
/**
 * Parses input arguments and creates a new AddInterestCommand object
 */
public class AddInterestCommandParser implements Parser<AddInterestCommand> {

    private static final Pattern INDEX_FORMAT = Pattern.compile("-?\\d+");
    private static final String MESSAGE_INDEX_EMPTY = "Index cannot be empty!";
    private static final String MESSAGE_INTERESTS_EMPTY = "Interests cannot be empty!";

    /**
     * Parses the given {@code String} of arguments in the context of the AddInterestCommandParser
     * and returns an AddInterestCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public AddInterestCommand parse(String args) throws ParseException {
        Index index;
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_MISSING_ARGUMENTS, AddInterestCommand.MESSAGE_USAGE));
        }

        String indexFromCommand = getIndexFromCommand(trimmedArgs);
        Set<Interest> interestSet;

        try {
            interestSet = getInterestsFromCommand(trimmedArgs);
        } catch (IllegalArgumentException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_INTERESTS,
                    AddInterestCommand.MESSAGE_USAGE), e);
        }

        try {
            index = parseIndex(indexFromCommand);
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        if (interestSet.isEmpty()) {
            throw new ParseException(MESSAGE_INTERESTS_EMPTY);
        }

        return new AddInterestCommand(index, interestSet);
    }

    /**
     * Extracts out the index of the student specified in the user command.
     *
     * @param args The user command.
     * @return The index of the student in String.
     */
    private String getIndexFromCommand(String args) throws ParseException {
        String[] splittedArgs = args.split("\\s+");
        String index = splittedArgs[0];
        final Matcher matcher = INDEX_FORMAT.matcher(index.trim());
        if (!matcher.matches()) {
            throw new ParseException(MESSAGE_INDEX_EMPTY);
        }
        return index;
    }

    /**
     * Extracts out the interests specified in the user command.
     *
     * @param args The user command.
     * @return A set of interests of type Interest.
     */
    private Set<Interest> getInterestsFromCommand(String args) {
        String[] splitArgs = args.split("\\s+");
        return Arrays.stream(splitArgs)
                .skip(1)
                .map(Interest::new)
                .collect(Collectors.toCollection(HashSet::new));
    }
}
