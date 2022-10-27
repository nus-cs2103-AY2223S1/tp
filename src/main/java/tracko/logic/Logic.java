package tracko.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import tracko.commons.core.GuiSettings;
import tracko.logic.commands.CommandResult;
import tracko.logic.commands.exceptions.CommandException;
import tracko.logic.parser.exceptions.ParseException;
import tracko.model.ReadOnlyTrackO;
import tracko.model.item.InventoryItem;
import tracko.model.order.Order;

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
     * Returns the TrackO.
     */
    ReadOnlyTrackO getTrackO();

    /** Returns an unmodifiable view of the list of orders */
    ObservableList<Order> getOrderList();

    /** Returns an unmodifiable view of the list of orders */
    ObservableList<Order> getFilteredOrderList();

    /** Returns an unmodifiable view of the sorted list of orders */
    ObservableList<Order> getSortedOrderList();

    /** Returns an unmodifiable view of the list of items */
    ObservableList<InventoryItem> getFilteredItemList();

    /**
     * Returns the user pref's order list file path.
     */
    Path getTrackOFilePath();


    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
