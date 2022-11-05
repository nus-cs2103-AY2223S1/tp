package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteModuleCommand;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.regex.Pattern;

import static seedu.address.commons.core.Messages.*;

/**
 * Parses input arguments and creates a new DeleteModuleCommand object
 */
public class DeleteModuleCommandParser implements Parser<DeleteModuleCommand> {

    private final Pattern pattern = Pattern.compile("(-|\\+)?\\d+(\\.\\d+)?");

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteModuleCommand
     * and returns a DeleteModuleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteModuleCommand parse(String args) throws ParseException {

        if (!pattern.matcher(args.strip()).matches()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteModuleCommand.MESSAGE_USAGE));
        }

        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteModuleCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_MODULE_INDEX);
        }
    }

}
