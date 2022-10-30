package seedu.boba.logic;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import seedu.boba.commons.core.GuiSettings;
import seedu.boba.commons.core.LogsCenter;
import seedu.boba.logic.commands.Command;
import seedu.boba.logic.commands.CommandResult;
import seedu.boba.logic.commands.exceptions.CommandException;
import seedu.boba.logic.parser.BobaBotParser;
import seedu.boba.logic.parser.exceptions.ParseException;
import seedu.boba.model.BobaBotModel;
import seedu.boba.model.ReadOnlyBobaBot;
import seedu.boba.model.customer.Customer;
import seedu.boba.storage.Storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final BobaBotModel bobaBotModel;
    private final Storage storage;
    private final BobaBotParser bobaBotParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code BobaBotModel} and {@code Storage}.
     */
    public LogicManager(BobaBotModel bobaBotModel, Storage storage) {
        this.bobaBotModel = bobaBotModel;
        this.storage = storage;
        bobaBotParser = new BobaBotParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = bobaBotParser.parseCommand(commandText);
        commandResult = command.execute(bobaBotModel);
        if (!(commandResult.isRedo() || commandResult.isUndo())) {
            bobaBotModel.commitBobaBot();
        }

        try {
            storage.saveBobaBot(bobaBotModel.getBobaBot());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyBobaBot getBobaBot() {
        return bobaBotModel.getBobaBot();
    }

    @Override
    public ObservableList<Customer> getFilteredPersonList() {
        return bobaBotModel.getFilteredPersonList();
    }

    @Override
    public ObservableList<Image> getPromotionList() {
        return bobaBotModel.getPromotionList();
    }

    @Override
    public void parseAllPromotion(String filePath) {
        bobaBotModel.parseAllPromotion(filePath);
    }

    @Override
    public Path getBobaBotFilePath() {
        return bobaBotModel.getBobaBotFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return bobaBotModel.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        bobaBotModel.setGuiSettings(guiSettings);
    }
}
