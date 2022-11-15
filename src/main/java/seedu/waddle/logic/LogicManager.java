package seedu.waddle.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.waddle.commons.core.GuiSettings;
import seedu.waddle.commons.core.LogsCenter;
import seedu.waddle.logic.commands.Command;
import seedu.waddle.logic.commands.CommandResult;
import seedu.waddle.logic.commands.exceptions.CommandException;
import seedu.waddle.logic.parser.WaddleParser;
import seedu.waddle.logic.parser.exceptions.ParseException;
import seedu.waddle.model.Model;
import seedu.waddle.model.ReadOnlyWaddle;
import seedu.waddle.model.itinerary.Itinerary;
import seedu.waddle.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final WaddleParser waddleParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        waddleParser = new WaddleParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = waddleParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveWaddle(model.getWaddle());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyWaddle getWaddle() {
        return model.getWaddle();
    }

    @Override
    public ObservableList<Itinerary> getFilteredItineraryList() {
        return model.getFilteredItineraryList();
    }

    @Override
    public Path getWaddleFilePath() {
        return model.getWaddleFilePath();
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
