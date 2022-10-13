package seedu.rc4hdb.logic.commands.storagemodelcommand;

import java.nio.file.Path;

import seedu.rc4hdb.logic.commands.Command;

/**
 * Encapsulates a command that targets a file.
 */
public abstract class FileCommand implements Command {

    public static final String COMMAND_WORD = "file";

    protected Path filePath;

}
