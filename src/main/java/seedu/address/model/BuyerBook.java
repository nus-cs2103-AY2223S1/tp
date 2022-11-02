package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.buyer.UniqueBuyerList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameBuyer comparison)
 */
public class BuyerBook implements ReadOnlyBuyerBook {

    private final UniqueBuyerList buyers;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        buyers = new UniqueBuyerList();
    }

    public BuyerBook() {}

    /**
     * Creates an BuyerBook using the Buyers in the {@code toBeCopied}
     */
    public BuyerBook(ReadOnlyBuyerBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the buyer list with {@code buyers}.
     * {@code buyers} must not contain duplicate buyers.
     */
    public void setBuyers(List<Buyer> buyers) {
        this.buyers.setBuyers(buyers);
    }

    /**
     * Resets the existing data of this {@code BuyerBook} with {@code newData}.
     */
    public void resetData(ReadOnlyBuyerBook newData) {
        requireNonNull(newData);

        setBuyers(newData.getBuyerList());
    }

    //// buyer-level operations

    /**
     * Returns true if a buyer with the same identity as {@code buyer} exists in the address book.
     */
    public boolean hasBuyer(Buyer buyer) {
        requireNonNull(buyer);
        return buyers.contains(buyer);
    }

    /**
     * Adds a buyer to the address book.
     * The buyer must not already exist in the address book.
     */
    public void addBuyer(Buyer p) {
        buyers.add(p);
    }

    /**
     * Replaces the given buyer {@code target} in the list with {@code editedBuyer}.
     * {@code target} must exist in the address book.
     * The buyer identity of {@code editedBuyer} must not be the same as another existing buyer in the address book.
     */
    public void setBuyer(Buyer target, Buyer editedBuyer) {
        requireNonNull(editedBuyer);

        buyers.setBuyer(target, editedBuyer);
    }

    /**
     * Removes {@code key} from this {@code BuyerBook}.
     * {@code key} must exist in the buyer book.
     */
    public void removeBuyer(Buyer key) {
        buyers.remove(key);
    }


    //// util methods

    @Override
    public String toString() {
        return buyers.asUnmodifiableObservableList().size() + " buyers";
        // TODO: refine later
    }

    @Override
    public ObservableList<Buyer> getBuyerList() {
        return buyers.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BuyerBook // instanceof handles nulls
                && buyers.equals(((BuyerBook) other).buyers));
    }

    @Override
    public int hashCode() {
        return buyers.hashCode();
    }
}
