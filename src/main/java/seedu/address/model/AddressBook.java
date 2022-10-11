package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.client.UniqueClientList;
import seedu.address.model.listing.Listing;
import seedu.address.model.listing.UniqueListingList;
import seedu.address.model.client.Client;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueClientList persons;
    private final UniqueListingList listings;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniqueClientList();
        listings = new UniqueListingList();
    }

    public AddressBook() {
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the client list with {@code clients}.
     * {@code clients} must not contain duplicate clients.
     */
    public void setPersons(List<Client> clients) {
        this.persons.setClients(clients);
    }

    /**
     * Replaces the contents of the listing list with {@code listings}.
     * {@code listings} must not contain duplicate listings.
     */
    public void setListings(List<Listing> listings) {
        this.listings.setListings(listings);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        try {
            setPersons(newData.getPersonList());
        } catch (Exception e) {
            setListings(newData.getListingList());
        }
    }

    //// client-level operations

    /**
     * Returns true if a client with the same identity as {@code client} exists in the address book.
     */
    public boolean hasPerson(Client client) {
        requireNonNull(client);
        return persons.contains(client);
    }

    /**
     * Returns true if a listing with the same identity as {@code listing} exists in the address book.
     */
    public boolean hasListing(Listing listing) {
        requireNonNull(listing);
        return listings.contains(listing);
    }

    /**
     * Adds a client to the address book.
     * The client must not already exist in the address book.
     */
    public void addPerson(Client p) {
        persons.add(p);
    }

    /**
     * Adds a listing to the address book.
     * The listing must not already exist in the address book.
     */
    public void addListing(Listing l) {
        listings.add(l);
    }

    /**
     * Replaces the given client {@code target} in the list with {@code editedClient}.
     * {@code target} must exist in the address book.
     * The client identity of {@code editedClient} must not be the same as another existing client in the address book.
     */
    public void setPerson(Client target, Client editedClient) {
        requireNonNull(editedClient);

        persons.setClient(target, editedClient);
    }

    /**
     * Replaces the given listing {@code target} in the list with {@code editedListing}.
     * {@code target} must exist in the address book.
     * The listing identity of {@code editedListing} must not be the same
     * as another existing listing in the address book.
     */
    public void setListing(Listing target, Listing editedListing) {
        requireNonNull(editedListing);

        listings.setListing(target, editedListing);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Client key) {
        persons.remove(key);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeListing(Listing key) {
        listings.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        if (listings.asUnmodifiableObservableList().isEmpty()) {
            return persons.asUnmodifiableObservableList().size() + " persons";
        } else {
            return listings.asUnmodifiableObservableList().size() + " listings";
        }
        // TODO: refine later
    }

    @Override
    public ObservableList<Client> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Listing> getListingList() {
        return listings.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (listings.asUnmodifiableObservableList().isEmpty()) {
            return other == this // short circuit if same object
                    || (other instanceof AddressBook // instanceof handles nulls
                    && persons.equals(((AddressBook) other).persons));
        } else {
            return other == this // short circuit if same object
                    || (other instanceof AddressBook // instanceof handles nulls
                    && listings.equals(((AddressBook) other).listings));
        }
    }

    @Override
    public int hashCode() {
        if (listings.asUnmodifiableObservableList().isEmpty()) {
            return persons.hashCode();
        } else {
            return listings.hashCode();
        }
    }
}
