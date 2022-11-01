package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.order.Order;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.MasterList;
import seedu.address.model.person.Supplier;
import seedu.address.model.pet.Pet;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {

    //@@author Russel-reused
    //Reused from https://github.com/RussellDash332/ip/blob/master/src/main/java/stashy/parser/Parser.java
    //with minor modification, it is a pretty good way to organise and extend the acceptable date formats.
    public static final String[] ACCEPTABLE_DATE_FORMATS = new String[]{
        "MMM dd yyyy",
        "dd/MM/yyyy",
        "yyyy/MM/dd",
        "yyyy-MM-dd",
        "dd MMM yyyy",
        "dd MMM yyyy",
        "MMM dd, yyyy",
        "MMM dd, yyyy"
    };

    public static final String PREFERRED_DATE_FORMAT = "yyyy-MM-dd";

    public static final DateTimeFormatter PREFERRED_FORMATTER = DateTimeFormatter.ofPattern(PREFERRED_DATE_FORMAT);
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Buyer> filteredBuyers;
    private final FilteredList<Supplier> filteredSuppliers;
    private final FilteredList<Deliverer> filteredDeliverers;
    private final FilteredList<Pet> filteredPets;
    private final FilteredList<Order> filteredOrders;
    private final MasterList filteredAll;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredBuyers = new FilteredList<>(this.addressBook.getBuyerList());
        filteredSuppliers = new FilteredList<>(this.addressBook.getSupplierList());
        filteredDeliverers = new FilteredList<>(this.addressBook.getDelivererList());
        filteredPets = new FilteredList<>(this.addressBook.getPetList());
        filteredOrders = new FilteredList<>(this.addressBook.getOrderList());
        filteredAll = new MasterList();
        collect();
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasBuyer(Buyer buyer) {
        requireNonNull(buyer);
        return addressBook.hasBuyer(buyer);
    }

    @Override
    public boolean hasSupplier(Supplier supplier) {
        requireNonNull(supplier);
        return addressBook.hasSupplier(supplier);
    }

    @Override
    public boolean hasDeliverer(Deliverer deliverer) {
        requireNonNull(deliverer);
        return addressBook.hasDeliverer(deliverer);
    }

    @Override
    public boolean hasPet(Pet pet) {
        requireNonNull(pet);
        return addressBook.hasPet(pet);
    }

    @Override
    public boolean hasOrder(Order order) {
        requireNonNull(order);
        return addressBook.hasOrder(order);
    }

    @Override
    public void deleteBuyer(Buyer target) {
        addressBook.removeBuyer(target);
        collect();
    }

    @Override
    public void deleteSupplier(Supplier target) {
        addressBook.removeSupplier(target);
        collect();
    }

    @Override
    public void deleteDeliverer(Deliverer target) {
        addressBook.removeDeliverer(target);
        collect();
    }

    @Override
    public void deletePet(Pet target) {
        addressBook.removePet(target);
        collect();
    }

    @Override
    public void deleteOrder(Order target) {
        addressBook.removeOrder(target);
        collect();
    }

    @Override
    public void addBuyer(Buyer buyer) {
        addressBook.addBuyer(buyer);
        updateFilteredBuyerList(PREDICATE_SHOW_ALL_BUYERS);
    }

    @Override
    public void addSupplier(Supplier supplier) {
        addressBook.addSupplier(supplier);
        updateFilteredSupplierList(PREDICATE_SHOW_ALL_SUPPLIERS);
    }

    @Override
    public void addDeliverer(Deliverer deliverer) {
        addressBook.addDeliverer(deliverer);
        updateFilteredDelivererList(PREDICATE_SHOW_ALL_DELIVERERS);
    }

    @Override
    public void addPet(Pet pet) {
        addressBook.addPet(pet);
        updateFilteredPetList(PREDICATE_SHOW_ALL_PETS);
    }

    @Override
    public void addOrder(Order order) {
        addressBook.addOrder(order);
        updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDERS);
    }

    @Override
    public void setBuyer(Buyer target, Buyer editedBuyer) {
        requireAllNonNull(target, editedBuyer);

        addressBook.setBuyer(target, editedBuyer);
        List<Order> orderFromId = addressBook.getOrderFromId(target.getOrderIds());
        for (Order order: orderFromId) {
            order.setBuyer(editedBuyer);
        }
        collect();
    }

    @Override
    public void setSupplier(Supplier target, Supplier editedSupplier) {
        requireAllNonNull(target, editedSupplier);

        addressBook.setSupplier(target, editedSupplier);
        List<Pet> petFromId = addressBook.getPetFromId(target.getPetIds());
        for (Pet pet: petFromId) {
            pet.setSupplier(editedSupplier);
        }
        collect();
    }

    @Override
    public void setDeliverer(Deliverer target, Deliverer editedDeliverer) {
        requireAllNonNull(target, editedDeliverer);

        addressBook.setDeliverer(target, editedDeliverer);

        collect();
    }

    @Override
    public void setPet(Pet target, Pet editedPet) {
        requireAllNonNull(target, editedPet);

        addressBook.setPet(target, editedPet);
        collect();
    }

    @Override
    public void setOrder(Order target, Order editedOrder) {
        requireAllNonNull(target, editedOrder);

        addressBook.setOrder(target, editedOrder);
        collect();
    }

    @Override
    public void sortBuyer(Comparator<Buyer> comparator) {
        requireNonNull(comparator);
        addressBook.sortBuyer(comparator);
        collect();
    }

    @Override
    public void sortSupplier(Comparator<Supplier> comparator) {
        requireNonNull(comparator);
        addressBook.sortSupplier(comparator);
        collect();
    }

    @Override
    public void sortDeliverer(Comparator<Deliverer> comparator) {
        requireNonNull(comparator);
        addressBook.sortDeliverer(comparator);
        collect();
    }

    @Override
    public void sortOrder(Comparator<Order> comparator) {
        requireNonNull(comparator);
        addressBook.sortOrder(comparator);
        collect();
    }

    @Override
    public void sortPet(Comparator<Pet> comparator) {
        requireNonNull(comparator);
        addressBook.sortPet(comparator);
        collect();
    }

    //=========== Filtered Person List Accessors =============================================================
    /**
     * Returns an ObservableList of buyers in the filteredPersons list.
     *
     * @return ObservableList of buyers.
     */
    public ObservableList<Buyer> getFilteredBuyerList() {
        return filteredBuyers;
    }

    /**
     * Returns an ObservableList of suppliers in the filteredPersons list.
     *
     * @return ObservableList of suppliers.
     */
    public ObservableList<Supplier> getFilteredSupplierList() {
        return filteredSuppliers;
    }

    /**
     * Returns an ObservableList of deliverers in the filteredPersons list.
     *
     * @return ObservableList of deliverers.
     */
    public ObservableList<Deliverer> getFilteredDelivererList() {
        return filteredDeliverers;
    }

    @Override
    public ObservableList<Pet> getFilteredPetList() {
        return filteredPets;
    }

    @Override
    public ObservableList<Order> getFilteredOrderList() {
        return filteredOrders;
    }

    @Override
    public ObservableList<Object> getFilteredMainList() {
        collect();
        return filteredAll.getMasterList();
    }

    @Override
    public void updateFilteredBuyerList(Predicate<Buyer> predicate) {
        requireNonNull(predicate);
        filteredBuyers.setPredicate(predicate);
        collect();
    }

    @Override
    public void updateFilteredSupplierList(Predicate<Supplier> predicate) {
        requireNonNull(predicate);
        filteredSuppliers.setPredicate(predicate);
        collect();
    }

    @Override
    public void updateFilteredDelivererList(Predicate<Deliverer> predicate) {
        requireNonNull(predicate);
        filteredDeliverers.setPredicate(predicate);
        collect();
    }

    @Override
    public void updateFilteredPetList(Predicate<Pet> predicate) {
        requireNonNull(predicate);
        filteredPets.setPredicate(predicate);
        collect();
    }

    @Override
    public void updateFilteredOrderList(Predicate<Order> predicate) {
        requireNonNull(predicate);
        filteredOrders.setPredicate(predicate);
        collect();
    }

    @Override
    public List<Order> getOrdersFromBuyer(Buyer buyer) {
        requireNonNull(buyer);
        return addressBook.getOrderFromId(buyer.getOrderIds());
    }

    @Override
    public List<Order> getOrdersFromDeliverer(Deliverer deliverer) {
        requireNonNull(deliverer);
        return addressBook.getOrderFromId(deliverer.getOrders());
    }

    @Override
    public List<Pet> getPetsFromSupplier(Supplier supplier) {
        requireNonNull(supplier);
        return addressBook.getPetFromId(supplier.getPetIds());
    }

    @Override
    public void clearMasterList() {
        filteredAll.clear();
    }

    private void collect() {
        filteredAll.clear();
        filteredAll.addAll(filteredBuyers);
        filteredAll.addAll(filteredSuppliers);
        filteredAll.addAll(filteredDeliverers);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredBuyers.equals(other.filteredBuyers)
                && filteredSuppliers.equals(other.filteredSuppliers)
                && filteredDeliverers.equals(other.filteredDeliverers)
                && filteredPets.equals(other.filteredPets)
                && filteredOrders.equals(other.filteredOrders);
    }

}
