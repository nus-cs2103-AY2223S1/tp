package seedu.address.model.product;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.product.exceptions.DuplicateProductException;
import seedu.address.model.product.exceptions.ProductNotFoundException;

/**
 * A list of products that enforces uniqueness between its elements and does not allow nulls.
 * A product is considered unique through its name by comparing using {@code Product#equals(Product)}.
 * Supports a minimal set of list operations.
 *
 * @see Product#equals(Object)
 */
public class UniqueProductList implements Iterable<Product> {

    private final ObservableList<Product> internalList = FXCollections.observableArrayList();
    private final ObservableList<Product> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent product as the given argument.
     */
    public boolean contains(Product toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a product to the list.
     * The product must not already exist in the list.
     */
    public void add(Product toAdd) {
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
    public void remove(Product toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ProductNotFoundException();
        }
    }


    /**
     * Replaces the contents of this list with {@code products}.
     * {@code products} must not contain duplicate products.
     */
    public void setProducts(List<Product> products) {
        requireAllNonNull(products);
        if (!hasUniqueProducts(products)) {
            throw new DuplicateProductException();
        }

        internalList.setAll(products);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Product> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Product> iterator() {
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
    private boolean hasUniqueProducts(List<Product> products) {
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
