package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ParserUtil.parseIndex;

import java.util.Arrays;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddIntCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.interest.Interest;


/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddIntCommandParser implements Parser<AddIntCommand> {

    private static final Pattern INDEX_FORMAT = Pattern.compile("-?\\d+");
    private static final String MESSAGE_INDEX_EMPTY = "Index cannot be empty!";
    private static final String MESSAGE_INTERESTS_EMPTY = "Interests cannot be empty!";

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddIntCommand parse(String args) throws ParseException {
        Index index;
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddIntCommand.MESSAGE_USAGE));
        }

        String indexFromCommand = getIndexFromCommand(trimmedArgs);

        try {
            index = parseIndex(indexFromCommand);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddIntCommand.MESSAGE_USAGE), pe);
        }

        ObservableList<Interest> interestList = getInterestsFromCommand(trimmedArgs);

        if (interestList.isEmpty()) {
            throw new ParseException(MESSAGE_INTERESTS_EMPTY);
        }
        return new AddIntCommand(index, interestList);
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
     * @return An observable list of interests of type Interest.
     */
    private ObservableList<Interest> getInterestsFromCommand(String args) {
        String[] splitArgs = args.split(" ");
        Set<Interest> interests = Arrays.stream(splitArgs)
                .skip(1)
                .map(Interest::new)
                .collect(Collectors.toSet());
        ObservableList<Interest> interestList = FXCollections.observableArrayList();
        interestList.addAll(interests);
        return interestList;
    }
}
