package tracko.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import tracko.commons.core.GuiSettings;
import tracko.model.order.Order;
import tracko.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    // TrackO =======================================================================================

    // TODO: add item related methods

    /**
     * Returns the user pref's orders file path.
     */
    Path getOrdersFilePath();

    /**
     * Sets the user pref's orders file path.
     */
    void setOrdersFilePath(Path ordersFilePath);

    /**
     * Replaces application data with the data in {@code trackO}
     */
    void setTrackO(ReadOnlyTrackO trackO);

    /** Returns the TrackO */
    ReadOnlyTrackO getTrackO();

    /**
     * Adds the given order.
     */
    void addOrder(Order order);

    /**
     * Returns the order list.
     */
    ObservableList<Order> getOrderList();

}
