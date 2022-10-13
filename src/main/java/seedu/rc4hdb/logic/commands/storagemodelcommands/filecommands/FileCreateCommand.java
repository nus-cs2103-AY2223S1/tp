package seedu.rc4hdb.logic.commands.storagemodelcommands.filecommands;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;

import seedu.rc4hdb.commons.util.FileUtil;
import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.logic.commands.misccommands.MiscCommand;
import seedu.rc4hdb.model.ResidentBook;
import seedu.rc4hdb.storage.JsonResidentBookStorage;

/**
 * Creates a new data file corresponding to the arguments provided.
 */
public class FileCreateCommand extends FileCommand implements MiscCommand {

    public static final String COMMAND_WORD = "create";

    public static final String MESSAGE_FILE_EXISTS = " already exists.";

    public static final String MESSAGE_SUCCESS = " successfully created.";

    public FileCreateCommand(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public CommandResult execute() throws CommandException {
        try {
            FileUtil.createFile(filePath);
            new JsonResidentBookStorage(filePath).saveResidentBook(new ResidentBook());
            return new CommandResult(filePath.getFileName() + MESSAGE_SUCCESS);
        } catch (FileAlreadyExistsException e) {
            throw new CommandException(filePath.getFileName() + MESSAGE_FILE_EXISTS);
        } catch (IOException e) {
            throw new CommandException(String.format(MESSAGE_FAILED, "creating"));
        }
    }

}
