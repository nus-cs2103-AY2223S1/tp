package gim.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import gim.commons.core.GuiSettings;
import gim.commons.core.LogsCenter;
import gim.logic.commands.Command;
import gim.logic.commands.CommandResult;
import gim.logic.commands.exceptions.CommandException;
import gim.logic.parser.ExerciseTrackerParser;
import gim.logic.parser.exceptions.ParseException;
import gim.model.Model;
import gim.model.ReadOnlyExerciseTracker;
import gim.model.exercise.Exercise;
import gim.model.exercise.ExerciseHashMap;
import gim.storage.Storage;
import javafx.collections.ObservableList;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final ExerciseTrackerParser exerciseTrackerParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        exerciseTrackerParser = new ExerciseTrackerParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = exerciseTrackerParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveExerciseTracker(model.getExerciseTracker());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyExerciseTracker getExerciseTracker() {
        return model.getExerciseTracker();
    }

    @Override
    public ObservableList<Exercise> getFilteredExerciseList() {
        return model.getFilteredExerciseList();
    }

    @Override
    public Path getExerciseTrackerFilePath() {
        return model.getExerciseTrackerFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    public ExerciseHashMap getExerciseHashmap() {
        return model.getExerciseHashMap();
    }
}
