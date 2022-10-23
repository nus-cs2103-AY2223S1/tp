package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.storage.Storage;

/**
 * Exports the displayed list to a JSON file.
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";

    public static final String MESSAGE_SUCCESS = "Displayed list exported to %1$s";

    private final String exportDirectory = "data/export/";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss_SSS");
        LocalDateTime currDateTime = LocalDateTime.now();
        String currDateTimeString = currDateTime.format(formatter);
        String filePath = exportDirectory + currDateTimeString + ".json";

        ObservableList<Person> displayedList = model.getFilteredPersonList();

        try {
            Storage.exportDisplayedList(displayedList, filePath);
        } catch (IOException ioe) {
            throw new CommandException("An error has occurred during exporting. Please try again.");
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, filePath));
    }
}
