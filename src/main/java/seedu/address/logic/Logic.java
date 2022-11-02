package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyBuyerBook;
import seedu.address.model.ReadOnlyPropertyBook;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.property.Property;

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
     * Returns the BuyerBook.
     *
     * @see seedu.address.model.Model#getBuyerBook()
     */
    ReadOnlyBuyerBook getBuyerBook();

    /** Returns an unmodifiable view of the filtered list of buyers */
    ObservableList<Buyer> getFilteredBuyerList();

    /**
     * Returns the PropertyBook.
     *
     * @see seedu.address.model.Model#getPropertyBook()
     */
    ReadOnlyPropertyBook getPropertyBook();

    /** Returns an unmodifiable view of the filtered list of properties */
    ObservableList<Property> getFilteredPropertyList();


    /**
     * Returns the user prefs' buyer book file path.
     */
    Path getBuyerBookFilePath();

    /**
     * Returns the user prefs' property book file path.
     */
    Path getPropertyBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
