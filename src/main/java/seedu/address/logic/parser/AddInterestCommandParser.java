package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ParserUtil.parseIndex;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddInterestCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.interest.Interest;


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
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddInterestCommand.MESSAGE_USAGE));
        }

        String indexFromCommand = getIndexFromCommand(trimmedArgs);
        Set<Interest> interestList = getInterestsFromCommand(trimmedArgs);

        try {
            index = parseIndex(indexFromCommand);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddInterestCommand.MESSAGE_USAGE), pe);
        }

        if (interestList.isEmpty()) {
            throw new ParseException(MESSAGE_INTERESTS_EMPTY);
        }

        return new AddInterestCommand(index, interestList);
    }

    /**
     * Extracts out the index of the person specified in the user command.
     *
     * @param args The user command.
     * @return The index of the person in String.
     */
    private String getIndexFromCommand(String args) throws ParseException {
        String[] splittedArgs = args.split(" ");
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
        String[] splitArgs = args.split(" ");
        return Arrays.stream(splitArgs)
                .skip(1)
                .map(Interest::new)
                .collect(Collectors.toCollection(HashSet::new));
    }
}
