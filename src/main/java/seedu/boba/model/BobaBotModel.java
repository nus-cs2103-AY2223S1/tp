package seedu.boba.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import seedu.boba.commons.core.GuiSettings;
import seedu.boba.model.customer.Customer;
import seedu.boba.model.customer.Email;
import seedu.boba.model.customer.Phone;
import seedu.boba.model.customer.Reward;
import seedu.boba.model.customer.exceptions.PersonNotFoundException;
import seedu.boba.model.exceptions.NextStateNotFoundException;
import seedu.boba.model.exceptions.PreviousStateNotFoundException;

/**
 * The API of the BobaBotModel component.
 */
public interface BobaBotModel {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Customer> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

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
     * Returns the user prefs' address book file path.
     */
    Path getBobaBotFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setBobaBotFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code bobaBot}.
     */
    void setBobaBot(ReadOnlyBobaBot bobaBot);

    /** Returns the BobaBot */
    ReadOnlyBobaBot getBobaBot();

    /**
     * Returns true if a customer with the same identity as {@code customer} exists in the address book.
     */
    boolean hasPerson(Customer customer);

    /**
     * Deletes the given customer.
     * The customer must exist in the address book.
     */
    void deletePerson(Customer target);

    /**
     * Adds the given customer.
     * {@code customer} must not already exist in the address book.
     */
    void addPerson(Customer customer);

    /**
     * Replaces the given customer {@code target} with {@code editedCustomer}.
     * {@code target} must exist in the address book.
     * The customer identity of {@code editedCustomer} must not be the same as another existing customer
     * in the address book.
     */
    void setPerson(Customer target, Customer editedCustomer);

    /** Returns an unmodifiable view of the filtered customer list */
    ObservableList<Customer> getFilteredPersonList();

    /**
     * Updates the filter of the filtered customer list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Customer> predicate);

    /**
     * Returns the index of the customer with the same phone number.
     *
     * @param phone Phone number to search
     * @return index of the customer with the same phone number
     * @throws PersonNotFoundException if no customer with corresponding phone number found
     */
    int findNum(Phone phone) throws PersonNotFoundException;

    /**
     * Returns the index of the customer with the same email.
     *
     * @param email Email to search
     * @return index of the customer with the same email
     * @throws PersonNotFoundException if no customer with corresponding email found
     */
    int findEmail(Email email) throws PersonNotFoundException;

    /**
     * Returns the current Reward points of a Customer
     *
     * @param phone Phone number of the Customer of interest
     * @return the current Reward points of a Customer
     */
    Reward getCurrentReward(Phone phone);

    /**
     * Returns the current Reward points of a Customer
     *
     * @param email Email of the Customer of interest
     * @return the current Reward points of a Customer
     */
    Reward getCurrentReward(Email email);

    /**
     * Retrieves the promotionList.
     *
     * @return an ObservableList consisting of the parsed promotion images
     */
    ObservableList<Image> getPromotionList();

    /**
     * Parses all the image files stored in the folder indicated by the filepath.
     *
     * @param filePath String representation of the FilePath of the folder containing the promotion images
     */
    void parseAllPromotion(String filePath);

    /**
     * Stores the current version of bobaBot into VersionedBobaBot.
     */
    void commitBobaBot();

    /**
     * Retrieves the previous state/version of bobaBot from VersionedBobaBot.
     *
     * @throws PreviousStateNotFoundException if bobaBot is in the initialised state
     */
    void undoBobaBot() throws PreviousStateNotFoundException;

    /**
     * Retrieves the state/version of bobaBot before the undoCommand from VersionedBobaBot.
     *
     * @throws NextStateNotFoundException if bobaBot is in the most updated state.
     */
    void redoBobaBot() throws NextStateNotFoundException;
}
