package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.core.Messages.MESSAGE_NOT_IN_FOCUS_MODE;
import static seedu.taassist.commons.core.csv.CsvConfig.CSV_EXPORT_PATH;
import static seedu.taassist.commons.core.csv.CsvConfig.CSV_EXTENSION;
import static seedu.taassist.commons.core.csv.CsvConfig.CSV_LINE_BREAK;
import static seedu.taassist.commons.core.csv.CsvConfig.CSV_SEPARATOR;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import seedu.taassist.commons.util.FileUtil;
import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.model.Model;
import seedu.taassist.model.moduleclass.ModuleClass;

/**
 * Retrieve module class information and creates a {@code StorageCommand} to
 * export the module class as a CSV file containing student's grade information.
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";

    public static final String MESSAGE_SUCCESS = "Class %1$s successfully exported to %2$s.";
    public static final String MESSAGE_EXPORT_FAILED = "Failed to export %1$s.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.isInFocusMode()) {
            throw new CommandException(String.format(MESSAGE_NOT_IN_FOCUS_MODE, COMMAND_WORD));
        }

        ModuleClass focusedClass = model.getFocusedClass();
        String fileName = focusedClass.getClassName();
        List<List<String>> fileData = model.exportModuleClassToStringList(focusedClass);

        Path filePath = CSV_EXPORT_PATH.resolve(fileName + CSV_EXTENSION);
        String content = fileData.stream()
                .map(ls -> ls.stream().collect(Collectors.joining(CSV_SEPARATOR)))
                .collect(Collectors.joining(CSV_LINE_BREAK));

        String feedback;
        try {
            FileUtil.writeToFile(filePath, content);
            feedback = String.format(MESSAGE_SUCCESS, fileName, filePath);
        } catch (IOException e) {
            feedback = String.format(MESSAGE_EXPORT_FAILED, fileName);
        }

        return new CommandResult(feedback);
    }
}
