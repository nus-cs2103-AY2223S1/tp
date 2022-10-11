package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.person.*;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueBuyerList buyers;
    private final UniqueSupplierList suppliers;
    private final UniqueDelivererList deliverers;

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

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setBuyers(newData.getBuyerList());
        setSuppliers(newData.getSupplierList());
        setDeliverers(newData.getDelivererList());
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

    public void setDeliverers(Deliverer target, Deliverer editedDeliverer) {
        requireNonNull(editedDeliverer);

        deliverers.setPerson(target, editedDeliverer);
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
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && buyers.equals(((AddressBook) other).buyers)
                && suppliers.equals(((AddressBook) other).suppliers)
                && deliverers.equals(((AddressBook) other).deliverers));
    }

//TODO
//    @Override
//    public int hashCode() {
//        return persons.hashCode();
//    }

}
