package tracko.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import tracko.commons.core.GuiSettings;
import tracko.commons.core.LogsCenter;
import tracko.logic.commands.Command;
import tracko.logic.commands.CommandResult;
import tracko.logic.commands.MultiLevelCommand;
import tracko.logic.commands.exceptions.CommandException;
import tracko.logic.parser.TrackOParser;
import tracko.logic.parser.exceptions.ParseException;
import tracko.model.Model;
import tracko.model.ReadOnlyTrackO;
import tracko.model.item.InventoryItem;
import tracko.model.order.Order;
import tracko.storage.Storage;


/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final TrackOParser trackOParser;

    private MultiLevelCommand inProgressCommand;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        inProgressCommand = null;
        trackOParser = new TrackOParser();
    }

    @Override
    public CommandResult execute(String userInput) throws CommandException, ParseException {
        logger.info("----------------[USER INPUT][" + userInput + "]");

        CommandResult commandResult;
        Command command;

        if (inProgressCommand != null) {
            command = trackOParser.parseAndUpdateCommand(userInput, inProgressCommand);
        } else {
            command = trackOParser.parseCommand(userInput);
        }

        if (command.isAwaitingInput()) {
            inProgressCommand = (MultiLevelCommand) command;
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
    public ObservableList<Order> getOrderList() {
        return model.getOrderList();
    }

    @Override
    public Path getTrackOFilePath() {
        return model.getTrackOFilePath();
    }

    @Override
    public ObservableList<Order> getFilteredOrderList() {
        return model.getFilteredOrderList();
    }

    @Override
    public ObservableList<Order> getSortedOrderList() {
        return model.getSortedOrderList();
    }

    @Override
    public ObservableList<InventoryItem> getFilteredItemList() {
        return model.getFilteredItemList();
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
