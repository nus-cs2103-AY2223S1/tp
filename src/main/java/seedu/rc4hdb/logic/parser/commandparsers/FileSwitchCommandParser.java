package seedu.rc4hdb.logic.parser.commandparsers;

import java.nio.file.Paths;

import seedu.rc4hdb.logic.commands.storagemodelcommand.FileSwitchCommand;
import seedu.rc4hdb.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FileSwitchCommand object
 */
public class FileSwitchCommandParser implements CommandParser<FileSwitchCommand> {
    @Override
    public FileSwitchCommand parse(String args) throws ParseException {
        return new FileSwitchCommand(Paths.get("data", args.trim() + ".json"));
    }
}
