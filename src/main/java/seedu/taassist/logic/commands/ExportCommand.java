package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.core.Messages.MESSAGE_NOT_IN_FOCUS_MODE;

import java.util.List;

import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.logic.commands.storagecommands.CommandResultWithStorageCommand;
import seedu.taassist.logic.commands.storagecommands.ExportCsvStorageCommand;
import seedu.taassist.logic.commands.storagecommands.StorageCommand;
import seedu.taassist.model.Model;
import seedu.taassist.model.moduleclass.ModuleClass;

/**
 * Retrieve module class information and creates a {@code StorageCommand} to
 * export the module class as a CSV file containing student's grade information.
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.isInFocusMode()) {
            throw new CommandException(String.format(MESSAGE_NOT_IN_FOCUS_MODE, COMMAND_WORD));
        }

        ModuleClass focusedClass = model.getFocusedClass();
        String fileName = focusedClass.getClassName();
        List<List<String>> fileData = model.exportModuleClassToStringList(focusedClass);

        StorageCommand storageCommand = new ExportCsvStorageCommand(fileName, fileData);

        return new CommandResultWithStorageCommand(storageCommand);
    }
}
