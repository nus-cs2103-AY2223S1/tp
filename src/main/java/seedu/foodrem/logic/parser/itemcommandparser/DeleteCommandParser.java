package seedu.foodrem.logic.parser.itemcommandparser;

import static java.util.Objects.requireNonNull;

import seedu.foodrem.commons.core.index.Index;
import seedu.foodrem.commons.util.StringUtil;
import seedu.foodrem.logic.commands.itemcommands.DeleteCommand;
import seedu.foodrem.logic.parser.Parser;
import seedu.foodrem.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        Index index = StringUtil.validateAndGetIndexFromString(args, DeleteCommand.getUsage());
        return new DeleteCommand(index);
    }
}
