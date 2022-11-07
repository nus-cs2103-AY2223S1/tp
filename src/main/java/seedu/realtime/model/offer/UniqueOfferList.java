package seedu.realtime.model.offer;

import static java.util.Objects.requireNonNull;
import static seedu.realtime.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.realtime.model.listing.Listing;
import seedu.realtime.model.offer.exceptions.DuplicateOfferException;
import seedu.realtime.model.offer.exceptions.OfferNotFoundException;
import seedu.realtime.model.person.Address;
import seedu.realtime.model.person.Client;
import seedu.realtime.model.person.Name;

/**
 * A list of offers that enforces uniqueness between its elements and does not allow nulls.
 * An offer is considered unique by comparing using {@code Offer#isSameOffer(Offer)}. As such, adding and
 * updating of Offers uses Offer#isSameOffer(Offer) for equality to ensure that the Offer being added or
 * updated is unique in terms of identity in the UniqueOfferList. However, the removal of an Offer uses
 * Offer#equals(Object) to ensure that the Offer with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Offer#isSameOffer(Offer)
 */
public class UniqueOfferList implements Iterable<Offer> {

    private final ObservableList<Offer> internalList = FXCollections.observableArrayList();
    private final ObservableList<Offer> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent Offer as the given argument.
     */
    public boolean contains(Offer toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameOffer);
    }

    /**
     * Adds an Offer to the list.
     * The Offer must not already exist in the list.
     */
    public void add(Offer toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateOfferException();
        }
        internalList.add(toAdd);
        Collections.sort(internalList);
    }

    /**
     * Replaces the Offer {@code target} in the list with {@code editedOffer}.
     * {@code target} must exist in the list.
     * The Offer identity of {@code editedOffer} must not be the same as another existing Offer in the list.
     */
    public void setOffer(Offer target, Offer editedOffer) {
        requireAllNonNull(target, editedOffer);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new OfferNotFoundException();
        }

        if (!target.isSameOffer(editedOffer) && contains(editedOffer)) {
            throw new DuplicateOfferException();
        }

        internalList.set(index, editedOffer);
    }

    /**
     * Gets the offer from the given name {@code name} and listing address {@code address}.
     * @param name name of the person in offer
     * @param address listing address of offer
     * @return offer with given name and listing address
     */
    public Offer getOffer(Name name, Address address) {
        requireNonNull(name);
        for (Offer offer : internalList) {
            if (offer.getClient().equals(name) && offer.getListing().equals(address)) {
                return offer;
            }
        }
        throw new OfferNotFoundException();
    }

    /**
     * Removes the equivalent Offer from the list.
     * The Offer must exist in the list.
     */
    public void remove(Offer toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new OfferNotFoundException();
        }
    }

    public void setOffers(UniqueOfferList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code Offers}.
     * {@code Offers} must not contain duplicate Offers.
     */
    public void setOffers(List<Offer> offers) {
        requireAllNonNull(offers);
        if (!offersAreUnique(offers)) {
            throw new DuplicateOfferException();
        }

        internalList.setAll(offers);
    }

    /**
     * Remove all offers made by the client.
     */
    public void deleteOffersOfClient(Client toBeRemoved) {
        List<Offer> newInternalList = internalList.stream().filter(toBeRemoved::doNotHaveOffer)
            .collect(Collectors.toList());
        setOffers(newInternalList);
    }

    /**
     * Remove all offers related to listing.
     */
    public void deleteOffersForListing(Listing toBeRemoved) {
        List<Offer> newInternalList = internalList.stream().filter(toBeRemoved::doNotHaveOffer)
            .collect(Collectors.toList());
        setOffers(newInternalList);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Offer> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Offer> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueOfferList // instanceof handles nulls
                && internalList.equals(((UniqueOfferList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code offers} contains only unique Offers.
     */
    private boolean offersAreUnique(List<Offer> offers) {
        for (int i = 0; i < offers.size() - 1; i++) {
            for (int j = i + 1; j < offers.size(); j++) {
                if (offers.get(i).isSameOffer(offers.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}

