package seedu.boba.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import seedu.boba.commons.core.GuiSettings;
import seedu.boba.logic.commands.CommandResult;
import seedu.boba.logic.commands.exceptions.CommandException;
import seedu.boba.logic.parser.exceptions.ParseException;
import seedu.boba.model.BobaBotModel;
import seedu.boba.model.ReadOnlyBobaBot;
import seedu.boba.model.customer.Customer;

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
     * Returns the BobaBot.
     *
     * @see BobaBotModel#getBobaBot()
     */
    ReadOnlyBobaBot getBobaBot();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Customer> getFilteredPersonList();

    /** Returns an unmodifiable view of the list of promotion images*/
    ObservableList<Image> getPromotionList();

    void parseAllPromotion(String filePath);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getBobaBotFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
