package seedu.rc4hdb.logic.commands.storagemodelcommands.filecommands;

import java.nio.file.Path;

import seedu.rc4hdb.logic.commands.Command;

/**
 * Encapsulates a command that targets a file.
 */
public abstract class FileCommand implements Command {

    public static final String COMMAND_WORD = "file";

    public static final String MESSAGE_FAILED = "Something unexpected occurred while %s your file.";

    protected Path filePath;

}
