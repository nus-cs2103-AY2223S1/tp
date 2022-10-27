package seedu.boba.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import seedu.boba.commons.core.GuiSettings;
import seedu.boba.commons.core.LogsCenter;
import seedu.boba.logic.commands.Command;
import seedu.boba.logic.commands.CommandResult;
import seedu.boba.logic.commands.exceptions.CommandException;
import seedu.boba.logic.parser.BobaBotParser;
import seedu.boba.logic.parser.exceptions.ParseException;
import seedu.boba.model.Model;
import seedu.boba.model.ReadOnlyBobaBot;
import seedu.boba.model.customer.Customer;
import seedu.boba.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final BobaBotParser bobaBotParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        bobaBotParser = new BobaBotParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = bobaBotParser.parseCommand(commandText);
        commandResult = command.execute(model);
        if (!(commandResult.isRedo() || commandResult.isUndo())) {
            model.commitBobaBot();
        }

        try {
            storage.saveBobaBot(model.getBobaBot());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyBobaBot getBobaBot() {
        return model.getBobaBot();
    }

    @Override
    public ObservableList<Customer> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public ObservableList<Image> getPromotionList() {
        return model.getPromotionList();
    }

    @Override
    public void parseAllPromotion(String filePath) throws IOException {
        model.parseAllPromotion(filePath);
    }

    @Override
    public Path getBobaBotFilePath() {
        return model.getBobaBotFilePath();
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
