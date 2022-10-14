package coydir.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import coydir.commons.core.GuiSettings;
import coydir.commons.core.LogsCenter;
import coydir.logic.commands.Command;
import coydir.logic.commands.CommandResult;
import coydir.logic.commands.exceptions.CommandException;
import coydir.logic.parser.DatabaseParser;
import coydir.logic.parser.exceptions.ParseException;
import coydir.model.Model;
import coydir.model.ReadOnlyDatabase;
import coydir.model.person.Person;
import coydir.storage.Storage;
import javafx.collections.ObservableList;

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

    @Override
    public ReadOnlyDatabase getDatabase() {
        return model.getDatabase();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public Path getDatabaseFilePath() {
        return model.getDatabaseFilePath();
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
