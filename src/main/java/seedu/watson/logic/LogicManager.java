package seedu.watson.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.watson.commons.core.GuiSettings;
import seedu.watson.commons.core.LogsCenter;
import seedu.watson.logic.commands.Command;
import seedu.watson.logic.commands.CommandResult;
import seedu.watson.logic.commands.exceptions.CommandException;
import seedu.watson.logic.parser.DatabaseParser;
import seedu.watson.logic.parser.exceptions.ParseException;
import seedu.watson.model.Model;
import seedu.watson.model.ReadOnlyDatabase;
import seedu.watson.model.student.Student;
import seedu.watson.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final DatabaseParser databaseParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        databaseParser = new DatabaseParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = databaseParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveDatabase(model.getDatabase());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    /**
     * Hacks
     * @return the model
     */
    public Model getModel() {
        return model;
    }

    /**
     * Hacks
     * @return the storage
     */
    public Storage getStorage() {
        return storage;
    }

    @Override
    public ReadOnlyDatabase getAddressBook() {
        return model.getDatabase();
    }

    @Override
    public ObservableList<Student> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getAddressBookFilePath();
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
