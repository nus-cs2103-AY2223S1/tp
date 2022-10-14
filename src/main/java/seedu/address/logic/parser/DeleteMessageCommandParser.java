package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteMessageCommand;
import seedu.address.logic.commands.GenerateMessageCommand;
import seedu.address.logic.commands.TagCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteMessageCommandParser extends MessageCommandGroupParser {
    public DeleteMessageCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteMessageCommand.MESSAGE_USAGE));
        }

        String[] argArray = trimmedArgs.split("\\s+");

        Index messageIndex;
        try {
            messageIndex = Index.fromOneBased(Integer.parseInt(argArray[0]));
        } catch (NumberFormatException e) {
            throw new ParseException(TagCommand.MESSAGE_USAGE);
        } catch (IndexOutOfBoundsException e) {
            throw new ParseException(Messages.MESSAGE_INVALID_MESSAGE_INDEX);
        }

        return new DeleteMessageCommand(messageIndex);
    }

}
