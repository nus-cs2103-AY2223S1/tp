package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.listing.Listing;
import seedu.address.model.listing.UniqueListingList;
import seedu.address.model.offer.Offer;
import seedu.address.model.offer.UniqueOfferList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueListingList listings;
    private final UniqueOfferList offers;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        listings = new UniqueListingList();
        offers = new UniqueOfferList();
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
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the listing list with {@code listings}.
     * {@code listings} must not contain duplicate listings.
     */
    public void setListings(List<Listing> listings) {
        this.listings.setListings(listings);
    }

    /**
     * Replaces the contents of the listing list with {@code listings}.
     * {@code listings} must not contain duplicate listings.
     */
    public void setOffers(List<Offer> offers) {
        this.offers.setOffers(offers);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        try {
            setPersons(newData.getPersonList());
            setOffers(newData.getOfferList());
        } catch (Exception e) {
            setListings(newData.getListingList());
        }
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Returns true if a listing with the same identity as {@code listing} exists in the address book.
     */
    public boolean hasListing(Listing listing) {
        requireNonNull(listing);
        return listings.contains(listing);
    }

    /**
     * Returns true if an offer with the same identity as {@code listing} exists in the address book.
     */
    public boolean hasOffer(Offer offer) {
        requireNonNull(offer);
        return offers.contains(offer);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
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
     * Adds an offer to the address book.
     * The offer must not already exist in the address book.
     */
    public void addOffer(Offer o) {
        offers.add(o);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
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
     * Replaces the given offer {@code target} in the list with {@code editedOffer}.
     * {@code target} must exist in the address book.
     * The offer identity of {@code editedOffer} must not be the same
     * as another existing offer in the address book.
     */
    public void setOffer(Offer target, Offer editedOffer) {
        requireNonNull(editedOffer);

        offers.setOffer(target, editedOffer);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeListing(Listing key) {
        listings.remove(key);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeOffer(Offer key) {
        offers.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        if (listings.asUnmodifiableObservableList().isEmpty() && offers.asUnmodifiableObservableList().isEmpty()) {
            return persons.asUnmodifiableObservableList().size() + " persons";
        } else if (offers.asUnmodifiableObservableList().isEmpty()) {
            return listings.asUnmodifiableObservableList().size() + " listings";
        } else {
            return offers.asUnmodifiableObservableList().size() + " offers";
        }
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Listing> getListingList() {
        return listings.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Offer> getOfferList() {
        return offers.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (listings.asUnmodifiableObservableList().isEmpty() && offers.asUnmodifiableObservableList().isEmpty()) {
            return other == this // short circuit if same object
                    || (other instanceof AddressBook // instanceof handles nulls
                    && persons.equals(((AddressBook) other).persons));
        } else if (offers.asUnmodifiableObservableList().isEmpty()) {
            return other == this // short circuit if same object
                    || (other instanceof AddressBook // instanceof handles nulls
                    && listings.equals(((AddressBook) other).listings));
        } else {
            return other == this // short circuit if same object
                    || (other instanceof AddressBook // instanceof handles nulls
                    && offers.equals(((AddressBook) other).offers));
        }
    }

    @Override
    public int hashCode() {
        if (listings.asUnmodifiableObservableList().isEmpty() && offers.asUnmodifiableObservableList().isEmpty()) {
            return persons.hashCode();
        } else if (offers.asUnmodifiableObservableList().isEmpty()){
            return listings.hashCode();
        } else {
            return offers.hashCode();
        }
    }
}
