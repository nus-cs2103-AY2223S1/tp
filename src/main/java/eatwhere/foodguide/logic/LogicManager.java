package eatwhere.foodguide.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import eatwhere.foodguide.commons.core.GuiSettings;
import eatwhere.foodguide.commons.core.LogsCenter;
import eatwhere.foodguide.logic.commands.Command;
import eatwhere.foodguide.logic.commands.CommandResult;
import eatwhere.foodguide.logic.commands.exceptions.CommandException;
import eatwhere.foodguide.logic.parser.FoodGuideParser;
import eatwhere.foodguide.logic.parser.exceptions.ParseException;
import eatwhere.foodguide.model.Model;
import eatwhere.foodguide.model.ReadOnlyFoodGuide;
import eatwhere.foodguide.model.eatery.Eatery;
import eatwhere.foodguide.storage.Storage;
import javafx.collections.ObservableList;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final FoodGuideParser foodGuideParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        foodGuideParser = new FoodGuideParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = foodGuideParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveFoodGuide(model.getFoodGuide());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyFoodGuide getFoodGuide() {
        return model.getFoodGuide();
    }

    @Override
    public ObservableList<Eatery> getFilteredEateryList() {
        return model.getFilteredEateryList();
    }

    @Override
    public Path getFoodGuideFilePath() {
        return model.getFoodGuideFilePath();
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
