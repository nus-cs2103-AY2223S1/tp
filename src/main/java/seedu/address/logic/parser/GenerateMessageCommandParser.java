package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.GenerateMessageCommand;
import seedu.address.logic.commands.TagCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new GenerateMessageCommand object
 */
public class GenerateMessageCommandParser implements Parser<GenerateMessageCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the GenerateMessageCommand
     * and returns a GenerateMessageCommand object for execution
     * @throws ParseException if the user input does not conform to the specified format
     */
    public GenerateMessageCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GenerateMessageCommand.MESSAGE_USAGE));
        }

        String[] argArray = trimmedArgs.split("\\s+");

        Index personIndex;
        try {
            personIndex = Index.fromOneBased(Integer.parseInt(argArray[0]));
        } catch (NumberFormatException e) {
            throw new ParseException(TagCommand.MESSAGE_USAGE);
        } catch (IndexOutOfBoundsException e) {
            throw new ParseException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Index messageIndex;
        try {
            messageIndex = Index.fromOneBased(Integer.parseInt(argArray[1]));
        } catch (NumberFormatException e) {
            throw new ParseException(TagCommand.MESSAGE_USAGE);
        } catch (IndexOutOfBoundsException e) {
            throw new ParseException(Messages.MESSAGE_INVALID_MESSAGE_INDEX);
        }

        return new GenerateMessageCommand(personIndex, messageIndex);
    }

}
