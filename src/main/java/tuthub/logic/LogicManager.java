package tuthub.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import tuthub.commons.core.GuiSettings;
import tuthub.commons.core.LogsCenter;
import tuthub.logic.commands.Command;
import tuthub.logic.commands.CommandResult;
import tuthub.logic.commands.exceptions.CommandException;
import tuthub.logic.parser.TuthubParser;
import tuthub.logic.parser.exceptions.ParseException;
import tuthub.model.Model;
import tuthub.model.ReadOnlyTuthub;
import tuthub.model.tutor.Tutor;
import tuthub.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final TuthubParser tuthubParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        tuthubParser = new TuthubParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = tuthubParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveTuthub(model.getTuthub());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyTuthub getTuthub() {
        return model.getTuthub();
    }

    @Override
    public ObservableList<Tutor> getFilteredTutorList() {
        return model.getFilteredTutorList();
    }

    @Override
    public Path getTuthubFilePath() {
        return model.getTuthubFilePath();
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
