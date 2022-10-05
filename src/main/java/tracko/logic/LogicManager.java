package tracko.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import tracko.commons.core.GuiSettings;
import tracko.commons.core.LogsCenter;
import tracko.logic.commands.Command;
import tracko.logic.commands.CommandResult;
import tracko.logic.commands.exceptions.CommandException;
import tracko.logic.parser.TrackOParser;
import tracko.logic.parser.exceptions.ParseException;
import tracko.model.Model;
import tracko.model.ReadOnlyTrackO;
import tracko.model.order.Order;
import tracko.storage.Storage;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final TrackOParser trackOParser;

    private Command inProgressCommand;
    private boolean isAwaitingInput;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        inProgressCommand = null;
        isAwaitingInput = false;
        trackOParser = new TrackOParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command;

        if (inProgressCommand == null) {
            command = trackOParser.parseCommand(commandText);
        } else {
            command = trackOParser.parseStageTwo(commandText, inProgressCommand);
        }

        if (command.isAwaitingInput()) {
            inProgressCommand = command;
        } else {
            inProgressCommand = null;
        }

        commandResult = command.execute(model);

        try {
            storage.saveTrackO(model.getTrackO());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyTrackO getTrackO() {
        return model.getTrackO();
    }

    @Override
    public ObservableList<Order> getOrderList() { return model.getOrderList();}

    @Override
    public Path getOrdersFilePath() {
        return model.getOrdersFilePath();
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
