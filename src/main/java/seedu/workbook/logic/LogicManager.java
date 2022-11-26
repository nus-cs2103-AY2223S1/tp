package seedu.workbook.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.workbook.commons.core.GuiSettings;
import seedu.workbook.commons.core.LogsCenter;
import seedu.workbook.logic.commands.Command;
import seedu.workbook.logic.commands.CommandResult;
import seedu.workbook.logic.commands.exceptions.CommandException;
import seedu.workbook.logic.parser.WorkBookParser;
import seedu.workbook.logic.parser.exceptions.ParseException;
import seedu.workbook.model.Model;
import seedu.workbook.model.ReadOnlyWorkBook;
import seedu.workbook.model.internship.Internship;
import seedu.workbook.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final WorkBookParser workBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        workBookParser = new WorkBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = workBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveWorkBook(model.getWorkBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyWorkBook getWorkBook() {
        return model.getWorkBook();
    }

    @Override
    public ObservableList<Internship> getFilteredInternshipList() {
        return model.getFilteredInternshipList();
    }

    @Override
    public Path getWorkBookFilePath() {
        return model.getWorkBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
