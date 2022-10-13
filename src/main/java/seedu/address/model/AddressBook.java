package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.order.Order;
import seedu.address.model.order.UniqueOrderList;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.Supplier;
import seedu.address.model.person.UniqueBuyerList;
import seedu.address.model.person.UniqueDelivererList;
import seedu.address.model.person.UniqueSupplierList;
import seedu.address.model.pet.Pet;
import seedu.address.model.pet.UniquePetList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueBuyerList buyers;
    private final UniqueSupplierList suppliers;
    private final UniqueDelivererList deliverers;
    private final UniquePetList pets;
    private final UniqueOrderList orders;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     * among constructors.
     */
    {
        buyers = new UniqueBuyerList();
        suppliers = new UniqueSupplierList();
        deliverers = new UniqueDelivererList();
        pets = new UniquePetList();
        orders = new UniqueOrderList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setBuyers(List<Buyer> persons) {
        this.buyers.setPersons(persons);
    }

    public void setSuppliers(List<Supplier> persons) {
        this.suppliers.setPersons(persons);
    }

    public void setDeliverers(List<Deliverer> persons) {
        this.deliverers.setPersons(persons);
    }

    public void setPets(List<Pet> pets) {
        this.pets.setPets(pets);
    }

    public void setOrders(List<Order> orders) {
        this.orders.setOrders(orders);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setBuyers(newData.getBuyerList());
        setSuppliers(newData.getSupplierList());
        setDeliverers(newData.getDelivererList());
        setPets(newData.getPetList());
        setOrders(newData.getOrderList());

    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasBuyer(Buyer buyer) {
        requireNonNull(buyer);
        return buyers.contains(buyer);
    }

    public boolean hasSupplier(Supplier supplier) {
        requireNonNull(supplier);
        return suppliers.contains(supplier);
    }

    public boolean hasDeliverer(Deliverer deliverer) {
        requireNonNull(deliverer);
        return deliverers.contains(deliverer);
    }

    public boolean hasPet(Pet pet) {
        requireNonNull(pet);
        return pets.contains(pet);
    }

    public boolean hasOrder(Order order) {
        requireNonNull(order);
        return orders.contains(order);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addBuyer(Buyer p) {
        buyers.add(p);
    }

    public void addSupplier(Supplier p) {
        suppliers.add(p);
    }

    public void addDeliverer(Deliverer p) {
        deliverers.add(p);
    }

    public void addOrder(Order p) {
        orders.add(p);
    }

    public void addPet(Pet p) {
        pets.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setBuyer(Buyer target, Buyer editedBuyer) {
        requireNonNull(editedBuyer);

        buyers.setPerson(target, editedBuyer);
    }

    public void setSupplier(Supplier target, Supplier editedSupplier) {
        requireNonNull(editedSupplier);

        suppliers.setPerson(target, editedSupplier);
    }

    public void setDeliverer(Deliverer target, Deliverer editedDeliverer) {
        requireNonNull(editedDeliverer);

        deliverers.setPerson(target, editedDeliverer);
    }

    public void setPet(Pet target, Pet editedPet) {
        requireNonNull(editedPet);

        pets.setPet(target, editedPet);
    }

    public void setOrder(Order target, Order editedOrder) {
        requireNonNull(editedOrder);

        orders.setOrder(target, editedOrder);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeBuyer(Buyer key) {
        buyers.remove(key);
    }

    public void removeSupplier(Supplier key) {
        suppliers.remove(key);
    }

    public void removeDeliverer(Deliverer key) {
        deliverers.remove(key);
    }

    public void removePet(Pet key) {
        pets.remove(key);
    }

    public void removeOrder(Order key) {
        orders.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(buyers.asUnmodifiableObservableList().size() + " buyers");
        sb.append("\n");
        sb.append(suppliers.asUnmodifiableObservableList().size() + " suppliers");
        sb.append("\n");
        sb.append(deliverers.asUnmodifiableObservableList().size() + " deliverers");
        sb.append("\n");
        sb.append(pets.asUnmodifiableObservableList().size() + " pets");
        sb.append("\n");
        sb.append(orders.asUnmodifiableObservableList().size() + " orders");
        sb.append("\n");
        return sb.toString();
        // TODO: refine later
    }

    @Override
    public ObservableList<Buyer> getBuyerList() {
        return buyers.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Supplier> getSupplierList() {
        return suppliers.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Deliverer> getDelivererList() {
        return deliverers.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Pet> getPetList() {
        return pets.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Order> getOrderList() {
        return orders.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && buyers.equals(((AddressBook) other).buyers)
                && suppliers.equals(((AddressBook) other).suppliers)
                && deliverers.equals(((AddressBook) other).deliverers)
                && pets.equals(((AddressBook) other).pets)
                && orders.equals(((AddressBook) other).orders));
    }

}
