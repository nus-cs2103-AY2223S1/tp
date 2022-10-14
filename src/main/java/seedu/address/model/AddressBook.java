package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.listing.Listing;
import seedu.address.model.listing.UniqueListingList;
import seedu.address.model.offer.Offer;
import seedu.address.model.offer.UniqueOfferList;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
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
     * Replaces the contents of the offer list with {@code offers}.
     * {@code persons} must not contain duplicate offers.
     */
    public void setOffers(List<Offer> offers) {
        this.offers.setOffers(offers);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setListings(newData.getListingList());
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
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Gets the person with the given name {@code name}.
     * @param name name of the person
     * @return person with given name
     */
    public Person getPerson(Name name) {
        return persons.getPerson(name);
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
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// listing-level operations

    /**
     * Returns true if a listing with the same identity as {@code listing} exists in the address book.
     */
    public boolean hasListing(Listing listing) {
        requireNonNull(listing);
        return listings.contains(listing);
    }

    /**
     * Adds a lsiting to the address book.
     * The listing must not already exist in the address book.
     */
    public void addListing(Listing l) {
        listings.add(l);
    }

    /**
     * Gets the listing with the given id {@code id}.
     * @param id id of the listing
     * @return listing with given id
     */
    public Listing getListing(String id) {
        return listings.getListing(id);
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
     * Removes {@code listing} from this {@code AddressBook}.
     * {@code listing} must exist in the address book.
     */
    public void removeListing(Listing listing) {
        listings.remove(listing);
    }

    //// offer-level operations

    /**
     * Returns true if an offer with the same identity as {@code offer} exists in the address book.
     */
    public boolean hasOffer(Offer offer) {
        requireNonNull(offer);
        return offers.contains(offer);
    }

    /**
     * Adds an offer to the address book.
     * The offer must not already exist in the address book.
     */
    public void addOffer(Offer o) {
        offers.add(o);
    }

    /**
     * Gets the offer from the given name {@code name} and listing address {@code address}.
     * @param name name of the person in offer
     * @param address listing address of offer
     * @return offer with given name and listing address
     */
    public Offer getOffer(Name name, Address address) {
        return offers.getOffer(name, address);
    }

    /**
     * Replaces the given offer {@code target} in the list with {@code editedOffer}.
     * {@code target} must exist in the address book.
     * The offer identity of {@code editedOffer} must not be the same as another existing offer in the address book.
     */
    public void setOffer(Offer target, Offer editedOffer) {
        requireNonNull(editedOffer);

        offers.setOffer(target, editedOffer);
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
        int personListSize = persons.asUnmodifiableObservableList().size();
        int listingListSize = listings.asUnmodifiableObservableList().size();
        int offerListSize = offers.asUnmodifiableObservableList().size();
        return String.format("%d persons, %d listings, %d offers", personListSize, listingListSize, offerListSize);
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
        return other == this // short circuit if same object
                || (other instanceof AddressBook) // instanceof handles nulls
                && (persons.equals(((AddressBook) other).persons))
                && (listings.equals(((AddressBook) other).listings))
                && (offers.equals(((AddressBook) other).offers));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }

}
