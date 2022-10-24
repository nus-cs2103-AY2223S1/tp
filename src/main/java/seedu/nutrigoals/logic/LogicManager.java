package seedu.nutrigoals.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.beans.property.DoubleProperty;
import javafx.collections.ObservableList;
import seedu.nutrigoals.commons.core.GuiSettings;
import seedu.nutrigoals.commons.core.LogsCenter;
import seedu.nutrigoals.logic.commands.Command;
import seedu.nutrigoals.logic.commands.CommandResult;
import seedu.nutrigoals.logic.commands.exceptions.CommandException;
import seedu.nutrigoals.logic.parser.NutriGoalsParser;
import seedu.nutrigoals.logic.parser.exceptions.ParseException;
import seedu.nutrigoals.model.Model;
import seedu.nutrigoals.model.ReadOnlyNutriGoals;
import seedu.nutrigoals.model.meal.Food;
import seedu.nutrigoals.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final NutriGoalsParser nutriGoalsParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        nutriGoalsParser = new NutriGoalsParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = nutriGoalsParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveNutriGoals(model.getNutriGoals());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyNutriGoals getNutriGoals() {
        return model.getNutriGoals();
    }

    @Override
    public ObservableList<Food> getFilteredFoodList() {
        return model.getFilteredFoodList();
    }

    @Override
    public Path getNutriGoalsFilePath() {
        return model.getNutriGoalsFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public DoubleProperty getCalorieIntakeProgress() {
        return model.getCalorieIntakeProgress();
    }
}
