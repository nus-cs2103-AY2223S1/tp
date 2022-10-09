package seedu.address.model.listing;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and updating of
 * persons uses Person#isSamePerson(Person) for equality so as to ensure that the person being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a person uses Person#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Listing#isSameListing(Listing)
 */
public class UniqueListingList implements Iterable<Listing> {

	private final ObservableList<Listing> internalList = FXCollections.observableArrayList();
	private final ObservableList<Listing> internalUnmodifiableList =
			FXCollections.unmodifiableObservableList(internalList);

	/**
	 * Returns true if the list contains an equivalent person as the given argument.
	 */
	public boolean contains(Listing toCheck) {
		requireNonNull(toCheck);
		return internalList.stream().anyMatch(toCheck::isSameListing);
	}

	/**
	 * Adds a person to the list.
	 * The person must not already exist in the list.
	 */
	public void add(Listing toAdd) {
		requireNonNull(toAdd);
		if (contains(toAdd)) {
			throw new DuplicatePersonException();
		}
		internalList.add(toAdd);
	}

	/**
	 * Replaces the person {@code target} in the list with {@code editedPerson}.
	 * {@code target} must exist in the list.
	 * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
	 */
	public void setListing(Listing target, Listing editedListing) {
		requireAllNonNull(target, editedListing);

		int index = internalList.indexOf(target);
		if (index == -1) {
			throw new PersonNotFoundException();
		}

		if (!target.isSameListing(editedListing) && contains(editedListing)) {
			throw new DuplicatePersonException();
		}

		internalList.set(index, editedListing);
	}

	/**
	 * Removes the equivalent person from the list.
	 * The person must exist in the list.
	 */
	public void remove(Listing toRemove) {
		requireNonNull(toRemove);
		if (!internalList.remove(toRemove)) {
			throw new PersonNotFoundException();
		}
	}

	public void setListings(UniqueListingList replacement) {
		requireNonNull(replacement);
		internalList.setAll(replacement.internalList);
	}

	/**
	 * Replaces the contents of this list with {@code persons}.
	 * {@code persons} must not contain duplicate persons.
	 */
	public void setListings(List<Listing> listings) {
		requireAllNonNull(listings);
		if (!listingsAreUnique(listings)) {
			throw new DuplicatePersonException();
		}

		internalList.setAll(listings);
	}

	/**
	 * Returns the backing list as an unmodifiable {@code ObservableList}.
	 */
	public ObservableList<Listing> asUnmodifiableObservableList() {
		return internalUnmodifiableList;
	}

	@Override
	public Iterator<Listing> iterator() {
		return internalList.iterator();
	}

	@Override
	public boolean equals(Object other) {
		return other == this // short circuit if same object
				|| (other instanceof UniqueListingList // instanceof handles nulls
				&& internalList.equals(((UniqueListingList) other).internalList));
	}

	@Override
	public int hashCode() {
		return internalList.hashCode();
	}

	/**
	 * Returns true if {@code persons} contains only unique persons.
	 */
	private boolean listingsAreUnique(List<Listing> persons) {
		for (int i = 0; i < persons.size() - 1; i++) {
			for (int j = i + 1; j < persons.size(); j++) {
				if (persons.get(i).isSameListing(persons.get(j))) {
					return false;
				}
			}
		}
		return true;
	}
}
