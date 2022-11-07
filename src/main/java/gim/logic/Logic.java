package gim.logic;

import java.nio.file.Path;

import gim.commons.core.GuiSettings;
import gim.logic.commands.CommandResult;
import gim.logic.commands.exceptions.CommandException;
import gim.logic.parser.exceptions.ParseException;
import gim.model.ReadOnlyExerciseTracker;
import gim.model.exercise.Exercise;
import gim.model.exercise.ExerciseHashMap;
import javafx.collections.ObservableList;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the ExerciseTracker.
     *
     * @see gim.model.Model#getExerciseTracker()
     */
    ReadOnlyExerciseTracker getExerciseTracker();

    /**
     * Returns an unmodifiable view of the filtered list of exercises.
     */
    ObservableList<Exercise> getFilteredExerciseList();

    /**
     * Returns a copy of the hashmap of exercises stored.
     */
    ExerciseHashMap getExerciseHashmap();

    /**
     * Returns the user prefs' exercise tracker file path.
     */
    Path getExerciseTrackerFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

}
