package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.client.Client;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.exceptions.ConflictingMeetingException;
import seedu.address.model.product.Product;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true. Used for filtered client list. */
    Predicate<Client> PREDICATE_SHOW_ALL_CLIENTS = unused -> true;

    /** {@code Predicate} that always evaluate to true. Used for filtered meeting list. */
    Predicate<Meeting> PREDICATE_SHOW_ALL_MEETING = unused -> true;

    /** {@code Predicate} that always evaluate to true. Used for filtered product list. */
    Predicate<Product> PREDICATE_SHOW_ALL_PRODUCTS = unused -> true;

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

    /**
     * Returns the user prefs' MyInsuRec file path.
     */
    Path getMyInsuRecFilePath();

    /**
     * Sets the user prefs' MyInsuRec file path.
     */
    void setMyInsuRecFilePath(Path myInsuRecFilePath);

    /**
     * Replaces MyInsuRec data with the data in {@code MyInsuRec}.
     */
    void setMyInsuRec(ReadOnlyMyInsuRec myInsuRec);

    /** Returns the MyInsuRec */
    ReadOnlyMyInsuRec getMyInsuRec();

    /**
     * Returns true if a client with the same identity as {@code client} exists in the MyInsuRec.
     */
    boolean hasClient(Client client);

    /**
     * Deletes the given client.
     * The client must exist in the MyInsuRec.
     */
    void deleteClient(Client target);

    /**
     * Adds the given client.
     * {@code client} must not already exist in the MyInsuRec.
     */
    void addClient(Client client);

    /**
     * Returns true if a meeting with the same time as {@code meeting} exists in the MyInsuRec.
     */
    boolean hasMeeting(Meeting meeting);

    /**
     * Returns true if a meeting with the same date, time, and description as {@code meeting} exists in the MyInsuRec.
     * This is a stronger version of hasMeeting.
     */
    boolean hasSpecificMeeting(Meeting meeting);

    /**
     * Adds the given meeting.
     * {@code meeting} must not already exist in the MyInsuRec.
     */
    void addMeeting(Meeting meeting);

    /**
     * Replaces the given meeting {@code target} with {@code editedMeeting}.
     * {@code target} must exist in the MyInsuRec.
     * The meeting identity of {@code editedMeeting} must not be the same as another existing meeting in the MyInsuRec.
     */
    void setMeeting(Meeting target, Meeting editedMeeting) throws ConflictingMeetingException;

    /**
     * Adds the given product.
     * {@code product} must not already exist in the MyInsuRec.
     */
    void addProduct(Product product);

    /**
     * Deletes the given product.
     * The meeting must exist in the product list in MyInsuRec.
     */
    void deleteProduct(Product product);

    /**
     * Returns true if a product with the same name as {@code product} exists in the MyInsuRec.
     */
    boolean hasProduct(Product product);

    /**
     * Replaces the given client {@code target} with {@code editedClient}.
     * {@code target} must exist in the MyInsuRec.
     * The client identity of {@code editedClient} must not be the same as another existing client in the MyInsuRec.
     */
    void setClient(Client target, Client editedClient);

    /** Returns an unmodifiable view of the filtered client list */
    ObservableList<Client> getFilteredClientList();

    /**
     * Updates the filter of the filtered client list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredClientList(Predicate<Client> predicate);

    void deleteMeeting(Meeting meeting);

    /** Returns an unmodifiable view of the filtered meeting list */
    ObservableList<Meeting> getFilteredMeetingList();

    /**
     * Updates the filter of the filtered Meeting list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredMeetingList(Predicate<Meeting> predicate);

    /**
     * Updates the detailedClientList to show the given {@code Client} in details.
     * @throws NullPointerException if {@code Client} is null.
     */
    void setDetailedClient(Client client);

    /** Returns an unmodifiable view of the meeting list, used to view one meeting in details at a time. **/
    ObservableList<Meeting> getDetailedMeetingList();

    /**
     * Updates the detailedMeetingList to show the given {@code Meeting} in details.
     * @throws NullPointerException if {@code Meeting} is null.
     */
    void setDetailedMeeting(Meeting meeting);

    /** Returns an unmodifiable view of the client list, used to view one meeting in details at a time. **/
    ObservableList<Client> getDetailedClientList();

    /** Returns an unmodifiable view of the filtered product list */
    ObservableList<Product> getFilteredProductList();

    /**
     * Updates the filter of the filtered Meeting list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredProductList(Predicate<Product> predicate);
}
