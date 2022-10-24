package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_FILE_PATH;

import java.nio.file.Path;

import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ExportCommand object
 */
public class ExportCommandParser implements Parser<ExportCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ExportCommand
     * and returns an ExportCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ExportCommand parse(String args) throws ParseException {
        try {
            Path path = ParserUtil.parseExportPath(args);
            return new ExportCommand(path);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_FILE_PATH, ExportCommand.MESSAGE_USAGE), pe);
        }
    }

}
