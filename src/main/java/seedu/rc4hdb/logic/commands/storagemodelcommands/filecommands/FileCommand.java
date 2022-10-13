package seedu.rc4hdb.logic.commands.storagemodelcommands.filecommands;

import java.nio.file.Path;

import seedu.rc4hdb.logic.commands.Command;

/**
 * Encapsulates a command that targets a file.
 */
public abstract class FileCommand implements Command {

    public static final String COMMAND_WORD = "file";

    public static final String MESSAGE_FAILED = "Something unexpected occurred while %s your file.";

    public static final String MESSAGE_INVALID_FILE_NAME = "File name must not have any whitespaces, fullstops, "
            + "forward and backslashes.";

    protected Path filePath;

    /**
     * Checks if the file path string provided is valid.
     * @param filePathString The file path string to be checked
     * @return True if filePathString does not contain whitespaces, fullstops, forward and backslashes.
     */
    public static boolean isValidPath(String filePathString) {
        return !(filePathString.contains(" ")
                || filePathString.contains(".")
                || filePathString.contains("/")
                || filePathString.contains("\\"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FileCommand // instanceof handles nulls
                && filePath.equals(((FileCommand) other).filePath));
    }

}
