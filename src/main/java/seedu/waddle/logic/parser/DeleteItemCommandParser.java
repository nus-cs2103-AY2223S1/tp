package seedu.waddle.logic.parser;

import static seedu.waddle.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import java.util.stream.Stream;

import seedu.waddle.commons.core.index.Index;
import seedu.waddle.logic.commands.AddCommand;
import seedu.waddle.logic.commands.AddItemCommand;
import seedu.waddle.logic.commands.DeleteCommand;
import seedu.waddle.logic.commands.DeleteItemCommand;
import seedu.waddle.logic.parser.exceptions.ParseException;
import seedu.waddle.model.item.Item;

/**
 * Parses input arguments and creates a new AddItemCommand object
 */
public class DeleteItemCommandParser implements Parser<DeleteItemCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddItemCommand
     * and returns an AddItemCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteItemCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteItemCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteItemCommand.MESSAGE_USAGE), pe);
        }
    }

}

