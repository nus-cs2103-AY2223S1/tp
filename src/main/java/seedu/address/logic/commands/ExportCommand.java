package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_EXPORT_ERROR;

import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.storage.CsvAdaptedPerson;

/**
 * Exports data to a CSV file located at the specified path.
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Exports data to a CSV file located at the specified path.\n"
            + "Parameters: path to CSV file\n"
            + "Examples: \n"
            + COMMAND_WORD + " ./data.csv\n"
            + "- exports data to the file \"data.csv\""
            + " which is located in the same directory as the FinBook executable\n"
            + COMMAND_WORD + " ../data.csv\n"
            + "- exports data to the file \"data.csv\""
            + " which is located one level outside the directory of the FinBook executable";

    public static final String MESSAGE_EXPORT_DATA_SUCCESS = "Exported data: %1$s";

    private final Path targetPath;

    public ExportCommand(Path targetPath) {
        this.targetPath = targetPath;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<CsvAdaptedPerson> beans = model.getAddressBook().getPersonList().stream()
                .map(CsvAdaptedPerson::new).collect(Collectors.toList());
        try {
            Writer writer = new FileWriter(targetPath.toFile());
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
            beanToCsv.write(beans);
            writer.close();
        } catch (Exception e) {
            throw new CommandException(String.format(MESSAGE_EXPORT_ERROR, e.getMessage()));
        }
        return new CommandResult(String.format(MESSAGE_EXPORT_DATA_SUCCESS, targetPath.getFileName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExportCommand // instanceof handles nulls
                && targetPath.equals(((ExportCommand) other).targetPath)); // state check
    }
}
