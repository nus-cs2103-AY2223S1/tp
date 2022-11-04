package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.model.order.Order;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.Supplier;
import seedu.address.model.pet.Pet;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Buyer> PREDICATE_SHOW_ALL_BUYERS = unused -> true;
    Predicate<Supplier> PREDICATE_SHOW_ALL_SUPPLIERS = unused -> true;
    Predicate<Deliverer> PREDICATE_SHOW_ALL_DELIVERERS = unused -> true;
    Predicate<Pet> PREDICATE_SHOW_ALL_PETS = unused -> true;
    Predicate<Order> PREDICATE_SHOW_ALL_ORDERS = unused -> true;


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
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasBuyer(Buyer buyer);
    boolean hasSupplier(Supplier supplier);
    boolean hasDeliverer(Deliverer deliverer);
    boolean hasPet(Pet pet);
    boolean hasOrder(Order order);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deleteBuyer(Buyer target);
    void deleteSupplier(Supplier target);
    void deleteDeliverer(Deliverer target);
    void deletePet(Pet target);
    void deleteOrder(Order target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addBuyer(Buyer buyer);
    void addSupplier(Supplier supplier);
    void addDeliverer(Deliverer deliverer);
    void addPet(Pet pet);
    void addOrder(Order order);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setBuyer(Buyer target, Buyer editedBuyer);
    void setSupplier(Supplier target, Supplier editedSupplier);
    void setDeliverer(Deliverer target, Deliverer editedDeliverer);
    void setPet(Pet target, Pet editedPet);
    void setOrder(Order target, Order editedOrder);

    /**
     * Sorts the buyer list using the specified comparator.
     * @param comparator The specified comparator.
     */
    void sortBuyer(Comparator<Buyer> comparator);

    /**
     * Sorts the supplier list using the specified comparator.
     * @param comparator The specified comparator.
     */
    void sortSupplier(Comparator<Supplier> comparator);

    /**
     * Sorts the deliverer list using the specified comparator.
     * @param comparator The specified comparator.
     */
    void sortDeliverer(Comparator<Deliverer> comparator);

    /**
     * Sorts the order list using the specified comparator.
     * @param comparator The specified comparator.
     */
    void sortOrder(Comparator<Order> comparator);

    /**
     * Sorts the pet list using the specified comparator.
     * @param comparator The specified comparator.
     */
    void sortPet(Comparator<Pet> comparator);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Buyer> getFilteredBuyerList();
    ObservableList<Supplier> getFilteredSupplierList();
    ObservableList<Deliverer> getFilteredDelivererList();
    ObservableList<Pet> getFilteredPetList();
    ObservableList<Order> getFilteredOrderList();
    ObservableList<Object> getFilteredMainList();
    ObservableList<Object> getFilteredCurrList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredBuyerList(Predicate<Buyer> predicate);
    void updateFilteredSupplierList(Predicate<Supplier> predicate);
    void updateFilteredDelivererList(Predicate<Deliverer> predicate);
    void updateFilteredPetList(Predicate<Pet> predicate);
    void updateFilteredOrderList(Predicate<Order> predicate);

    /**
     * Gets orders from a given buyer.
     * @param buyer The given buyer.
     * @return The list of orders.
     */
    List<Order> getOrdersFromBuyer(Buyer buyer);

    /**
     * Gets orders from a given deliverer.
     * @param deliverer The given deliverer.
     * @return The list of orders.
     */
    List<Order> getOrdersFromDeliverer(Deliverer deliverer);

    /**
     * Gets pets from a given supplier.
     * @param supplier The given supplier.
     * @return The list of pets.
     */
    List<Pet> getPetsFromSupplier(Supplier supplier);

    /**
     * Clears the curr display list
     */
    void clearCurrList();

    /**
     * Switches the current displayed list to buyer list.
     */
    void switchToBuyerList();

    /**
     * Switches the current displayed list to supplier list.
     */
    void switchToSupplierList();

    /**
     * Switches the current displayed list to deliverer list.
     */
    void switchToDelivererList();

    /**
     * Switches the current displayed list to order list.
     */
    void switchToOrderList();

    /**
     * Switches the current displayed list to pet list.
     */
    void switchToPetList();

    /**
     * Switches the current displayed list to main list.
     */
    void switchToMainList();

    /**
     * Sets the current list to the list of orders of a buyer.
     */
    void checkBuyerOrder(Buyer buyer);

    /**
     * Sets the current list to the list of pets of a supplier.
     */
    void checkSupplierPet(Supplier supplier);

    /**
     * Sets the current list to the list of orders of a deliverer.
     */
    void checkDelivererOrder(Deliverer deliverer);

    /**
     * Sets the current list to the show the buyer of an order.
     */
    void checkBuyerOfOrder(Order order);

    /**
     * Sets the current list to show the supplier of a pet.
     */
    void checkSupplierOfPet(Pet pet);

}
