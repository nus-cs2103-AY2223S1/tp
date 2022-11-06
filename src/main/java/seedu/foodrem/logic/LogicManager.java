package seedu.foodrem.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.foodrem.commons.core.GuiSettings;
import seedu.foodrem.commons.core.LogsCenter;
import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.logic.commands.exceptions.CommandException;
import seedu.foodrem.logic.parser.FoodRemParser;
import seedu.foodrem.logic.parser.exceptions.ParseException;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.ReadOnlyFoodRem;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final FoodRemParser foodRemParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        foodRemParser = new FoodRemParser();
    }

    /**
     * @inheritDoc
     */
    @Override
    public CommandResult<?> execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult<?> commandResult;
        Command command = foodRemParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveFoodRem(model.getFoodRem());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReadOnlyFoodRem getFoodRem() {
        return model.getFoodRem();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObservableList<Item> getCurrentList() {
        return model.getCurrentList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Path getFoodRemFilePath() {
        return model.getFoodRemFilePath();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
