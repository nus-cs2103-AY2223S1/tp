package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.tag.exceptions.DuplicateProductException;
import seedu.address.model.tag.exceptions.ProductNotFoundException;

/**
 * A list of products that enforces uniqueness between its elements and does not allow nulls.
 * A product is considered unique by comparing using {@code Product#equals(Product)}.
 * Supports a minimal set of list operations.
 *
 * @see Tag#equals(Object)
 */
public class UniqueProductList implements Iterable<Tag> {

    private final ObservableList<Tag> internalList = FXCollections.observableArrayList();
    private final ObservableList<Tag> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent product as the given argument.
     */
    public boolean contains(Tag toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a product to the list.
     * The product must not already exist in the list.
     */
    public void add(Tag toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateProductException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent product from the list.
     * The product must exist in the list.
     */
    public void remove(Tag toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ProductNotFoundException();
        }
    }


    /**
     * Replaces the contents of this list with {@code product}.
     * {@code persons} must not contain duplicate clients.
     */
    public void setProducts(List<Tag> tags) {
        requireAllNonNull(tags);
        if (!productsAreUnique(tags)) {
            throw new DuplicateProductException();
        }

        internalList.setAll(tags);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Tag> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Tag> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueProductList // instanceof handles nulls
                && internalList.equals(((UniqueProductList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code products} contains only unique products.
     */
    private boolean productsAreUnique(List<Tag> products) {
        for (int i = 0; i < products.size() - 1; i++) {
            for (int j = i + 1; j < products.size(); j++) {
                if (products.get(i).equals(products.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
