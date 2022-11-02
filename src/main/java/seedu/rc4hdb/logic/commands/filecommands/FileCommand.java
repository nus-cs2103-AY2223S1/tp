package seedu.rc4hdb.logic.commands.filecommands;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Set;

import seedu.rc4hdb.logic.commands.Command;

/**
 * Encapsulates a command that targets a file.
 */
public abstract class FileCommand implements Command {

    public static final String COMMAND_WORD = "file";

    public static final String MESSAGE_FAILED = "Something unexpected occurred while %s your file.";

    public static final String MESSAGE_INVALID_FILE_NAME = "File name must not have any whitespaces, fullstops, "
            + "forward and backslashes.";

    public static final String MESSAGE_TRYING_TO_EXECUTE_ON_CURRENT_FILE = "%s is the current working data file. "
            + "Try another file.";

    public static final String MESSAGE_FILE_ALREADY_EXISTS = "%s sub data folder already exists. "
            + "Delete it before trying again.";

    /**
     * Stores the set of restricted characters. Add to the set to add restricted characters.
     */
    public static final Collection<String> RESTRICTED_CHARACTERS = Set.of(
            " ", ".", "/", "\\"
    );

    protected final Path folderPath;

    protected final String folderName;

    /**
     * Base constructor for all file commands.
     * @param subDir the path of the file to be executed on.
     */
    public FileCommand(Path subDir) {
        this.folderPath = subDir;
        folderName = subDir.getFileName().toString();
    }

    /**
     * Checks if the file path string provided is valid.
     * @param filePathString The file path string to be checked
     * @return True if filePathString does not contain whitespaces, fullstops, forward and backslashes.
     */
    public static boolean isValidPath(String filePathString) {
        return !RESTRICTED_CHARACTERS.stream()
                .map(filePathString::contains)
                .reduce(false, (curr, next) -> curr || next);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FileCommand // instanceof handles nulls
                && folderPath.equals(((FileCommand) other).folderPath));
    }

}
