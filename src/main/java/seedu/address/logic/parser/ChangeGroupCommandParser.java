package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ChangeGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.Path;

/**
 * Parses input arguments and creates a new ChangeGroupCommand object
 */
public class ChangeGroupCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the ChangeGroupCommand.
     *
     * @param args refer to the subsequent arguments after the initial command word.
     * @return a ChangeGroupCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ChangeGroupCommand parse(String args) throws ParseException {
        try {
            requireNonNull(args);
            Path path = ParserUtil.parsePath(args);
            return new ChangeGroupCommand(path);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            ChangeGroupCommand.MESSAGE_USAGE), pe);
        }
    }
}