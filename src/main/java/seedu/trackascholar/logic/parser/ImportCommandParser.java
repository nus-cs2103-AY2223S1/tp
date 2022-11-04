package seedu.trackascholar.logic.parser;

import static seedu.trackascholar.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.trackascholar.logic.commands.ImportCommand;
import seedu.trackascholar.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new ImportCommand object.
 */
public class ImportCommandParser implements Parser<ImportCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ImportCommand
     * and returns an ImportCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public ImportCommand parse(String args) throws ParseException {
        String trimmedArgs = args.toLowerCase().trim();
        switch (trimmedArgs) {

        case ImportCommand.REPLACE:
        case ImportCommand.KEEP:
            return new ImportCommand(trimmedArgs);

        default:
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE));
        }

    }

}
